package com.zyt.servlet;

import com.zyt.entity.SmbmsUser;
import com.zyt.service.Impl.LoginServiceImpl;
import com.zyt.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: LoginServlet
 * @author: Tao_zy
 * @date: 2023/5/8
 **/
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取前一页传递的参数
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

        //操作数据库
        /**
         * 通过userCode查询用户，返回一个用户，对比密码是否正确
         * 面向接口变成，将操作数据库放在service中，service中有接口和实现类
         *  所以在service中写一个接口 LoginService,并有一个方法 getUserByUserCode
         */
        LoginService loginService = new LoginServiceImpl();
        SmbmsUser user = loginService.getUserByUserCode(userCode);

        if (user != null) {
            if (userPassword.equals(user.getUserPassword())) {
                resp.sendRedirect("jsp/frame.jsp");
                return ;
            }
        }
        //页面跳转
        resp.sendRedirect("jsp/login.jsp");
    }
}
