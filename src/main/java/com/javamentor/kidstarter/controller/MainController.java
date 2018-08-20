package com.javamentor.kidstarter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {

    @RequestMapping("/main")
    public String showMain() {
        return "main";
    }

    @RequestMapping("/cretejobtag")
    public String showCreateJobTag() {
        return "cretejobtag";
    }

    @RequestMapping("/adminpage")
    public String showAdminPage() {
        return "adminpage";
    }

    @RequestMapping("/useredit")
    public String showUserEdit() {
        return "useredit";
    }

    @RequestMapping("/createUser")
    public String createUserPage() {
        return "createUser";
    }
}