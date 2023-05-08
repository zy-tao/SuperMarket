package com.zyt.service.impl;

import com.zyt.dao.JdbcTemplate;
import com.zyt.entity.SmbmsUser;
import com.zyt.service.UpdPwdService;
import com.zyt.service.UserService;

/**
 * @className: UpdPwdServiceImpl
 * @author: Tao_zy
 * @date: 2023/5/8
 **/
public class UpdPwdServiceImpl implements UpdPwdService {


    @Override
    public void updPwd(SmbmsUser smbmsUser) {

        String sql = "update smbms_user " +
                "set userPassword = ?" +
                "where userCode = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.executeUpdate(sql,smbmsUser.getUserPassword(),smbmsUser.getUserCode());
    }
}
