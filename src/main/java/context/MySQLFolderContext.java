package context;

import objects.Folder;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

@Component
public class MySQLFolderContext {

    public List<Folder> getAllFolders() {
        List<Folder> result = new ArrayList<>();

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            result = session.createQuery("from Folder", Folder.class).list();
        }

        return result;
    }
}
