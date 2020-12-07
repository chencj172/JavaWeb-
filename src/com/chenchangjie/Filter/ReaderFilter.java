package com.chenchangjie.Filter;


import com.chenchangjie.entity.Reader;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 防止访问非登录页面时出现服务器内部错误
 */
@WebFilter("/book")
public class ReaderFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
        Reader reader = (Reader) session.getAttribute("reader");
        if(reader == null){
            ((HttpServletResponse) servletResponse).sendRedirect("/login.jsp");
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
