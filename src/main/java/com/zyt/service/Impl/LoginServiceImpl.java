package com.zyt.service.Impl;

import com.zyt.dao.JdbcTemplate;
import com.zyt.entity.SmbmsUser;

/**
 * @className: UserServiceImpl
 * @author: Tao_zy
 * @date: 2023/5/8
 **/
public class LoginServiceImpl implements com.zyt.service.LoginService {

    JdbcTemplate jdbcTemplate = new JdbcTemplate();


    public SmbmsUser getUserByUserCode(String userCode) {

        
        String sql = "select * from smbms_user where userCode = ?";
        SmbmsUser smbmsUser = jdbcTemplate.selectOne(SmbmsUser.class, sql, userCode);
        return smbmsUser;
    }
}
