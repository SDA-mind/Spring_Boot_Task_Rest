package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.model.Role;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    public List<Role> allRoles() {
        return roleDao.allRoles();
    }

    public void add(Role role) {
        roleDao.add(role);
    }

    public void delete(Role role) {
        roleDao.delete(role);
    }

    public void edit(Role role) {
        roleDao.edit(role);
    }

    public Role getById(Long id) {
        return roleDao.getById(id);
    }
}