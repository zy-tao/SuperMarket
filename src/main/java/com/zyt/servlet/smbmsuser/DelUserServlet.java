package com.zyt.servlet.smbmsuser;

import com.alibaba.fastjson2.JSONObject;
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
 * @className: DelUserServlet
 * @author: Tao_zy
 * @date: 2023/5/9
 **/
@WebServlet("/jsp/deluser.do")
public class DelUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收页面值
        String uid = req.getParameter("uid");
        //操作数据库
        UserService userService = new UserServiceImpl();
        userService.delSmbmsUserById(uid);
        //跳转
        HashMap hashMap = new HashMap();
        hashMap.put("delResult","true");
        String jsonStr = JSONObject.toJSONString(hashMap);
        resp.getWriter().write(jsonStr);

    }
}
