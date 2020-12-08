package com.atguigu.controller;

import com.atguigu.pojo.Book;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/manager/bookServlet")
public class BookServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();
    /**
     * 列表查询
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求参数，如果有
        //2 调用bookService.QueryBooks()查询全部图书
        List<Book> books = bookService.queryBooks();
        //3 保存到Request域中
        req.setAttribute("books",books);
        //4 请求转发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
    /**
     * 添加
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数，封装成为Bean对象。Book
        Book book = WebUtils.copyParamToBean(new Book(),req.getParameterMap());
        //2 调用BookService.addBook(book);
        bookService.addBook(book);
//        //3 重定向 到图书列表管理页面/book/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list");

//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);

    }


    /**
     * 删除
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数商品编号，转换为int
        Integer id = WebUtils.parseInt(req.getParameter("id"),0);
        //2 调用bookService.deleteBookById(id);
        bookService.deleteBookById(id);
        //3 重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath()
                + "/manager/bookServlet?action=list");
    }



    /**
     * update
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1 获取请求的参数 ， 封装成为book对象
        Book book = WebUtils.copyParamToBean(new Book(),req.getParameterMap());
//        2 调用bookService.updateBook(book);修改图书
        bookService.updateBook(book);
//        3 重定向回图书列表管理页面
//                /book/managaer/bookServlet?action=list
        resp.sendRedirect(req.getContextPath()
                + "/manager/bookServlet?action=list");
    }




    /**
     * 根据id查询需要的图书信息
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 编号
        Integer id = WebUtils.parseInt(req.getParameter( "id"),0);
        //2 调用bookService.queryBookById(id);
        Book book = bookService.queryBookById(id);
        //3 保存图书到reqeust域中
        req.setAttribute("book",book);
        //4 请求转发到、pages/manager/book_edit.jsp页面、
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }





}
