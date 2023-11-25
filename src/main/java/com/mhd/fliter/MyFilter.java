package com.mhd.fliter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author MouHongDa
 * @date 2023/11/25 16:51
 */
@WebFilter("/*")

public class MyFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        chain.doFilter(req, resp);//放行
    }


    public void destroy() {

    }
}