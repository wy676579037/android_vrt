package com.jzycc.android_vrt.vrt_js.constant;

/**
 * author Jzy(Xiaohuntun)
 * date 18-9-27
 */
public class Constant {
    public static final String JAVA_CALL_JS_FUNCTION = "Object.prototype._clsName = null;\n" +
            "\n" +
            "var vrtViewIndex = 0;\n" +
            "\n" +
            "var _kHeightScale = 0;\n" +
            "var _kWidthScale = 0;\n" +
            "var _kFontScale = 0;\n" +
            "\n" +
            "function Vec2(x,y)\n" +
            "{\n" +
            "    this.x = x;\n" +
            "    this.y = y;\n" +
            "}\n" +
            "\n" +
            "function Vec4(x,y,z,w)\n" +
            "{\n" +
            "    this.x = x;\n" +
            "    this.y = y;\n" +
            "    this.z = z;\n" +
            "    this.w = w;\n" +
            "}\n" +
            "\n" +
            "var redColor = new Vec4(255,0,0,255);\n" +
            "var greenColor = new Vec4(0,255,0,255);\n" +
            "var blackColor = new Vec4(0,0,0,255);\n" +
            "var whiteColor = new Vec4(255,255,255,255);\n" +
            "var clearColor = new Vec4(0,0,0,0);\n" +
            "\n" +
            "function createColorWithRGBA(r,g,b,a)\n" +
            "{\n" +
            "    return new Vec4(r,g,b,a);\n" +
            "}\n" +
            "\n" +
            "function createFrameWithRect(x,y,width,height)\n" +
            "{\n" +
            "    return new Vec4(x,y,width,height);\n" +
            "}\n" +
            "\n" +
            "var _vrt_kvo_responserIndex = 0;\n" +
            "var _vrt_kvo_responserCache = {};\n" +
            "var _vrt_kvo_targetCache = {};\n" +
            "\n" +
            "function vrt_kvo_bind(target,key,func)\n" +
            "{\n" +
            "    var hashKey = key + \" KVO \" + target._vrtId;\n" +
            "    if(_vrt_kvo_targetCache[hashKey] == undefined)\n" +
            "    {\n" +
            "        var vrt_kvo_target = new Object();\n" +
            "        vrt_kvo_target.target = target;\n" +
            "        vrt_kvo_target.targetValue = target[key];\n" +
            "        _vrt_kvo_targetCache[hashKey] = vrt_kvo_target;\n" +
            "    }\n" +
            "    \n" +
            "    var repHashKey = hashKey + \"REPINDEXMASK\" + _vrt_kvo_responserIndex++;\n" +
            "    var vrt_kvo_responser = new Object();\n" +
            "    vrt_kvo_responser.callBack = func;\n" +
            "    \n" +
            "    if(_vrt_kvo_responserCache[repHashKey] == undefined)\n" +
            "    {\n" +
            "        _vrt_kvo_responserCache[repHashKey] = new Array();\n" +
            "        \n" +
            "        Object.defineProperty(target,key,{\n" +
            "                              get : function(){\n" +
            "                              return _vrt_kvo_targetCache[hashKey].targetValue;\n" +
            "                              },\n" +
            "                              set : function(value){\n" +
            "                              var tmpArray;\n" +
            "                              if(_vrt_kvo_responserCache[repHashKey] != undefined)\n" +
            "                              {\n" +
            "                              tmpArray = _vrt_kvo_responserCache[repHashKey];\n" +
            "                              tmpArray.forEach(element => {\n" +
            "                                               element.callBack(_vrt_kvo_targetCache[hashKey].target,key,_vrt_kvo_targetCache[hashKey].targetValue,value);\n" +
            "                                               });\n" +
            "                              }\n" +
            "                              _vrt_kvo_targetCache[hashKey].targetValue = value;\n" +
            "                              }\n" +
            "                              });\n" +
            "    }\n" +
            "    \n" +
            "    _vrt_kvo_responserCache[repHashKey].push(vrt_kvo_responser);\n" +
            "}\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "var _vrt_callback_cache = {};\n" +
            "\n" +
            "function addCallBack(key,func)\n" +
            "{\n" +
            "    _vrt_callback_cache[key] = func;\n" +
            "}\n" +
            "\n" +
            "function _api_responseBasicCallBack(key)\n" +
            "{\n" +
            "    var func = _vrt_callback_cache[key];\n" +
            "    if(func != null || func != undefined)\n" +
            "        func();\n" +
            "}\n" +
            "\n" +
            "function _api_responseListDidSelectRow(key,section,row)\n" +
            "{\n" +
            "    var func = _vrt_callback_cache[key];\n" +
            "    if(func != null || func != undefined)\n" +
            "        func(section,row);\n" +
            "}\n" +
            "\n" +
            "function _api_responseTextFieldReturn(key,text)\n" +
            "{\n" +
            "    var func = _vrt_callback_cache[key];\n" +
            "    if(func != null || func != undefined)\n" +
            "        func(text);\n" +
            "}\n" +
            "\n" +
            "\n" +
            "/**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**//**/\n" +
            "function vrt_layout(){\n" +
            "    \n" +
            "    this.masterView = null;\n" +
            "    this.pendCenterX = null;\n" +
            "    this.pendCenterY = null;\n" +
            "    \n" +
            "    this._resolvePendCenterX = function()\n" +
            "    {\n" +
            "        if(this.pendCenterX != null)\n" +
            "        {\n" +
            "            this.masterView._x = this.pendCenterX - this.masterView._width/2.0;\n" +
            "        }\n" +
            "    }\n" +
            "    \n" +
            "    this._resolvePendCenterY = function()\n" +
            "    {\n" +
            "        if(this.pendCenterY != null)\n" +
            "        {\n" +
            "            this.masterView._y = this.pendCenterY - this.masterView._height/2.0;\n" +
            "        }\n" +
            "    }\n" +
            "    \n" +
            "    this.heightIs = function(height){\n" +
            "        this.masterView._height = height * _kHeightScale;\n" +
            "        this._resolvePendCenterY();\n" +
            "        return this;\n" +
            "    }\n" +
            "    \n" +
            "    this.widthIs = function(width){\n" +
            "        this.masterView._width = width * _kWidthScale;\n" +
            "        this._resolvePendCenterX();\n" +
            "        return this;\n" +
            "    }\n" +
            "    \n" +
            "    this.widthEqualToHeight = function(){\n" +
            "        this.masterView._width = this.masterView.height();\n" +
            "        this._resolvePendCenterX();\n" +
            "        return this;\n" +
            "    }\n" +
            "    \n" +
            "    this.heightEqualToWidth = function(){\n" +
            "        this.masterView._height = this.masterView.width();\n" +
            "        this._resolvePendCenterY();\n" +
            "        return this;\n" +
            "    }\n" +
            "    \n" +
            "    this.heightRatioToView = function(view,scale){\n" +
            "        if(view == null)\n" +
            "            view = this.masterView.superView;\n" +
            "        this.masterView._height = view.height() * scale;\n" +
            "        this._resolvePendCenterY();\n" +
            "        return this;\n" +
            "    }\n" +
            "    \n" +
            "    this.widthRatioToView = function(view,scale){\n" +
            "        if(view == null)\n" +
            "            view = this.masterView.superView;\n" +
            "        this.masterView._width = view.width() * scale;\n" +
            "        return this;\n" +
            "    }\n" +
            "    \n" +
            "    this.centerYEqualToView = function(view)\n" +
            "    {\n" +
            "        if(view == null)\n" +
            "            view = this.masterView.superView;\n" +
            "        if(this.masterView.superView == view)\n" +
            "            this.pendCenterY = view.height()/2.0;\n" +
            "        else\n" +
            "            this.pendCenterY = view.center().y;\n" +
            "        return this;\n" +
            "    }\n" +
            "    \n" +
            "    this.centerYIs = function(y)\n" +
            "    {\n" +
            "        this.pendCenterY = y * _kHeightScale;\n" +
            "        return this;\n" +
            "    }\n" +
            "    \n" +
            "    this.topEqualToView = function(view){\n" +
            "        if(view == null)\n" +
            "            view = this.masterView.superView;\n" +
            "        this.pendCenterY = null;\n" +
            "        if(this.masterView.superView == view)\n" +
            "            this.masterView._y = 0;\n" +
            "        else\n" +
            "            this.masterView._y = view._y;\n" +
            "        return this;\n" +
            "    }\n" +
            "    \n" +
            "    this.topSpaceToView = function(view,space){\n" +
            "        space = space * _kHeightScale;\n" +
            "        if(view == null)\n" +
            "            view = this.masterView.superView;\n" +
            "        this.pendCenterY = null;\n" +
            "        if(this.masterView.superView == view)\n" +
            "            this.masterView._y = space;\n" +
            "        else\n" +
            "            this.masterView._y = space + view.bottom();\n" +
            "        return this;\n" +
            "    }\n" +
            "    \n" +
            "    this.centerXEqualToView = function(view)\n" +
            "    {\n" +
            "        if(view == null)\n" +
            "            view = this.masterView.superView;\n" +
            "        if(this.masterView.superView == view)\n" +
            "            this.pendCenterX = view.width()/2.0;\n" +
            "        else\n" +
            "            this.pendCenterX = view.center().x;\n" +
            "        return this;\n" +
            "    }\n" +
            "    \n" +
            "    this.centerXIs = function(x)\n" +
            "    {\n" +
            "        this.pendCenterX = x * _kWidthScale;\n" +
            "        return this;\n" +
            "    }\n" +
            "    \n" +
            "    this.leftEqualToView = function(view){\n" +
            "        if(view == null)\n" +
            "            view = this.masterView.superView;\n" +
            "        this.pendCenterX = null;\n" +
            "        if(this.masterView.superView == view)\n" +
            "            this.masterView._x = 0;\n" +
            "        else\n" +
            "            this.masterView._x = view.left();\n" +
            "        return this;\n" +
            "    }\n" +
            "    \n" +
            "    this.leftSpaceToView = function(view,space){\n" +
            "        space = space * _kWidthScale;\n" +
            "        if(view == null)\n" +
            "            view = this.masterView.superView;\n" +
            "        this.pendCenterX = null;\n" +
            "        if(this.masterView.superView == view)\n" +
            "            this.masterView._x = space;\n" +
            "        else\n" +
            "            this.masterView._x = view.right() + space;\n" +
            "        return this;\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "function _kvo_add_refresh_prop(view,key)\n" +
            "{\n" +
            "    vrt_kvo_bind(view,key,function(target,key,oldVlaue,newValue){\n" +
            "                 api_refreshView(target._vrtId,key,newValue);\n" +
            "                 });\n" +
            "}\n" +
            "\n" +
            "function _registerRefreshView(view)\n" +
            "{\n" +
            "    _kvo_add_refresh_prop(view,'_x');\n" +
            "    _kvo_add_refresh_prop(view,'_y');\n" +
            "    _kvo_add_refresh_prop(view,'_height');\n" +
            "    _kvo_add_refresh_prop(view,'_width');\n" +
            "    \n" +
            "    _kvo_add_refresh_prop(view,'backgroundColor');\n" +
            "    _kvo_add_refresh_prop(view,'cornerRadius');\n" +
            "}\n" +
            "\n" +
            "function View()\n" +
            "{\n" +
            "    this._vrtId = vrtViewIndex++;\n" +
            "    \n" +
            "    this._x = null;\n" +
            "    this._y = null;\n" +
            "    this._height = null;\n" +
            "    this._width = null;\n" +
            "    \n" +
            "    this.superView = null;\n" +
            "    this._clsName = 'View';\n" +
            "    this.subViews = new Array();\n" +
            "    this.backgroundColor = clearColor;\n" +
            "    this.vrt_layout = new vrt_layout();\n" +
            "    this.vrt_layout.masterView = this;\n" +
            "    \n" +
            "    this.cornerRadius = 0;\n" +
            "    \n" +
            "    _registerRefreshView(this);\n" +
            "    \n" +
            "    this._setFrame = function(x,y,width,height)\n" +
            "    {\n" +
            "        this._x = x;\n" +
            "        this._y = y;\n" +
            "        this._width = width;\n" +
            "        this._height = height;\n" +
            "    }\n" +
            "    \n" +
            "    this.addClick = function(view,func)\n" +
            "    {\n" +
            "        addCallBack(view._vrtId,func);\n" +
            "        api_addViewClick(view._vrtId);\n" +
            "    }\n" +
            "    \n" +
            "    this.addSubView = function(subView){\n" +
            "        if(subView == this || subView == null)\n" +
            "        {\n" +
            "            return;\n" +
            "        }\n" +
            "        this.subViews.push(subView);\n" +
            "        subView.superView = this;\n" +
            "    }\n" +
            "    \n" +
            "    this.top = function()\n" +
            "    {\n" +
            "        if(this._y == null)\n" +
            "            this._y = this.vrt_layout._getTop();\n" +
            "        return this._y;\n" +
            "    }\n" +
            "    \n" +
            "    this.left = function()\n" +
            "    {\n" +
            "        if(this._x == null)\n" +
            "            this._x = this.vrt_layout._getLeft();\n" +
            "        return this._x;\n" +
            "    }\n" +
            "    \n" +
            "    this.right = function()\n" +
            "    {\n" +
            "        return this.left() + this.width();\n" +
            "    }\n" +
            "    \n" +
            "    this.bottom = function()\n" +
            "    {\n" +
            "        return this.top() + this.height();\n" +
            "    }\n" +
            "    \n" +
            "    this.height = function()\n" +
            "    {\n" +
            "        if(this._height == null)\n" +
            "            this._height = this.vrt_layout._getHeight();\n" +
            "        return this._height;\n" +
            "    }\n" +
            "    \n" +
            "    this.width = function()\n" +
            "    {\n" +
            "        if(this._width == null)\n" +
            "            this._width = this.vrt_layout._getWidth();\n" +
            "        return this._width;\n" +
            "    }\n" +
            "    \n" +
            "    this.center = function()\n" +
            "    {\n" +
            "        return new Vec2((this.left() + this.right())/2.0 , (this.top() + this.bottom())/2.0);\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "\n" +
            "function ViewController()\n" +
            "{\n" +
            "    this._vrtId = -1;\n" +
            "    this.view = new View();\n" +
            "    this.view._setFrame(0,0,api_getBaseViewWidth() * 1,api_getBaseViewHeight(true,true) * 1);//667 full screen  554 with Navigation Bar & Tab Bar\n" +
            "    _kHeightScale = this.view._height/667.0;\n" +
            "    _kWidthScale = this.view._width/375.0;\n" +
            "    _kFontScale = _kWidthScale;\n" +
            "    this.view.backgroundColor = whiteColor;\n" +
            "    this.view.superView = null;\n" +
            "    this._clsName = 'ViewController';\n" +
            "    this.title = \"title\";\n" +
            "    \n" +
            "    _kvo_add_refresh_prop(this,'title');\n" +
            "    \n" +
            "    this.addCallBackViewDidLoad = function(func){\n" +
            "        addCallBack(this._vrtId + \"CallBackViewDidLoad\",func);\n" +
            "    }\n" +
            "    \n" +
            "    this.addCallBackViewWillAppear = function(func){\n" +
            "        addCallBack(this._vrtId + \"CallBackViewWillAppear\",func);\n" +
            "    }\n" +
            "    \n" +
            "    this.addCallBackViewDidAppear = function(func){\n" +
            "        addCallBack(this._vrtId + \"CallBackViewDidAppear\",func);\n" +
            "    }\n" +
            "    \n" +
            "    this.addCallBackViewWillDisappear = function(func){\n" +
            "        addCallBack(this._vrtId + \"CallBackViewWillDisappear\",func);\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "\n" +
            "function Label()\n" +
            "{\n" +
            "    View.call(this);\n" +
            "    this._clsName = 'Label';\n" +
            "    this.text = '';\n" +
            "    this.fontSize = 12 * _kFontScale;\n" +
            "    this.textColor = blackColor;\n" +
            "    this.numberOfLines = 1;\n" +
            "    \n" +
            "    _kvo_add_refresh_prop(this,'text');\n" +
            "    _kvo_add_refresh_prop(this,'fontSize');\n" +
            "    _kvo_add_refresh_prop(this,'textColor');\n" +
            "    _kvo_add_refresh_prop(this,'numberOfLines');\n" +
            "}\n" +
            "\n" +
            "function ImgView()\n" +
            "{\n" +
            "    View.call(this);\n" +
            "    this._clsName = 'ImgView';\n" +
            "    this.imageUrl = null;\n" +
            "    _kvo_add_refresh_prop(this,'imageUrl');\n" +
            "}\n" +
            "\n" +
            "function List()\n" +
            "{\n" +
            "    View.call(this);\n" +
            "    this._clsName = 'List';\n" +
            "    \n" +
            "    this._cell = {};\n" +
            "    this._dataSource = {};\n" +
            "    \n" +
            "    this.setCellAtSection = function(section,Celltmp)\n" +
            "    {\n" +
            "        this._cell[section] = Celltmp;\n" +
            "    }\n" +
            "    \n" +
            "    this.setDataSourceAtSection = function(section,data)\n" +
            "    {\n" +
            "        this._dataSource[section] = data;\n" +
            "    }\n" +
            "    \n" +
            "    this.addCallBackDidSelectRowAtIndexPath = function(func)\n" +
            "    {\n" +
            "        addCallBack(this._vrtId + \"CallBackDidSelectRowAtIndexPath\",func);\n" +
            "    }\n" +
            "    \n" +
            "    this.reloadData = function()\n" +
            "    {\n" +
            "        api_refreshList(this._vrtId);\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "function bindKey(key)\n" +
            "{\n" +
            "    return \"bindKey:\" + key;\n" +
            "}\n" +
            "\n" +
            "function Cell()\n" +
            "{\n" +
            "    View.call(this);\n" +
            "    this._clsName = 'Cell';\n" +
            "    this.setCellFixHeight = function(fixHeight)\n" +
            "    {\n" +
            "        this._setFrame(0,0,0,fixHeight);\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "function TextField()\n" +
            "{\n" +
            "    View.call(this);\n" +
            "    this._clsName = \"TextField\";\n" +
            "    this.text = \"\";\n" +
            "    this.fontSize = 12 * _kFontScale;\n" +
            "    this.textColor = blackColor;\n" +
            "    \n" +
            "    _kvo_add_refresh_prop(this,'text');\n" +
            "    _kvo_add_refresh_prop(this,'fontSize');\n" +
            "    _kvo_add_refresh_prop(this,'textColor');\n" +
            "    \n" +
            "    this.addCallBackDidReturn = function(func)\n" +
            "    {\n" +
            "        addCallBack(this._vrtId + \"CallBackDidReturn\",func);\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "\n" +
            "var _vrt_httpRqe_Cache = {};\n" +
            "//原型func(json data,string info)\n" +
            "function HttpRequest(url,func)\n" +
            "{\n" +
            "    this.url = url;\n" +
            "    this._param = {};\n" +
            "    \n" +
            "    this.request = function(param)\n" +
            "    {\n" +
            "        if(param == null)\n" +
            "            this._param = {};\n" +
            "        else\n" +
            "            this._param = param;\n" +
            "        api_httpRequest(this);\n" +
            "    }\n" +
            "    _vrt_httpRqe_Cache[url] = func;\n" +
            "}\n" +
            "\n" +
            "function _api_httpResponse(url,data,info)\n" +
            "{\n" +
            "    var httpReqFunc = _vrt_httpRqe_Cache[url];\n" +
            "    if(httpReqFunc)\n" +
            "    {\n" +
            "        httpReqFunc(data,info);\n" +
            "    }\n" +
            "}";

