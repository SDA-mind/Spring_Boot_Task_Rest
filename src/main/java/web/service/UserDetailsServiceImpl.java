package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.dao.dao;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final dao userDao;

    public UserDetailsServiceImpl(dao dao) {
        this.userDao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return (UserDetails) userDao.findById(s).get();
    }
}
