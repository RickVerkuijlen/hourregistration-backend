package context.MySQL;

import context.IProjectContext;
import objects.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import util.HibernateUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MySQLProjectContext implements IProjectContext {

    private Connection con;

    public MySQLProjectContext() {

    }

    @Override
    public boolean delete(UUID entity) {
        return false;
    }

    @Override
    public boolean update(Project entity) {
        return false;
    }

    @Override
    public boolean create(Project entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(entity);

            transaction.commit();

            return true;
        } catch (Exception e ) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return false;
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