    public static final String JS_TEST_CODE = "//创建测试数据\n" +
            "function model4Test()\n" +
            "{\n" +
            "    this.userName = null;\n" +
            "}\n" +
            "\n" +
            "var dataSource = new Array();\n" +
            "\n" +
            "for(var i = 0; i < 10; i++)\n" +
            "{\n" +
            "    var model = new model4Test();\n" +
            "    model.userName = \"userName \" + i;\n" +
            "    dataSource.push(model);\n" +
            "}\n" +
            "\n" +
            "//创建唯一的视图控制器\n" +
            "var mainVC = new ViewController();\n" +
            "\n" +
            "\n" +
            "\n" +
            "//在视图不同阶段添加回调\n" +
            "mainVC.addCallBackViewDidLoad(function(){\n" +
            "                              // api_log(\"view did load\");\n" +
            "                              });\n" +
            "mainVC.addCallBackViewWillAppear(function(){\n" +
            "                                 // api_log(\"view will appear\");\n" +
            "                                 });\n" +
            "mainVC.addCallBackViewDidAppear(function(){\n" +
            "                                // api_log(\"view did appear\");\n" +
            "                                });\n" +
            "mainVC.addCallBackViewWillDisappear(function(){\n" +
            "                                    // api_log(\"view will disappear\");\n" +
            "                                    });\n" +
            "\n" +
            "//创建一个列表视图\n" +
            "var list = new List();\n" +
            "\n" +
            "//添加列表中cell的点击回调\n" +
            "list.addCallBackDidSelectRowAtIndexPath(function(section,row){\n" +
            "                                        api_log(\" did select section : \" + section + \" row : \" + row);\n" +
            "                                        });\n" +
            "\n" +
            "mainVC.view.addSubView(list);\n" +
            "list.vrt_layout.topEqualToView(null).leftEqualToView(null).heightRatioToView(null,0.5).widthRatioToView(null,1);\n" +
            "\n" +
            "//设置list的数据源\n" +
            "list.setDataSourceAtSection(0,dataSource);\n"+
            "api_commitVC(mainVC);";

