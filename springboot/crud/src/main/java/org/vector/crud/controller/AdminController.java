package org.vector.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vector.crud.pojo.Admin;
import org.vector.crud.service.AdminService;
import org.vector.crud.util.Sha1HashUtil;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/save/{adminName}/{password}")
    public String add(@PathVariable("adminName") String adminName, @PathVariable("password") String password) {

        if (adminName != null) {
            Admin admin1 = adminService.findAdmin(adminName);
            if (admin1 != null) {
                return "exit";
            }else {
                Admin admin = new Admin();
                admin.setAdminName(adminName);
                admin.setPassword(Sha1HashUtil.toSha1Hash(password));
                adminService.addAdmin(admin);
                return "ok";
            }
        }else {
            return "no";
        }
    }


    @PostMapping("/find/{adminName}/{password}")
    public String find(@PathVariable("adminName") String adminName, @PathVariable("password") String password) {
        Admin admin = adminService.findAdmin(adminName, Sha1HashUtil.toSha1Hash(password));
        if (admin != null) {
            return "good";
        }
        return "no";
    }

    //注销
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        /*session.removeAttribute("admin");*/
        /*Map<String,String> list = new HashMap<>();
        list.put("url","/main");
        list.put("message","ok");
        list.put("code","500");*/
        return "/main";
    }


}
