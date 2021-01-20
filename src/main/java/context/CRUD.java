package context;

import context.Interfaces.IContext;
import lombok.extern.slf4j.Slf4j;
import objects.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

@Slf4j
public class CRUD<T> implements IContext<T> {
    public boolean delete(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(entity);

            transaction.commit();
            return true;
        } catch (Exception e) {
            log.error(e.toString());
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }

    @Override
    public boolean update(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(entity);

            transaction.commit();

            return true;
        } catch (Exception e) {
            log.error(e.toString());
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }

    @Override
    public int create(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(entity);

            transaction.commit();

            return 1;
        } catch (Exception e ) {
            log.error(e.toString());
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return 0;
    }
}
