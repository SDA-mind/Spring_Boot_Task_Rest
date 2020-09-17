package web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.User;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

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

    @GetMapping("/login/google")
    public String welcome(Principal principal) {
//        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
//        Authentication authentication = oAuth2Authentication.getUserAuthentication();
//        Map<String, String> details = new LinkedHashMap<>();
//        details = (Map<String, String>) authentication.getDetails();
//        User googUser = new User();
//        map.put("email", details.get("email"));

        return "redirect:/admin";
    }
}
