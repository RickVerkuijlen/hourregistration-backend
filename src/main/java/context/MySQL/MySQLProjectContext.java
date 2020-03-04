package context.MySQL;

import context.IProjectContext;
import objects.Project;
import org.springframework.stereotype.Component;

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
        return false;
    }

    @Override
    public boolean create(Project entity) {
        return false;
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());
        projects.add(new Project());
        projects.add(new Project());
        return projects;
    }
}
