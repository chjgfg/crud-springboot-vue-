package org.vector.crud.service;


import org.vector.crud.pojo.Admin;

public interface AdminService {

    Admin addAdmin(Admin admin);

    Admin findAdmin(String adminname, String password);

    Admin findAdmin(String adminname);
}
