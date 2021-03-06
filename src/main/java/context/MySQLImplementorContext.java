package context;

import context.Interfaces.IImplementorContext;
import objects.Implementor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

@Component
public class MySQLImplementorContext extends CRUD<Implementor> implements IImplementorContext {

    @Override
    public List<Implementor> getAllImplementors() {
        List<Implementor> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            result = session.createQuery("from Implementor ORDER BY name ASC", Implementor.class).list();
        }
        return result;
    }

    @Override
    public Implementor getById(int id) {
        Implementor result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Implementor i where i.id = :implementor_id", Implementor.class);
            query.setParameter("implementor_id", id);
            result = (Implementor)query.uniqueResult();
        }
        return result;
    }
}
