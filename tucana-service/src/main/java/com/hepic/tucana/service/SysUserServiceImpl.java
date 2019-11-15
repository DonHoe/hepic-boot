package com.hepic.tucana.service;

import com.hepic.tucana.dal.dao.SysUserDao;
import com.hepic.tucana.model.shiro.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务实现类
 */
@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserDao sysUserDao;

    @Override
    public List<User> selectSysUserListByModel(User model) {
        return sysUserDao.selectSysUserListByModel(model);
    }

    @Override
    public User selectSysUserById(Long id) {
        return sysUserDao.selectSysUserById(id);
    }

    @Override
    public int insertSysUser(User model) {
        return sysUserDao.insertSysUser(model);
    }

    @Override
    public int updateSysUser(User model) {
        return sysUserDao.updateSysUser(model);
    }
}