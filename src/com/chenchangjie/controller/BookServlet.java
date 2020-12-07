package com.chenchangjie.controller;

import com.chenchangjie.entity.Book;
import com.chenchangjie.entity.Borrow;
import com.chenchangjie.entity.Reader;
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

@WebServlet("/book")
public class BookServlet extends HttpServlet {

    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        HttpSession session = req.getSession();
        Reader reader = (Reader) session.getAttribute("reader");
        if(method == null){
            method = "findAll";
        }
        switch (method){
            case "findAll":
                String pageStr = req.getParameter("page");
                Integer page = Integer.valueOf(pageStr);
                List<Book> list = bookService.FindAll(page);
                req.setAttribute("list",list);
                req.setAttribute("dataPrePage",list.size());
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getPages());
                req.getRequestDispatcher("/index.jsp").forward(req,resp);
                break;
            case "borrow":
                int bookId = Integer.valueOf(req.getParameter("bookid"));
                bookService.Borrow(bookId,reader.getId());
                resp.sendRedirect("/book?method=findAll&page=1");
                break;
            case "findAllBorrow":
                pageStr = req.getParameter("page");
                page = Integer.valueOf(pageStr);
                List<Borrow>list1 = bookService.ShowBorrowBookByReadId(reader.getId(),page);
                req.setAttribute("list",list1);
                req.setAttribute("dataPrePage",list1.size());
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getBorrowBooksPages(reader.getId()));
                req.getRequestDispatcher("/borrow.jsp").forward(req,resp);
                break;
        }
    }
}
