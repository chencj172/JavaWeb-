package com.chenchangjie.Filter;

import com.chenchangjie.entity.Admin;
import com.chenchangjie.entity.Reader;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin == null){
            ((HttpServletResponse) servletResponse).sendRedirect("/login.jsp");
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
