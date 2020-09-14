package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.RoleService;
import web.service.RoleServiceImpl;
import web.service.UserService;
import web.service.UserServiceImpl;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private UserService userServiceImpl;

    @GetMapping(value = "/admin/userlist")
    public List<User> getList() {
        return userServiceImpl.allUsers();
    }

    @GetMapping(value = "/user/{name}")
    public User getUser(@PathVariable("name") String name) {
        return userServiceImpl.getByName(name);
    }

    @PostMapping(value = "/admin/userlist")
    public User add(@RequestBody User user) {
        userServiceImpl.add(user);
        return user;
    }

    @PutMapping(value = "/admin/userlist/{name}")
    public User edit(@PathVariable("name") String editName, @RequestBody User user) {
       userServiceImpl.edit(user, editName);
       return user;
    }

    @DeleteMapping(value = "/admin/userlist/{name}")
    public User delete(@PathVariable("name") String name) {
        User user = userServiceImpl.getByName(name);
        userServiceImpl.delete(user);
        return user;
    }

}
