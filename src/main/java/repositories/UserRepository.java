package repositories;

import context.MySQLUserContext;
import objects.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository {

    @Autowired
    private MySQLUserContext userContext;

    public List<UserDTO> allUsers() {
        return userContext.getAllUsers();
    }

    public UserDTO getById(int id) {
        return userContext.getById(id);
    }
}
