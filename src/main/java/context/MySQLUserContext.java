package context;

import context.Interfaces.IContext;
import lombok.extern.slf4j.Slf4j;
import objects.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class MySQLUserContext extends CRUD<User> implements IContext<User> {

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            result = session.createQuery("from User", User.class).list();
        }
        return result;
    }

    public User getById(int id) {
        User result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from User i where i.id = :user_id", User.class);
            query.setParameter("user_id", id);
            result = (User)query.uniqueResult();
        }
        return result;
    }
}
