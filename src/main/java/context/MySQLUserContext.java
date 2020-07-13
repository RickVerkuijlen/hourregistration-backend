package context;

import context.Interfaces.IContext;
import objects.UserDTO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MySQLUserContext implements IContext<UserDTO> {

    @Override
    public boolean delete(UUID entity) {
        return false;
    }

    @Override
    public boolean update(UserDTO entity) {
        return false;
    }

    @Override
    public boolean create(UserDTO entity) {
        return false;
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            result = session.createQuery("from UserDTO", UserDTO.class).list();
        }
        return result;
    }

    public UserDTO getById(int id) {
        UserDTO result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from UserDTO i where i.id = :user_id", UserDTO.class);
            query.setParameter("user_id", id);
            result = (UserDTO)query.uniqueResult();
        }
        return result;
    }
}
