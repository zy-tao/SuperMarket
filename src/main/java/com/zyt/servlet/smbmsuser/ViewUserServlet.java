package com.zyt.servlet.smbmsuser;

import com.zyt.entity.SmbmsUser;
import com.zyt.service.UserService;
import com.zyt.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: ViewUserServlet
 * @author: Tao_zy
 * @date: 2023/5/9
 **/
@WebServlet("/jsp/viewuser.do")
public class ViewUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String method = req.getParameter("method");
        UserService userService = new UserServiceImpl();
        SmbmsUser smbmsUser= userService.getSmbmsUserById(uid);
        req.getSession().setAttribute("user",smbmsUser);
        if (method.equals("view")) {
            resp.sendRedirect("userview.jsp");
        }else {
            resp.sendRedirect("usermodify.jsp");
        }
    }
}
