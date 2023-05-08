package com.zyt.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @className: LoginOutServlet
 * @author: Tao_zy
 * @date: 2023/5/8
 **/
@WebServlet("/jsp/logout.do")
public class LoginOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
//        httpSession.removeAttribute("userSessionS");
        httpSession.invalidate();//invalidate使作废、无效
        resp.sendRedirect("login.jsp");
    }
}
