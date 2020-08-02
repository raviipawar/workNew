package com.myapp.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <h1>CorsFilterConfiguration</h1>
 * <p>
 * This class is responsible for Cross-origin resource sharing
 * </p>
 *
 * @author Shanawaz
 * @author Ravindra Pawar
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilterConfiguration implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Nothing to intialize
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with,authorization,content-type");
        if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) request).getMethod())) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        //Nothing to clean up
    }
}
