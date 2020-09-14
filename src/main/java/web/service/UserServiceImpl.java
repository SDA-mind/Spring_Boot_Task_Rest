package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @Override
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public void edit(User user,String editName) {
        userDao.edit(user,editName);
    }

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public void addRole(String name, Long id) {
        User user = getByName(name);
        user.getRoles().add(roleServiceImpl.getById(id));
        edit(user,user.getUsername());
    }

    @Override
    public void deleteRole(String name, Long id) {
        User user = getByName(name);
        user.getRoles().remove(roleServiceImpl.getById(id));
        edit(user,user.getUsername());
    }
}
