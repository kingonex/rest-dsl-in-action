package org.jboss.fuse;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CORSFilter implements Filter {
    
    static final Logger LOG = LoggerFactory.getLogger(CORSFilter.class);

    public static final String ACCESS_CONTROL_ALLOW_ORIGIN_HEADER = "Access-Control-Allow-Origin";
    public static final String ACCESS_CONTROL_ALLOW_METHODS_HEADER = "Access-Control-Allow-Methods";
    public static final String ACCESS_CONTROL_ALLOW_HEADERS_HEADER = "Access-Control-Allow-Headers";

    public CORSFilter() {}

    public void init(FilterConfig fConfig) throws ServletException {
        LOG.info("CORSFilter initialized");
    }

    public void destroy() {}

    public void doFilter(
            ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
        LOG.info("Filter called");
        
        HttpServletRequest req = (HttpServletRequest)request;
        StringBuffer urlBuffer = req.getRequestURL();

        LOG.info("HTTP URL : " + urlBuffer.toString());

        HttpServletResponse reply = (HttpServletResponse) response;
        reply.addHeader(ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        reply.addHeader(ACCESS_CONTROL_ALLOW_METHODS_HEADER, "GET, POST, DELETE, PUT, PATCH, OPTIONS");
        reply.addHeader(ACCESS_CONTROL_ALLOW_HEADERS_HEADER, "X-Requested-With,api_key,Content-Type,Accept,Origin");

        LOG.info("Added : " + ACCESS_CONTROL_ALLOW_ORIGIN_HEADER + ": " + "*");
        LOG.info("Added : " + ACCESS_CONTROL_ALLOW_METHODS_HEADER + ": " + "GET, POST, DELETE, PUT, PATCH, OPTIONS");
        LOG.info("Added : " + ACCESS_CONTROL_ALLOW_HEADERS_HEADER + ": " + "X-Requested-With,Content-Type,api_key,Accept,Origin");
        
        chain.doFilter(request, reply);
    }
}