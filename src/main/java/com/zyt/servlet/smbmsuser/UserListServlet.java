package com.zyt.servlet.smbmsuser;

import com.zyt.entity.SmbmsUser;
import com.zyt.service.SmbmsRoleService;
import com.zyt.service.UserService;
import com.zyt.service.impl.SmbmsRoleServiceImpl;
import com.zyt.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @className: UserListServlet
 * @author: Tao_zy
 * @date: 2023/5/8
 **/
@WebServlet("/jsp/userlist.do")
public class UserListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受页面值

        //用户名和用户角色查询
        String queryname = req.getParameter("queryname");
        String queryUserRole = req.getParameter("queryUserRole");


        String pageIndex = req.getParameter("pageIndex");
        int currentPageNo = 1;
        if (pageIndex != null && !"".equals(pageIndex)) {
            currentPageNo = Integer.valueOf(pageIndex);
        }
        UserService userService = new UserServiceImpl();
        List<SmbmsUser> smbmsUserList = userService.getSmbmsUserByPage(currentPageNo, 5,queryname,queryUserRole);
        HttpSession httpSession = req.getSession();
        int totalCount = userService.getCountByPage(queryname,queryUserRole);
        SmbmsRoleService smbmsRoleService = new SmbmsRoleServiceImpl();
        List<SmbmsUser> smbmsRoleAll = smbmsRoleService.getSmbmsRoleAll();
        int totalPagecount = totalCount%5==0?totalCount/5:totalCount/5+1;
        httpSession.setAttribute("roleList",smbmsRoleAll);
        httpSession.setAttribute("userList",smbmsUserList);
        httpSession.setAttribute("totalPageCount",totalPagecount);
        httpSession.setAttribute("totalCount",totalCount);
        httpSession.setAttribute("currentPageNo",currentPageNo);
        //查询数据库

        //跳转页面
        resp.sendRedirect("userlist.jsp");
    }
}
