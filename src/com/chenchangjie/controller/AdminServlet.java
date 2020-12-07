package com.chenchangjie.controller;

import com.chenchangjie.entity.Admin;
import com.chenchangjie.entity.Borrow;
import com.chenchangjie.service.BookService;
import com.chenchangjie.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private BookService bookService = new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method == null){
            method = "findAllBorrowByState";
        }
        switch (method){
            case "findAllBorrowByState":
                String pageStr = req.getParameter("page");
                Integer page = Integer.valueOf(pageStr);
                List<Borrow> list = bookService.findAllBorrowByState(page,0);
                req.setAttribute("list",list);
                req.setAttribute("dataPrePage",list.size());
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getBorrowPagesByState(0));
                req.getRequestDispatcher("/admin.jsp").forward(req,resp);
                break;
            case  "handle":
                HttpSession session = req.getSession();
                Admin admin = (Admin) session.getAttribute("admin");
                String borrowIdStr = req.getParameter("borrowId");
                String stateStr = req.getParameter("state");
                Integer borrowId = Integer.parseInt(borrowIdStr);
                Integer state = Integer.parseInt(stateStr);
                bookService.handleBorrow(borrowId,admin.getId(),state);
                if(state == 3)
                    resp.sendRedirect("/admin?method=getBorrowed&page=1");
                else if(state == 1 || state == 2)
                    resp.sendRedirect("/admin?page=1");
                break;
            case "getBorrowed":
                pageStr = req.getParameter("page");
                page = Integer.valueOf(pageStr);
                List<Borrow> list1 = bookService.findAllBorrowByState(page,1);
                req.setAttribute("list",list1);
                req.setAttribute("dataPrePage",list1.size());
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getBorrowPagesByState(1));
                req.getRequestDispatcher("/return.jsp").forward(req,resp);
                break;
        }
    }
}
