package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.User;
import web.service.service;

@Controller
public class UserController {
    @Autowired
    service userService;

    @GetMapping(value = "/")
    public String getHomePage() {
        return "redirect:/user";
    }

    @GetMapping(value = "/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping(value = "/user")
    public String userInfo(Model model, Authentication authentication) {
        if (authentication.isAuthenticated()) {
            User user = (User)authentication.getPrincipal();
            model.addAttribute("User", user);
        } else {
            model.addAttribute("User", new User());
        }
        return "user";
    }

}
