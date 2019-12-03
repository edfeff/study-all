package com.wpp.security.distributed.uaa.mode.filter;

import java.util.Enumeration;

public interface WorkFilterConfig {

    String getFilterName();

    String getInitParameter(String var1);

    Enumeration<String> getInitParameterNames();
}
