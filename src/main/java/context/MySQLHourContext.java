package context;

import context.Interfaces.IContext;
import objects.HourDTO;
import objects.ProjectDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateUtil;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

            ProjectDTO projectDTO = session.get(ProjectDTO.class, entity.getProjectId());
            projectDTO.setWorkedHours(projectDTO.getWorkedHours() + entity.getWorkedHours());
//            projectDTO.setLastModified(LocalDateTime.now());


            System.out.println(LocalDateTime.now());

            session.update(projectDTO);

            session.saveOrUpdate(checkForHour(entity, session));

            transaction.commit();

            return true;
        } catch (Exception e ) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }

    private HourDTO checkForHour(HourDTO entity, Session session) {
        HourDTO result;
        Query<HourDTO> query = session.createQuery("from HourDTO h where h.projectId = :projectId and h.userId = :userId and h.date = :date", HourDTO.class);
        query.setParameter("projectId", entity.getProjectId());
        query.setParameter("userId", entity.getUserId());
        query.setParameter("date", entity.getDate());
        result = query.uniqueResult();

        if(result != null) {
            result.setWorkedHours(result.getWorkedHours() + entity.getWorkedHours());
            return result;
        } else {
            return entity;
        }
    }

    public List<HourDTO> getAllFromMonth(int month, int year) {
        List<HourDTO> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<HourDTO> query = session.createQuery("from HourDTO h where MONTH(h.date) = :month and YEAR(h.date) = :year", HourDTO.class);
            query.setParameter("month", month);
            query.setParameter("year", year);
            result = query.getResultList();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return result;
    }

    public List<HourDTO> getAll() {
        List<HourDTO> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<HourDTO> query = session.createQuery("from HourDTO", HourDTO.class);
            result = query.getResultList();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return result;
    }
}
