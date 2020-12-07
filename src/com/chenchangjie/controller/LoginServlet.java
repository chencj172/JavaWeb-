package com.chenchangjie.controller;

import com.chenchangjie.entity.Admin;
import com.chenchangjie.entity.Reader;
import com.chenchangjie.service.BookService;
import com.chenchangjie.service.LoginService;
import com.chenchangjie.service.impl.BookServiceImpl;
import com.chenchangjie.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static LoginService loginService = new LoginServiceImpl();
    private BookService bookService = new BookServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String type = req.getParameter("type");
        //数据交给LoginService进行逻辑处理
        Object object = loginService.login(username,password,type);
        if(object == null){
            resp.sendRedirect("/login.jsp");   //登录失败
        }else{
            //有数据，登录成功,检查是用户还是管理员
            HttpSession session = req.getSession();
            switch (type){
                case "reader":
                    Reader reader = (Reader) object;
                    session.setAttribute("reader",reader);
                    resp.sendRedirect("/book?page=1");
                    /**   解耦合
                    //跳转到读者的首页
                    List<Book> list = bookService.FindAll(1);
                    req.setAttribute("list",list);
                    //每页显示数据条数就是list的size
                    req.setAttribute("dataPrePage",list.size());
                    //显示第一页
                    req.setAttribute("currentPage",1);
                    //总页数
                    req.setAttribute("pages",bookService.count());
                    //跳转
                    req.getRequestDispatcher("/index.jsp").forward(req,resp);*/
                    break;
                case "admin":
                    Admin admin = (Admin) object;
                    session.setAttribute("admin",admin);
                    //跳转到管理员的首页
                    resp.sendRedirect("/admin?page=1");
                    break;
            }
        }
    }
}
