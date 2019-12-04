package com.wpp.oauth2.iotdev.filter.error;

import lombok.Data;

/**
 * @author wangpp
 */
@Data
public class ResponseData {
    private Integer code;
    private String message;
    private Object data;

    public ResponseData(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
