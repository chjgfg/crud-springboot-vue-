package org.vector.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vector.crud.pojo.Admin;

public interface AdminDao extends JpaRepository<Admin, Integer> {

    Admin findByAdminNameAndPassword(String adminName, String password);

    Admin findByAdminName(String adminName);


}
