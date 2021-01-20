package context;

import context.Interfaces.IContext;
import objects.Hour;
import objects.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class MySQLHourContext implements IContext<Hour> {
    @Override
    public boolean delete(Hour entity) {
        return false;
    }

    @Override
    public boolean update(Hour entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            entity.setId(getHourId(entity));

            System.out.println(LocalDateTime.now());

            session.saveOrUpdate(entity);

            transaction.commit();

            transaction = session.beginTransaction();

            Project project = session.get(Project.class, entity.getProjectId());

            project.setWorkedHours(calculateUpdateHours(project.getCode()));

            session.update(project);

            transaction.commit();

            return true;
        } catch (Exception e ) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }

    private int getHourId(Hour entity) {
        Hour result;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Hour> query = session.createQuery("from Hour h where h.projectId = :projectId and h.userId = :userId and h.date = :date", Hour.class);
            query.setParameter("projectId", entity.getProjectId());
            query.setParameter("userId", entity.getUserId());
            query.setParameter("date", entity.getDate());
            result = query.uniqueResult();
        }

        return result != null ? result.getId() : 0;

    }

    @Override
    public int create(Hour entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Project project = session.get(Project.class, entity.getProjectId());
            project.setWorkedHours(project.getWorkedHours() + entity.getWorkedHours());

            System.out.println(LocalDateTime.now());

            session.update(project);

            session.saveOrUpdate(checkForHour(entity, session));

            transaction.commit();

            return entity.getId();
        } catch (Exception e ) {
            if(transaction != null) {
                transaction.rollback();
            }
        }
        return 0;
    }

    private float calculateUpdateHours(String projectId) {
        float result = 0;
        List<Hour> hours = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Hour> query = session.createQuery("from Hour h where h.projectId = :projectId", Hour.class);
            query.setParameter("projectId", projectId);
            hours = query.list();
        }

        if(hours != null || !hours.isEmpty()) {
            for (Hour hour : hours) {
                result += hour.getWorkedHours();
            }
        }

        return result;
    }

    private Hour checkForHour(Hour entity, Session session) {
        Hour result;
        Query<Hour> query = session.createQuery("from Hour h where h.projectId = :projectId and h.userId = :userId and h.date = :date", Hour.class);
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

    public List<Hour> getAllFromMonth(int month, int year) {
        List<Hour> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Hour> query = session.createQuery("from Hour h where MONTH(h.date) = :month and YEAR(h.date) = :year", Hour.class);
            query.setParameter("month", month);
            query.setParameter("year", year);
            result = query.getResultList();
        } catch (Exception e) {
            log.error(e.toString());
        }
        return result;
    }

    public List<Hour> getAllFromWeek(Date start, Date end) {
        List<Hour> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Hour WHERE date >= date(:startDate) AND date <= date(:endDate)");
            query.setParameter("startDate", start);
            query.setParameter("endDate", end);
            result = query.getResultList();
        } catch (Exception e) {
            log.error(e.toString());
        }
        return result;
    }
}
