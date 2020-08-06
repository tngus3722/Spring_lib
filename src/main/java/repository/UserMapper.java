package repository;

import domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    public void signUp(User user);
    public User getUserByName(String name);
}