    public static final String JS_CODE_4ANDROID = "/**\n" +
            " * Created by JzyCc on 2018/10/3.\n" +
            " */\n" +
            "\n" +
            "\n" +
            "//api commit\n" +
            "var method_Api_commitVC4Android = ScriptAPI.getMethod(\"api_commitVC4Android\",[java.lang.String])\n" +
            "function api_commitVC() {\n" +
            "\n" +
            "    var vc1 = new ViewController()\n" +
            "\n" +
            "    vc1 = mainVC\n" +
            "\n" +
            "    getVcForAndroid([vc1.view])\n" +
            "\n" +
            "    var str = JSON.stringify(vc1)\n" +
            "\n" +
            "    method_Api_commitVC4Android.invoke(javaContext,str);\n" +
            "\n" +
            "}\n" +
            "\n" +
            "function getVcForAndroid(v){\n" +
            "    if(v!=null){\n" +
            "        for(var i =0;i < v.length; i++)\n" +
            "        {\n" +
            "            v[i].superView = null; \n" +
            "            v[i].vrt_layout.masterView = null;\n" +
            "            if(v[i]._clsName == \"List\")\n" +
            "            {\n" +
            "                for(var key in v[i]._cell)\n" +
            "                {\n" +
            "                    var cell = v[i]._cell[key];\n" +
            "                    if(key == \"_clsName\" || cell._clsName != \"Cell\")\n" +
            "                        continue;\n" +
            "                    cell.superView = null;\n" +
            "                    cell.vrt_layout.masterView = null;\n" +
            "                    getVcForAndroid(cell.subViews)\n" +
            "                }\n" +
            "            }\n" +
            "            getVcForAndroid(v[i].subViews);\n" +
            "        }\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "//log\n" +
            "var method_Api_log = ScriptAPI.getMethod(\"api_log\",[java.lang.String])\n" +
            "function api_log(msg){\n" +
            "    method_Api_log.invoke(javaContext,msg)\n" +
            "}\n" +
            "\n" +
            "//获取本地高\n" +
            "var method_Api_getBaseViewWidth = ScriptAPI.getMethod(\"api_getBaseViewWidth\")\n" +
            "function api_getBaseViewWidth(){\n" +
            "    return method_Api_getBaseViewWidth.invoke(javaContext)\n" +
            "}\n" +
            "\n" +
            "//获取本地宽\n" +
            "var method_Api_getBaseViewHeight = ScriptAPI.getMethod(\"api_getBaseViewHeight\")\n" +
            "function api_getBaseViewHeight(){\n" +
            "    return method_Api_getBaseViewHeight.invoke(javaContext)\n" +
            "}\n" +
            "\n" +
            "//获取设备系统名\n" +
            "function api_platform(){\n" +
            "    return \"Android\"\n" +
            "}\n" +
            "\n" +
            "//刷新view\n" +
            "var method_Api_refreshView = ScriptAPI.getMethod(\"api_refreshView\",[java.lang.String])\n" +
            "function api_refreshView(vrtId, key, newValue){\n" +
            "    var refreshModel = new RefreshModel();\n" +
            "    refreshModel._vrtId = vrtId;\n" +
            "    refreshModel._key = key;\n" +
            "    refreshModel._newValue = newValue;\n" +
            "    \n" +
            "    var jsonStr = JSON.stringify(refreshModel)\n" +
            "    method_Api_refreshView.invoke(javaContext,jsonStr)\n" +
            "}\n" +
            "\n" +
            "function RefreshModel(){\n" +
            "    var _vrtId;\n" +
            "    var _key;\n" +
            "    var _newValue;\n" +
            "}"+
            "\n" +
            "//网络请求\n" +
            "var method_Api_httpRequest = ScriptAPI.getMethod(\"api_httpRequest\",[java.lang.String])\n" +
            "function api_httpRequest(jsObject){\n" +
            "    var jsonStr = JSON.stringify(jsObject)\n" +
            "    method_Api_httpRequest.invoke(javaContext,jsonStr)\n" +
            "}\n" +
            "\n" +
            "//点击回调\n" +
            "var method_Api_addViewClick = ScriptAPI.getMethod(\"api_addViewClick\",[java.lang.String])\n" +
            "function api_addViewClick(vrtId){\n" +
            "    method_Api_addViewClick.invoke(javaContext,vrtId+\"\")\n" +
            "}\n";

