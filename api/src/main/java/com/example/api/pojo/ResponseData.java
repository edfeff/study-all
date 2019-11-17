package com.example.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wangpp
 */
@Data
public class ResponseData {
    @Data
    @AllArgsConstructor
    static class Meta {
        private Integer status;
        private String msg;
    }

    private Meta meta;

    private Object data;

    public static ResponseData success() {
        return success(null);
    }

    public static ResponseData fail() {
        return fail(null);
    }

    public static ResponseData success(Object data) {
        ResponseData result = new ResponseData();
        result.setMeta(new Meta(200, "ok"));
        result.setData(data);
        return result;
    }

    public static ResponseData fail(Object data) {
        ResponseData result = new ResponseData();
        result.setMeta(new Meta(-100, "fail"));
        result.setData(data);
        return result;
    }

}
