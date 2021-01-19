package context;

import context.Interfaces.IClientContext;
import objects.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MySQLClientContext implements IClientContext {
    @Override
    public Client getById(int id) {
        Client result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Client c where c.id = :client_id", Client.class);
            query.setParameter("client_id", id);
            result = (Client)query.uniqueResult();
        }
        return result;
    }

    @Override
    public List<Client> getAll() {
        List<Client> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            result = session.createQuery("from Client", Client.class).list();
        }
        return result;
    }

    @Override
    public boolean delete(UUID entity) {
        return false;
    }

    @Override
    public boolean update(Client entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(entity);

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
    public int create(Client entity) {
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
