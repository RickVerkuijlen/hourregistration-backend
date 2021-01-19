package mocks;

import context.Interfaces.IProjectContext;
import objects.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectContextMock implements IProjectContext {
    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());
        projects.add(new Project());
        projects.add(new Project());
        return projects;
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
    public int create(Project entity) {
        return 0;
    }
}
