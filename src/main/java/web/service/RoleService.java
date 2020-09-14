package web.service;

import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;

import java.util.List;

@Component
public interface RoleService {
    List<Role> allRoles();

    void add(Role role);

    void delete(Role role);

    void edit(Role role);

    Role getById(Long id);

    void roleSet(User user);
}
