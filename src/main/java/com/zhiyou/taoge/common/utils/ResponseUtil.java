package com.zhiyou.taoge.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.zhiyou.taoge.common.vo.Result;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

@Slf4j
public class ResponseUtil<T> {

    /**
     * 使用response输出JSON
     */
    public static void out(ServletResponse response, Result result) {

        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSONObject.toJSON(result));
        } catch (Exception e) {
            log.error(e + "输出JSON出错");
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result();
        result.setSuccess(true);
        result.setCode(200);
        result.setMessage("success");
        result.setTimestamp(System.currentTimeMillis());
        result.setResult(data);
        return result;
    }

    public static <T> Result<T> ok() {
        Result<T> result = new Result();
        result.setSuccess(true);
        result.setCode(200);
        result.setMessage("success");
        result.setTimestamp(System.currentTimeMillis());
        return result;
    }

    public static <T> Result<T> createResult(boolean flag, Integer code, String msg) {
        Result<T> result = new Result();
        result.setSuccess(flag);
        result.setCode(code);
        result.setMessage(msg);
        result.setTimestamp(System.currentTimeMillis());
        return result;
    }

    public static <T> Result<T> createResult(boolean flag, Integer code, String msg, T data) {
        Result<T> result = new Result();
        result.setSuccess(flag);
        result.setCode(code);
        result.setMessage(msg);
        result.setTimestamp(System.currentTimeMillis());
        result.setResult(data);
        return result;
    }
}
