package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.User;
@Repository
public interface dao extends JpaRepository<User,String> {
}
