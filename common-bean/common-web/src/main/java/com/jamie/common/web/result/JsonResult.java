package com.jamie.common.web.result;

public class JsonResult extends ResultBase {
    public static JsonResult success(){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStatus(SUCCESS);
        return jsonResult;
    }

    public static JsonResult success(Object data){
        JsonResult jsonResult = success();
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult fail(String msg){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setMessage(msg);
        return jsonResult;
    }
}
