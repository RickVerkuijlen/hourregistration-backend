package context;

import context.Interfaces.IContext;
import objects.HourDTO;
import objects.ProjectDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import util.HibernateUtil;

import java.util.UUID;

@Component
public class MySQLHourContext implements IContext<HourDTO> {
    @Override
    public boolean delete(UUID entity) {
        return false;
    }

    @Override
    public boolean update(HourDTO entity) {
        return false;
    }

    @Override
    public boolean create(HourDTO entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            System.out.println("Create in context: " + entity.getProjectId());

            ProjectDTO projectDTO = session.get(ProjectDTO.class, entity.getProjectId());
            projectDTO.setWorkedHours(projectDTO.getWorkedHours() + entity.getWorkedHours());

            session.saveOrUpdate(projectDTO);

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
}
