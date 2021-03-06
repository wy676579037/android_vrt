package com.jzycc.android_vrt.vrt_js;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.jzycc.android_vrt.model.ViewControllerData;
import com.jzycc.android_vrt.model.VrtRefreshMsg;
import com.jzycc.android_vrt.model.VrtRequestBody;
import com.jzycc.android_vrt.model.VrtSectionData;
import com.jzycc.android_vrt.model.VrtViewData;
import com.jzycc.android_vrt.utils.CalculateUtils;
import com.jzycc.android_vrt.utils.FileUtils;
import com.jzycc.android_vrt.vrt.VRTActivity;
import com.jzycc.android_vrt.vrt.ViewController;
import com.jzycc.android_vrt.vrt.adapter.VrtListAdapter;
import com.jzycc.android_vrt.vrt.manager.VRTSdkManager;
import com.jzycc.android_vrt.vrt.manager.VRTViewManager;
import com.jzycc.android_vrt.vrt_js.constant.Constant;
import com.jzycc.android_vrt.vrt_js.constant.FunctionName;
import com.jzycc.android_vrt.vrt_js.manager.VRTJSNativeContract;
import com.jzycc.android_vrt.vrt_js.manager.VRTJsManager;
import com.jzycc.android_vrt.vrt_js.manager.VRTOkHttpManager;

import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeJSON;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * author Jzy
 * date 18-9-26
 */
public class VRTJsEngine implements VRTLifeCycle, VRTJSFunction{

    private static final String TAG = "VRTJsEngine";

    private Context mContext;
    private Class clazz;
    private String allFunctions = "";
    public final static String API_COMMITVC = "api_commitVC";
    private WindowManager windowManager;
    private ViewControllerData vc;
    private View vrtView;
    private VRTRenderListener VRTRenderListener;
    private VRTSdkManager vrtSdkManager;
    private int viewWidth;
    private VRTViewManager vrtViewManager;
    private int viewHeight;
    private boolean isHasParent = false;
    private boolean isRender = true;
    private org.mozilla.javascript.Context rhino;
    private Scriptable scope;
    private String vcJsCode = Constant.JAVA_CALL_JS_FUNCTION;
    private Gson gson;
    private VRTOkHttpManager okHttpManager = VRTOkHttpManager.getInstance();
    private Activity activity;
    private VRTJsManager vrtJsManager;
    private String url = "";
    private VRTJSNativeContract vrtjsNativeContract;
    public VRTJsEngine(Activity mContext) {
        this.mContext = mContext;
        this.activity = mContext;
        this.clazz = VRTJsEngine.class;
        this.VRTRenderListener = (VRTRenderListener)mContext;
        this.vrtSdkManager = VRTSdkManager.getInstance();
        this.vrtViewManager = new VRTViewManager(new HashMap<String, View>(), new HashMap<String, VrtViewData>());
        this.vrtJsManager = new VRTJsManager(mContext,this);
        windowManager = mContext.getWindowManager();
        this.gson = new Gson();
        initJSStr();
        vrtjsNativeContract = (VRTJSNativeContract)mContext;
    }

    private void initJSStr(){
        allFunctions = "var ScriptAPI = java.lang.Class.forName(\"" + VRTJsEngine.class.getName() + "\", true, javaLoader);\n" +
                Constant.JS_CODE_4ANDROID+
                Constant.JAVA_CALL_JS_FUNCTION+
                Constant.JS_CODE_TEST;
        vc = new ViewControllerData();
    }

    private void requestRenderInParent(final String jsCode, final ViewGroup parent){
        isHasParent = true;
        parent.post(new Runnable() {
            @Override
            public void run() {
                viewWidth = parent.getMeasuredWidth();
                viewHeight = parent.getMeasuredHeight();
                String vrtjsAndroidFunction = FileUtils.FiletoString(mContext,"VRTJSAndroidFunction.js");
                String vrtJsFrameworkCode = FileUtils.FiletoString(mContext,"VRTJSFramework.js");

                String result = "var ScriptAPI = java.lang.Class.forName(\"" + VRTJsEngine.class.getName() + "\", true, javaLoader);\n" +
                        vrtjsAndroidFunction +
                        vrtJsFrameworkCode+
                        jsCode;

                requestRender(result);
            }
        });

        String s = "var method_Api_rhino_test = ScriptAPI.getMethod(\"rhino_test\",[java.lang.String])\n" +
                "function rhino_test() {\n" +
                "    var str = \"jzy666\";\n" +
                "    method_Api_rhino_test .invoke(javaContext,str);\n" +
                "}";
    }

    public void requestRender(String jsCode){
        rhino = org.mozilla.javascript.Context.enter();
        rhino.setOptimizationLevel(-1);

        try{
            scope = rhino.initStandardObjects();
            ScriptableObject.putProperty(scope,"javaContext", org.mozilla.javascript.Context.javaToJS(this,scope));
            ScriptableObject.putProperty(scope,"javaLoader", org.mozilla.javascript.Context.javaToJS(clazz.getClassLoader(),scope));
            Object x = rhino.evaluateString(scope, jsCode, clazz.getSimpleName(), 1, null);
            onStart();
            onResume();
            callBackViewDidLoad();
        }finally {

        }
    }

