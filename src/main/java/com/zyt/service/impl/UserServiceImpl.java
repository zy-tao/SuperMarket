package com.zyt.service.impl;

import com.zyt.dao.JdbcTemplate;
import com.zyt.entity.SmbmsRole;
import com.zyt.entity.SmbmsUser;
import com.zyt.service.UserService;

import java.util.List;

/**
 * @className: UserServiceImpl
 * @author: Tao_zy
 * @date: 2023/5/8
 **/
public class UserServiceImpl implements UserService {

    /**
     * 在 service 中的操作：
     *  组织 sql 语句
     *  调用 dao 数据库操作
     */
    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    //按照用户名查询用户
    public SmbmsUser getUserByUserCode(String userCode) {

        String sql = "SELECT * FROM smbms_user WHERE userCode = ?";
        SmbmsUser smbmsUser = jdbcTemplate.selectOne(SmbmsUser.class, sql, userCode);
        return smbmsUser;
    }

    @Override
    public List<SmbmsUser> getSmbmsUserByPage(int currentPage, int pageSize,String queryname,String queryUserRole) {
        // 页面上有一个
        int startRow = (currentPage - 1)*pageSize;

        //用户角色  数据库 层处理
        //String sql = "SELECT su.*,YEAR(NOW())-YEAR(su.birthday) age,s.roleName AS userRoleName FROM smbms_role s LEFT JOIN smbms_user su ON s.id = su.`userRole` LIMIT ?,?";

        //用户角色，Java 层处理
        String sql = "SELECT su.*,(YEAR(NOW())-YEAR(su.birthday)) AS age FROM `smbms_user` su WHERE 1=1";
        int flag = 0;
        if (queryname != null && !queryname.equals("")) {
            sql += " and su.userName like concat('%',?,'%') ";
            flag = 1;
        }
        if (queryUserRole != null && !queryUserRole.equals("0")) {
            sql += " and su.userRole = ? ";
            if (flag == 0) {
                flag = 2;
            }else {
                flag = 3;
            }
        }
        sql= sql +" limit ?,?";
        System.out.println(sql);
        //用户角色，Java 层处理
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        List<SmbmsUser> smbmsUserList =null;
        if(flag==0){
            smbmsUserList=  jdbcTemplate.selectList(SmbmsUser.class,sql,startRow,pageSize);
        }
        if(flag==1){
            smbmsUserList=  jdbcTemplate.selectList(SmbmsUser.class,sql,queryname,startRow,pageSize);
        }
        if(flag==2){
            smbmsUserList=  jdbcTemplate.selectList(SmbmsUser.class,sql,queryUserRole,startRow,pageSize);
        }
        if(flag==3){
            smbmsUserList=  jdbcTemplate.selectList(SmbmsUser.class,sql,queryname,queryUserRole,startRow,pageSize);
        }

        for (SmbmsUser smbmsUser:smbmsUserList) {
            String sql1 = "select * from smbms_role where id = ?";
            SmbmsRole smbmsRole = jdbcTemplate.selectOne(SmbmsRole.class, sql1, smbmsUser.getUserRole());
            smbmsUser.setUserRoleName(smbmsRole.getRoleName());
        }
        return smbmsUserList;
    }

    @Override
    public int getCountByPage(String queryname, String queryUserRole) {

        //用户角色  数据库 层处理
        //String sql = "SELECT su.*,YEAR(NOW())-YEAR(su.birthday) age,s.roleName AS userRoleName FROM smbms_role s LEFT JOIN smbms_user su ON s.id = su.`userRole` LIMIT ?,?";

        //用户角色，Java 层处理
        String sql = "SELECT count(1) FROM `smbms_user` su WHERE 1=1";
        int flag = 0;
        if (queryname != null && !queryname.equals("")) {
            sql += " and su.userName like concat('%',?,'%') ";
            flag = 1;
        }
        if (queryUserRole != null && !queryUserRole.equals("0")) {
            sql += " and su.userRole = ? ";
            if (flag == 0) {
                flag = 2;
            }else {
                flag = 3;
            }
        }
        System.out.println(sql);
        //用户角色，Java 层处理
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        int smbmsUserListCount = 0;
        if(flag==0){
            smbmsUserListCount=  jdbcTemplate.selectCount(sql);
        }
        if(flag==1){
            smbmsUserListCount=  jdbcTemplate.selectCount(sql,queryname);
        }
        if(flag==2){
            smbmsUserListCount=  jdbcTemplate.selectCount(sql,queryUserRole);
        }
        if(flag==3){
            smbmsUserListCount=  jdbcTemplate.selectCount(sql,queryname,queryUserRole);
        }
        return smbmsUserListCount;
    }

    @Override
    public void delSmbmsUserById(String id) {
        String sql = "delete from smbms_user where id = ?";
        jdbcTemplate.executeUpdate(sql,id);
    }

    @Override
    public SmbmsUser getSmbmsUserById(String uid) {
        String sql = "select * from smbms_user where id = ?";
        SmbmsUser smbmsUser = jdbcTemplate.selectOne(SmbmsUser.class, sql, uid);
        String sqlRole = "select * from smbms_role where id = ?";
        SmbmsRole smbmsRole = jdbcTemplate.selectOne(SmbmsRole.class, sqlRole, smbmsUser.getUserRole());
        smbmsUser.setUserRoleName(smbmsRole.getRoleName());
        return smbmsUser;
    }

    public void addUser(SmbmsUser smbmsUser) {
        String sql = "insert into smbms_user (userCode," +
                "userName," +
                "userPassword," +
                "gender," +
                "birthday," +
                "phone," +
                "address," +
                "userRole) values (?,?,?,?,?,?,?,?)";
        jdbcTemplate.executeUpdate(sql,smbmsUser.getUserCode(),
                smbmsUser.getUserName(),
                smbmsUser.getUserPassword(),
                smbmsUser.getGender(),
                smbmsUser.getBirthday(),
                smbmsUser.getPhone(),
                smbmsUser.getAddress(),
                smbmsUser.getUserRole());
    }
}