    public static final String JS_CODE_TEST = "function model4Test()\n" +
            "{\n" +
            "    this.userName = null;\n" +
            "}\n" +
            "\n" +
            "var dataSource = new Array();\n" +
            "\n" +
            "for(var i = 0; i < 10; i++)\n" +
            "{\n" +
            "    var model = new model4Test();\n" +
            "    model.userName = \"userName \" + i;\n" +
            "    dataSource.push(model);\n" +
            "}\n" +
            "\n" +
            "//创建唯一的视图控制器\n" +
            "var mainVC = new ViewController();\n" +
            "\n" +
            "\n" +
            "\n" +
            "//在视图不同阶段添加回调\n" +
            "mainVC.addCallBackViewDidLoad(function(){\n" +
            "                              // api_log(\"view did load\");\n" +
            "                              });\n" +
            "mainVC.addCallBackViewWillAppear(function(){\n" +
            "                                 // api_log(\"view will appear\");\n" +
            "                                 });\n" +
            "mainVC.addCallBackViewDidAppear(function(){\n" +
            "                                // api_log(\"view did appear\");\n" +
            "                                });\n" +
            "mainVC.addCallBackViewWillDisappear(function(){\n" +
            "                                    // api_log(\"view will disappear\");\n" +
            "                                    });\n" +
            "\n" +
            "//创建一个列表视图\n" +
            "var list = new List();\n" +
            "\n" +
            "//添加列表中cell的点击回调\n" +
            "list.addCallBackDidSelectRowAtIndexPath(function(section,row){\n" +
            "                                        api_log(\" did select section : \" + section + \" row : \" + row);\n" +
            "                                        });\n" +
            "\n" +
            "mainVC.view.addSubView(list);\n" +
            "list.vrt_layout.topEqualToView(null).leftEqualToView(null).heightRatioToView(null,0.5).widthRatioToView(null,1);\n" +
            "\n" +
            "//创建一个cell模版\n" +
            "var cell = new Cell();\n" +
            "//设置cell的固定高度 (当前不支持动态高度自适应)\n" +
            "cell.setCellFixHeight(100);\n" +
            "\n" +
            "//创建一个图片视图\n" +
            "var imgView = new ImgView();\n" +
            "imgView.imageUrl = \"http://114.55.84.37/anbao/img/dog.png\";\n" +
            "cell.addSubView(imgView);\n" +
            "imgView.vrt_layout.topSpaceToView(cell,10).leftSpaceToView(cell,10).heightIs(60).widthEqualToHeight();\n" +
            "\n" +
            "//创建一个文本显示视图\n" +
            "var label1 = new Label();\n" +
            "//因为该视图是放在cell模版中的、需要bind一个model中的key\n" +
            "label1.text = bindKey(\"userName\");\n" +
            "label1.textColor = redColor;\n" +
            "label1.fontSize = 18;\n" +
            "cell.addSubView(label1);\n" +
            "label1.vrt_layout.topSpaceToView(cell,10).leftSpaceToView(imgView,10).heightIs(60).widthIs(150);\n" +
            "\n" +
            "//给list设置cell模版、native将根据cell模版生成合适数量的视图、并进行复用\n" +
            "list.setCellAtSection(0,cell);\n" +
            "//设置list的数据源\n" +
            "list.setDataSourceAtSection(0,dataSource);\n" +
            "\n" +
            "\n" +
            "var basicUrl = \"http://dyba.xuebaeasy.com:8090/\";\n" +
            "//创建一个http请求器\n" +
            "var reqTest = new HttpRequest(basicUrl + \"user/loginToApp\",function(data,info){\n" +
            "                              api_log(data.msg);\n" +
            "                              })\n" +
            "\n" +
            "//创建一个基本的视图、\n" +
            "var view4testClick = new View();\n" +
            "view4testClick.backgroundColor = redColor;\n" +
            "mainVC.view.addSubView(view4testClick);\n" +
            "view4testClick.vrt_layout.topSpaceToView(list,15).centerXEqualToView(null).heightIs(30).widthEqualToHeight();\n" +
            "//为该视图增加一个点击事件的回调\n" +
            "view4testClick.addClick(view4testClick,function(){\n" +
            "                        reqTest.request({\"userPhone\":\"\",\"userPassword\":\"\"});\n" +
            "                        textField.text = \"OK\";\n" +
            "                        });\n" +
            "\n" +
            "//创建一个文本输入视图\n" +
            "var textField = new TextField();\n" +
            "textField.text = \"placeHolder\";\n" +
            "textField.backgroundColor = blackColor;\n" +
            "textField.textColor = whiteColor;\n" +
            "mainVC.view.addSubView(textField);\n" +
            "textField.vrt_layout.topSpaceToView(list,15).leftSpaceToView(view4testClick,10).heightIs(30).widthIs(100);\n" +
            "textField.addCallBackDidReturn(function(text){\n" +
            "                               api_log(\"user input text: \" + text);\n" +
            "                               });\n" +
            "//提交这个视图控制器\n" +
            "api_commitVC(mainVC);";
}
