package repositories;

import context.IProjectContext;
import org.springframework.stereotype.Component;

@Component
public class ProjectRepository {

    private IProjectContext projectContext;

    public String readProject() {
        return "Hello world";
    }
}
