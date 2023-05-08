package com.zyt.servlet;

import com.zyt.entity.SmbmsUser;
import com.zyt.service.UpdPwdService;
import com.zyt.service.impl.UpdPwdServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @className: UpdPwdServlet
 * @author: Tao_zy
 * @date: 2023/5/8
 **/
@WebServlet("/jsp/updPwd.do")
public class UpdPwdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取页面密码
        String newPassWord = req.getParameter("newpassword");

        //调用
        UpdPwdService updPwdService = new UpdPwdServiceImpl();
        HttpSession httpSession = req.getSession();
        Object userObj = httpSession.getAttribute("userSessionS");
        if (userObj != null) {
            SmbmsUser loginSmbmsUser = (SmbmsUser) userObj;
            //设置新密码
            loginSmbmsUser.setUserPassword(newPassWord);
            updPwdService.updPwd(loginSmbmsUser);

            resp.sendRedirect("pwdmodify.jsp");
        }
    }
}
