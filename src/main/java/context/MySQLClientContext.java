package context;

import context.Interfaces.IClientContext;
import objects.ClientDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateUtil;

import java.util.UUID;

@Component
public class MySQLClientContext implements IClientContext {
    @Override
    public ClientDTO getById(int id) {
        ClientDTO result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from ClientDTO c where c.id = :client_id", ClientDTO.class);
            query.setParameter("client_id", id);
            result = (ClientDTO)query.uniqueResult();
        }
        return result;
    }

    @Override
    public boolean delete(UUID entity) {
        return false;
    }

    @Override
    public boolean update(ClientDTO entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(entity);

            transaction.commit();

            return true;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }

    @Override
    public int create(ClientDTO entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(entity);

            transaction.commit();

            return entity.getId();
        } catch (Exception e ) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return 0;
    }
}
