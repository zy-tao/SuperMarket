package com.zyt.servlet;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.zyt.entity.SmbmsUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/jsp/validatePwd.do")
/**
 * @className: ValidatePwdServlet
 * @author: Tao_zy
 * @date: 2023/5/8
 **/
public class ValidatePwdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受页面的值
        //axjax向浏览器输出内容
        HashMap resultMap = new HashMap();

        String oldPassword = req.getParameter("oldpassword");
        //判断密码  和 谁对比
        //session
        HttpSession httpSession = req.getSession();
        Object userObj = httpSession.getAttribute("userSessionS");
        System.out.println(userObj);
        if (userObj != null) {
            SmbmsUser loginSmbmsUser = (SmbmsUser) userObj;
            System.out.println(loginSmbmsUser.getUserPassword());
            if (oldPassword.equals(loginSmbmsUser.getUserPassword())) {
                resultMap.put("result","true");
            }else {
                resultMap.put("result","false");
            }
        }


        String jsonString = JSONObject.toJSONString(resultMap);
        resp.getWriter().write(jsonString);
    }
}
