package com.test.app.Controller;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;
import java.util.WeakHashMap;

@ControllerAdvice
public class Response implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public APIResponse beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        APIResponse apiResponse = new APIResponse();
        apiResponse.response(body);
        return apiResponse;
    }
}
class RETCode extends Exception {
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    private Map<Integer, String> codemap = new WeakHashMap<Integer, String>();

    public Map<Integer, String> getCodemap() {
        return codemap;
    }

    public void setCodemap(Map<Integer, String> codemap) {
        this.codemap = codemap;
    }

    RETCode(Integer code) {
        super();
        this.code = code;
        codemap.put(0, "操作成功");
        codemap.put(107, "密码错误");
        codemap.put(106, "账户名或密码错误");
        codemap.put(100, "未知错误");
        codemap.put(101, "未登录");
        codemap.put(102, "数据库错误");
        codemap.put(103, "账户名已存在");
        codemap.put(104, "装备名称已存在");
        codemap.put(105, "没有权限");
        codemap.put(108, "单位名已存在");
    }

}
class APIResponse<T> {
    private Integer code;
    private String msg;
    private T body;
   private RETCode retCode;
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public void response (T ret) {
        if (ret instanceof Exception) {
            RETCode retCode = (RETCode)ret;
            setCode(retCode.getCode());
            setMsg(retCode.getCodemap().get(retCode.getCode()));
            setBody(null);
        } else {
            setMsg("操作成功");
            setCode(0);
            setBody(ret);
        }
    }

}