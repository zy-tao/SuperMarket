package com.zyt.servlet;

import com.zyt.dao.JdbcTemplate;
import com.zyt.entity.SmbmsUser;
import com.zyt.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @className: LoginServlet
 * @author: Tao_zy
 * @date: 2023/5/8
 **/
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {

    UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收页面传值
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

        //调用service 操作数据库
        SmbmsUser user = userService.getUserByUserCode(userCode);
        if (user != null) {
            if (userPassword.equals(user.getUserPassword())) {

                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("userSessionS",user);

                //页面跳转
                resp.sendRedirect("jsp/frame.jsp");
                return ;
            }
        }
        
        resp.sendRedirect("jsp/login.jsp");
    }
}
