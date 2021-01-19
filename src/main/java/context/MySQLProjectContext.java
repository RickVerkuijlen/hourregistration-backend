package context;

import context.Interfaces.IProjectContext;
import objects.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MySQLProjectContext implements IProjectContext {

    @Override
    public boolean delete(UUID entity) {
        return false;
    }

    @Override
    public boolean update(Project entity) {
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
    public int create(Project entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(entity);

            transaction.commit();

            return 1;
        } catch (Exception e ) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return 0;
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            result = session.createQuery("from Project", Project.class).list();
        }
        return result;
    }
}
