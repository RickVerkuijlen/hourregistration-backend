package repositories;

import context.MySQLUserContext;
import objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository {

    @Autowired
    private MySQLUserContext userContext;

    public List<User> allUsers() {
        return userContext.getAllUsers();
    }

    public User getById(int id) {
        return userContext.getById(id);
    }
}
