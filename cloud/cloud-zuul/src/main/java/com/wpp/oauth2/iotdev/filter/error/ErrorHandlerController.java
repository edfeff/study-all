package com.wpp.oauth2.iotdev.filter.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author wangpp
 */
@RestController
public class ErrorHandlerController implements ErrorController {
    @Autowired
    ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping( "/error" )
    public ResponseData error(HttpServletRequest request) {
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(new ServletWebRequest(request), true);
        String message = (String) errorAttributes.get("message");
        String trace = (String) errorAttributes.get("trace");
        return new ResponseData(500, message + " : " + trace, null);
    }
}
