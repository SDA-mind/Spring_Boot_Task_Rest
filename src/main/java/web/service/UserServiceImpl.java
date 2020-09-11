package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @Override
    public List<User> allUsers() {
        return userDao.findAll();
    }

    @Override
    public void add(User user) {
        user.setRoles(Collections.singleton(roleServiceImpl.getById(2L)));
        userDao.save(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public void edit(User user,String id) {
        entityManager.createNativeQuery("update users set first_name= :firstname, last_name= :lastname," +
                "date= :date, name= :name, password= :pass where name= :id")
                .setParameter("id",id)
                .setParameter("firstname",user.getFirstName())
                .setParameter("lastname",user.getLastName())
                .setParameter("date",user.getDate())
                .setParameter("name",user.getUsername())
                .setParameter("pass",user.getPassword())
                .executeUpdate();
    }

    @Override
    public User getByName(String name) {
        return userDao.findById(name).get();
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
