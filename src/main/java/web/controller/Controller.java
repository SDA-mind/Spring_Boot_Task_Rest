package web.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping(value = "/admin")
    public String getPageAdmin() {
        return "admin";
    }

    @GetMapping(value = "/user")
    public String getPageUser() {
        return "user";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }
}
