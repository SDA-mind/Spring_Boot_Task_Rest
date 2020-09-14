package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;

@Repository
public interface UserDao {
    List<User> allUsers();

    void add(User user);

    void delete(User user);

    void edit(User user,String id);

    User getById(int id);

    User getByName(String name);
}
