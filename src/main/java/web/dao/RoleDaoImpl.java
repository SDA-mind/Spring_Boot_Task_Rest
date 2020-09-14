package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
}
