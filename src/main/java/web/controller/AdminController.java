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
    @Autowired
    private RoleService roleServiceImpl;

    @GetMapping(value = "/admin/userlist")
    public List<User> getList() {
        List<User> userList = userServiceImpl.allUsers();
        return userList;
    }

    @GetMapping(value = "/user/{name}")
    public User getUser(@PathVariable("name") String name) {
        User user = userServiceImpl.getByName(name);
        return user;
    }

    @PostMapping(value = "/admin/userlist")
    public User add(@RequestBody User user) {
        userServiceImpl.add(user);
        return user;
    }

    @PutMapping(value = "/admin/userlist/{name}")
    public User edit(@PathVariable("name") String name, @RequestBody User user) {
        userServiceImpl.edit(user, name);
        return user;
    }

    @DeleteMapping(value = "/admin/userlist/{name}")
    public User delete(@PathVariable("name") String name) {
        User user = userServiceImpl.getByName(name);
        userServiceImpl.delete(user);
        return user;
    }

//    @GetMapping(value = "/admin/editRoleAd/{name}")
//    public String setRoleAdmin(@PathVariable("name") String name) {
//        userService.addRole(name, 1L);
//        return "redirect:/admin/userlist";
//    }
//
//    @GetMapping(value = "/admin/editRoleAn/{name}")
//    public String setRoleAnnon(@PathVariable("name") String name) {
//        userService.addRole(name, 3L);
//        return "redirect:/admin/userlist";
//    }
//
//    @GetMapping(value = "/admin/deleteRoleAd/{name}")
//    public String deleteRoleAdmin(@PathVariable("name") String name) {
//        userService.deleteRole(name, 1L);
//        return "redirect:/admin/userlist";
//    }
//
//    @GetMapping(value = "/admin/deleteRoleAn/{name}")
//    public String deleteRoleAnnon(@PathVariable("name") String name) {
//        userService.deleteRole(name, 3L);
//        return "redirect:/admin/userlist";
//    }

}
