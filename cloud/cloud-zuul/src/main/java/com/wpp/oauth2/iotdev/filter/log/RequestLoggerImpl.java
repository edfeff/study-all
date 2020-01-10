package com.wpp.oauth2.iotdev.filter.log;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wangpp
 */
@Component
public class RequestLoggerImpl implements RequestLogger {
    @Override
    public void log(Map<String, Object> infoMap, RequestContext requestContext) {
        //日志记录 放入到infoMap中
        HttpServletRequest request = requestContext.getRequest();
        infoMap.put("RequestURI", request.getRequestURI());
        infoMap.put("RemoteAddr", request.getRemoteAddr());
    }
}
