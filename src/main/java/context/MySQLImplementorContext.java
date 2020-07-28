package context;

import context.Interfaces.IImplementorContext;
import objects.ImplementorDTO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MySQLImplementorContext implements IImplementorContext {

    @Override
    public List<ImplementorDTO> getAllImplementors() {
        List<ImplementorDTO> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            result = session.createQuery("from ImplementorDTO ORDER BY name ASC", ImplementorDTO.class).list();
        }
        return result;
    }

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
    public int create(ImplementorDTO entity) {
        return 0;
    }
}
