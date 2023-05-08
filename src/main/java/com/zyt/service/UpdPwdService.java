package com.zyt.service;

import com.zyt.entity.SmbmsUser;

/**
 * @className: UpdPwdService
 * @author: Tao_zy
 * @date: 2023/5/8
 **/
public interface UpdPwdService {
    //根据用户userCode  更行userPwd
    public void updPwd(SmbmsUser smbmsUser);
}
