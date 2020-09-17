package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    public List<Role> allRoles() {
        return entityManager.createQuery("from roles", Role.class)
                .getResultList();
    }

    public void add(Role role) {
        entityManager.persist(role);
    }

    public void delete(Role role) {
        entityManager.remove(entityManager.merge(role));
    }

    public void edit(Role role) {
        entityManager.merge(role);
    }

    public Role getById(Long id) {
        return entityManager.find(Role.class, id);
    }
    @Override
    public void roleSet(User user) {
        Set<Role> tempSet = new HashSet<Role>();
        System.out.println(user.getRoles());
        for (Role role : user.getRoles()) {
            switch (role.getRole()) {
                case "ROLE_ADMIN": {
                    tempSet.add(getById(1L));
                    break;
                }
                case "ROLE_USER": {
                    tempSet.add(getById(2L));
                    break;
                }
                case "ROLE_ANNON": {
                    tempSet.add(getById(3L));
                    break;
                }
            }
        }
        user.setRoles(tempSet);
        entityManager.merge(user);
    }
}
