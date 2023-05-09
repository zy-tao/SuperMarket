package com.zyt.servlet.smbmsuser;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.zyt.entity.SmbmsUser;
import com.zyt.service.SmbmsRoleService;
import com.zyt.service.impl.SmbmsRoleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @className: GetRoleListServlet
 * @author: Tao_zy
 * @date: 2023/5/9
 **/
@WebServlet("/jsp/getrolelist.do")
public class GetRoleListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("utf-8");
        SmbmsRoleService smbmsRoleService = new SmbmsRoleServiceImpl();
        List<SmbmsUser> smbmsRoleAll = smbmsRoleService.getSmbmsRoleAll();
        String jsonStr = JSONArray.toJSONString(smbmsRoleAll);
        resp.getWriter().write(jsonStr);
    }
}
