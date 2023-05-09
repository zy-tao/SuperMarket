package com.zyt.service;

import com.zyt.entity.SmbmsUser;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: UserService
 * @author: Tao_zy
 * @date: 2023/5/8
 **/
public interface UserService {
    public SmbmsUser getUserByUserCode(String userCode);
    public List<SmbmsUser> getSmbmsUserByPage(int currentPage,int size,String queryname,String queryUserRole);

    public int getCountByPage(String queryname,String queryUserRole);

    public void delSmbmsUserById (String id);

    public SmbmsUser getSmbmsUserById (String id);

    public void addUser(SmbmsUser smbmsUser);
}
