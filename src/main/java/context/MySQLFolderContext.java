package context;

import objects.FolderDTO;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

@Component
public class MySQLFolderContext {

    public List<FolderDTO> getAllFolders() {
        List<FolderDTO> result = new ArrayList<>();

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            result = session.createQuery("from FolderDTO", FolderDTO.class).list();
        }

        return result;
    }
}
