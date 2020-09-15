package org.vector.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vector.crud.dao.AdminDao;
import org.vector.crud.pojo.Admin;

import javax.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Transactional
    @Override
    public Admin addAdmin(Admin admin) {
        return adminDao.save(admin);
    }

    @Override
    public Admin findAdmin(String adminName, String password) {
        return adminDao.findByAdminNameAndPassword(adminName, password);
    }

    @Override
    public Admin findAdmin(String adminName) {
        return adminDao.findByAdminName(adminName);
    }

}
