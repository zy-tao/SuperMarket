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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className: AddUserServlet
 * @author: Tao_zy
 * @date: 2023/5/9
 **/
@WebServlet("/jsp/adduser.do")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //1.获取页面的数据
        String userCode = req.getParameter("userCode");
        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPassword");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String userRole = req.getParameter("userRole");

        UserService userService = new UserServiceImpl();
        SmbmsUser smbmsUser = new SmbmsUser();
        smbmsUser.setUserCode(userCode);

        smbmsUser.setUserName(userName);
        smbmsUser.setUserPassword(userPassword);
        smbmsUser.setGender(Integer.valueOf(gender));
        smbmsUser.setPhone(phone);
        smbmsUser.setAddress(address);
        smbmsUser.setUserRole(Integer.valueOf(userRole));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = simpleDateFormat.parse(birthday);
            smbmsUser.setBirthday(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        userService.addUser(smbmsUser);

        resp.sendRedirect("userlist.do");
    }
}
