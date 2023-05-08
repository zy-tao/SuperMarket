package com.zyt.service.impl;

import com.zyt.dao.JdbcTemplate;
import com.zyt.entity.SmbmsRole;
import com.zyt.entity.SmbmsUser;
import com.zyt.service.SmbmsRoleService;

import java.util.List;

/**
 * @className: SmbmsRoleServiceImpl
 * @author: Tao_zy
 * @date: 2023/5/8
 **/
public class SmbmsRoleServiceImpl implements SmbmsRoleService {

    @Override
    public List<SmbmsUser> getSmbmsRoleAll() {
        String sql = "select * from smbms_role";
        return new JdbcTemplate().selectList(SmbmsRole.class,sql);
    }
}
