package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import java.util.List;

@Repository
public interface RoleDao {
    List<Role> allRoles();

    void add(Role role);

    void delete(Role role);

    void edit(Role role);

    Role getById(Long id);
}
