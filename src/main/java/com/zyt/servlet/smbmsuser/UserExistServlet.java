package com.zyt.servlet.smbmsuser;

import com.alibaba.fastjson2.JSONObject;
import com.zyt.entity.SmbmsUser;
import com.zyt.service.UserService;
import com.zyt.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @className: UserExistServlet
 * @author: Tao_zy
 * @date: 2023/5/9
 **/
@WebServlet("/jsp/userexist.do")
public class UserExistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userCode = req.getParameter("userCode");
        UserService userService = new UserServiceImpl();
        SmbmsUser smbmsUser = userService.getUserByUserCode(userCode);
        HashMap hashMap = new HashMap();
        if (smbmsUser == null) {
            hashMap.put("userCode","noexist");
        }else {
            hashMap.put("userCode","exist");
        }
        String jsonStr = JSONObject.toJSONString(hashMap);
        resp.getWriter().write(jsonStr);
    }
}
