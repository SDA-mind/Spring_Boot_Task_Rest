package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.dao;
import web.model.User;

import java.util.Collections;
import java.util.List;
@Service
@Transactional
public class UserService implements service {
    @Autowired
    dao userDao;
    @Autowired
    RoleService roleService;
    @Override
    public List<User> allUsers() {
        return userDao.findAll();
    }

    @Override
    public void add(User user) {
        user.setRoles(Collections.singleton(roleService.getById(2L)));
        userDao.save(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public void edit(User user) {

        userDao.save(user);
    }

    @Override
    public User getByName(String name) {
        return userDao.findById(name).get();
    }

    @Override
    public void addRole(String name, Long id) {
        User user = getByName(name);
        user.getRoles().add(roleService.getById(id));
        edit(user);
    }

    @Override
    public void deleteRole(String name, Long id) {
        User user = getByName(name);
        user.getRoles().remove(roleService.getById(id));
        edit(user);
    }
}
