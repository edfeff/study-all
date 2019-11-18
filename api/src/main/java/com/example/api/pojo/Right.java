package com.example.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author wangpp
 */
@Data
@AllArgsConstructor
public class Right {
    private Integer id;
    private String authName;
    private Integer level;
    private String path;
    private String pid;
}