    /**
     * @param fileName
     */
    public void requestRenderByFile(String fileName){
        String vrtJsCode = FileUtils.FiletoString(mContext, fileName);
        if(TextUtils.isEmpty(vrtJsCode)){
            Log.w(TAG, "requestRenderByFile: the file does not exist or get file faield");
        }else {
            String vrtjsAndroidFunction = FileUtils.FiletoString(mContext,"VRTJSAndroidFunction.js");
            String vrtJsFrameworkCode = FileUtils.FiletoString(mContext,"VRTJSFramework.js");

            String result = "var ScriptAPI = java.lang.Class.forName(\"" + VRTJsEngine.class.getName() + "\", true, javaLoader);\n" +
                    vrtjsAndroidFunction+
                    vrtJsFrameworkCode+
                    vrtJsCode;

            requestRender(result);
        }
    }
    
    public void requestRenderByUrl(String url, final ViewGroup parent){
        this.url = url;
        okHttpManager.getClient().newCall(VRTOkHttpManager.getJsByUrl(url)).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "onFailure: ",e );
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String jsCode = response.body().string();
                if(!TextUtils.isEmpty(jsCode)){
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(parent != null){
                                requestRenderInParent(jsCode,parent);
                            }else {
                                requestRender(jsCode);
                            }
                        }
                    });
                }else {
                    Log.e(TAG, "onResponse: no jscode");
                }
            }
        }); 
    }

    @Override
    public void onStart() {
        if(isRender){
            callFunction(FunctionName.API_RESPONSE_BASIC_CALL_BACK,new Object[]{("CallBackViewWillAppear")});
        }
    }

    @Override
    public void onResume() {
        if(isRender){
            callFunction(FunctionName.API_RESPONSE_BASIC_CALL_BACK,new Object[]{("CallBackViewDidAppear")});
        }
    }

    @Override
    public void callBackViewDidLoad() {
        if(isRender){
            callFunction(FunctionName.API_RESPONSE_BASIC_CALL_BACK,new Object[]{("CallBackViewDidLoad")});
        }
    }

    @Override
    public void onPause() {
        if(isRender){
            callFunction(FunctionName.API_RESPONSE_BASIC_CALL_BACK,new Object[]{("CallBackViewWillDisappear")});
        }
    }

    @Override
    public void onDestroy() {
        org.mozilla.javascript.Context.exit();
    }


    @Override
    public void api_log(Object msg){
        Log.i("jzy111", "api_log: "+msg);
    }

    @Override
    public void api_commitVC4Android(String jsonStr){
        vc = gson.fromJson(jsonStr, ViewControllerData.class);
        if(isHasParent){
            setViewController(vc,viewWidth);
        }else {
            setViewController(vc,CalculateUtils.getWindowWidth(windowManager));
        }
    }

    @Override
    public void api_refreshView(String refreshMsg){
        VrtRefreshMsg vrtRefreshMsg = gson.fromJson(refreshMsg,VrtRefreshMsg.class);
        vrtJsManager.refreshView(vrtRefreshMsg.get_vrtId(),vrtRefreshMsg.get_key(),vrtRefreshMsg.get_newValue());
    }

    @Override
    public void api_httpRequest(String  jsObjectJsonStr){
        VrtRequestBody vrtRequestBody = gson.fromJson(jsObjectJsonStr, VrtRequestBody.class);
        vrtJsManager.callbackHttpRequestForJs(vrtRequestBody.getUrl(),vrtRequestBody.get_param());
    }

    private void setViewController(ViewControllerData vc, float width){
        try{
            ViewController viewController = new ViewController(mContext,vrtJsManager,vc, width);
            vrtView = viewController.getVRTRenderView();
            isRender = true;
            VRTRenderListener.renderSuccess(vrtView);
        }catch (Exception e){
            Log.e("VRTJsEngine", "setViewController: ",e );
        }

    }

    public void callFunction(String functionName,Object[] functionParams){
        Function function = (Function) scope.get(functionName,scope);
        function.call(rhino,scope,scope,functionParams);
    }

    public Object getNativeJsonObject(String controlValueJsonString){
        return NativeJSON.parse(rhino, scope, controlValueJsonString, new Callable() {
            @Override
            public Object call(org.mozilla.javascript.Context context, Scriptable scriptable, Scriptable scriptable1, Object[] objects) {
                return objects[1];
            }
        });
    }

    @Override
    public Map<String,Object> api_getThisNavigationComp(){
        Map<String, Object> map = new HashMap<>();
        map.put("url",url);
        return  map;
    }

    @Override
    public void api_pushUrlWithParam(String jsonStrt){
        Map param = gson.fromJson(jsonStrt,Map.class);
        VRTActivity.start(mContext, param.get("url").toString(), param.get("param"));
    }

    @Override
    public Object api_getPushedParam(){
        return vrtjsNativeContract.getPushParams();
    }

    @Override
    public void api_popThis(){

    }

    @Override
    public void api_refreshListData(String jsonStr){
        VrtSectionData vrtSectionData = gson.fromJson(jsonStr, VrtSectionData.class);

        RecyclerView recyclerView = (RecyclerView) vrtViewManager.getVrtViewMap().get(vrtSectionData.get_vrtId());
        VrtListAdapter vrtListAdapter = new VrtListAdapter(mContext,vrtJsManager, vrtSectionData.get_vrtId(),VrtListAdapter.getList(vrtSectionData));
        recyclerView.setAdapter(vrtListAdapter);
    }

    public VRTViewManager getVrtViewManager() {
        return vrtViewManager;
    }
}
