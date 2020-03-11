package context;

import context.Interfaces.IImplementorContext;
import objects.ImplementorDTO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateUtil;

import java.util.UUID;

@Component
public class MySQLImplementorContext implements IImplementorContext {
    @Override
    public ImplementorDTO getById(int id) {
        ImplementorDTO result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from ImplementorDTO i where i.id = :implementor_id", ImplementorDTO.class);
            query.setParameter("implementor_id", id);
            result = (ImplementorDTO)query.uniqueResult();
        }
        return result;
    }

    @Override
    public boolean delete(UUID entity) {
        return false;
    }

    @Override
    public boolean update(ImplementorDTO entity) {
        return false;
    }

    @Override
    public boolean create(ImplementorDTO entity) {
        return false;
    }
}
