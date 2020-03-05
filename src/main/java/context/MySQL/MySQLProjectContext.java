package context.MySQL;

import context.IProjectContext;
import objects.Project;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MySQLProjectContext implements IProjectContext {

    private Connection con;

    public MySQLProjectContext() {
        con = ConnectionManager.getConnection();
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
        return false;
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> result = new ArrayList<>();

        return result;
    }
}
