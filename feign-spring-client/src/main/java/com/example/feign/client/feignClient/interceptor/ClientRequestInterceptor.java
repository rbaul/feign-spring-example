package com.example.feign.client.feignClient.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@Component
public class ClientRequestInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Nullable
    @Autowired
    private HttpServletRequest request;

    @Override
    public void apply(RequestTemplate template) {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
        log.info(">>> interceptor");

        if(Objects.nonNull(request)) {
            String requestId = request.getHeader(AUTHORIZATION_HEADER);
            if (!StringUtils.isEmpty(requestId)) {
                template.header(AUTHORIZATION_HEADER, requestId);
            }
        }
    }
}
