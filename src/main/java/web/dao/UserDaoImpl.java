package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    public void add(User user) {
        roleDao.roleSet(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(entityManager.merge(user));
    }

    @Override
    public void edit(User user,String id) {
        roleDao.roleSet(user);
        entityManager.merge(user);
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
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getByName(String name) {
        return entityManager.find(User.class, name);
    }
}
