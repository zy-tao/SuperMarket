package com.zyt.service;

import com.zyt.entity.SmbmsUser;

/**
 * @className: UserService
 * @author: Tao_zy
 * @date: 2023/5/8
 **/
public interface LoginService {
    public SmbmsUser getUserByUserCode (String userCode);
}
