package context;

import context.Interfaces.IProjectContext;
import lombok.extern.slf4j.Slf4j;
import objects.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class MySQLProjectContext extends CRUD<Project> implements IProjectContext {

    @Override
    public List<Project> getAllProjects() {
        List<Project> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            result = session.createQuery("from Project", Project.class).list();
        }
        return result;
    }
}
