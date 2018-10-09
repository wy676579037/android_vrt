package com.jzycc.android_vrt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.jzycc.android_vrt.vrt_js.Constant;
import com.jzycc.android_vrt.vrt_js.VRTJsEngine;
import com.jzycc.android_vrt.vrt_js.VRTRenderListener;

public class MainActivity extends AppCompatActivity implements VRTRenderListener {

    private VRTJsEngine VRTJsEngine;
    private FrameLayout frameLayout;

    private static final String JS_CALL_JAVA_FUNCTION =
            "var ScriptAPI = java.lang.Class.forName(\"" + MainActivity.class.getName() + "\", true, javaLoader);" +
                    "var methodRead = ScriptAPI.getMethod(\"jsCallJava\", [java.lang.String]);" +
                    "function jsCallJava(url) {return methodRead.invoke(javaContext, url);}" +
                    "function Test(url){ return jsCallJava(url); }";

    private static final String JS_HEADER = "var ScriptAPI = java.lang.Class.forName(\"" + MainActivity.class.getName() + "\", true, javaLoader);" +
            "var methodRead = ScriptAPI.getMethod(\"native_commitVC\", [java.lang.Object]);\n"
            + Constant.JAVA_CALL_JS_FUNCTION;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VRTJsEngine = new VRTJsEngine(this);
        frameLayout = (FrameLayout)findViewById(R.id.fl_layout);
        VRTJsEngine.requestRenderInParent(frameLayout);


    }

    @Override
    public void renderSuccess(View view) {
        frameLayout.addView(view);
    }

    @Override
    public void setImageByUrl(View imageView, String imageUrl) {

    }

}
