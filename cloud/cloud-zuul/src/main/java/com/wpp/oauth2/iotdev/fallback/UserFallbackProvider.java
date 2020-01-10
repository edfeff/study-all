package com.wpp.oauth2.iotdev.fallback;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.context.RequestContext;
import com.wpp.oauth2.iotdev.filter.error.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wangpp
 */
@Component
public class UserFallbackProvider implements FallbackProvider {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String getRoute() {
        return "cloud-eureka-user";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return httpHeaders;
            }

            @Override
            public InputStream getBody() throws IOException {
                RequestContext requestContext = RequestContext.getCurrentContext();
                ResponseData responseData = new ResponseData(100, "User服务暂时不可访问", cause.getMessage());
                String s = objectMapper.writeValueAsString(responseData);
                return new ByteArrayInputStream(s.getBytes());
            }

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {
            }
        };
    }
}
