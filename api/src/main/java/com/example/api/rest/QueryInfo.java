package com.example.api.rest;

import lombok.Data;

/**
 * @author wangpp
 */
@Data
public class QueryInfo {
    private String query;
    private Integer pagenum;
    private Integer pagesize;
}
