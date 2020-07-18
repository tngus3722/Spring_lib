package Repository;

import Domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    public void signUp(User user);
}
