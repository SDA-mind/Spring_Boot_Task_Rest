package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.daoRole;
import web.model.Role;

import java.util.List;

@Service
@Transactional
public class RoleService {
    @Autowired
    daoRole roleDao;

    public List<Role> allRoles() {
        return roleDao.findAll();
    }

    public void add(Role role) {
        roleDao.save(role);
    }

    public void delete(Role role) {
        roleDao.delete(role);
    }

    public void edit(Role role) {
        roleDao.save(role);
    }

    public Role getById(Long id) {
        return roleDao.findById(id).get();
    }
}