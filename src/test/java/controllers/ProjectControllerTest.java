package controllers;

import context.IProjectContext;
import mocks.ProjectContextMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.ProjectRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProjectControllerTest {
    private IProjectContext projectContext;
    private ProjectController projectController;
    private ProjectRepository projectRepository;

    @BeforeEach
    void setUp() {
        this.projectContext = new ProjectContextMock();
        this.projectRepository = new ProjectRepository(projectContext);
        this.projectController = new ProjectController(projectRepository);
    }

    @Test
    void getAllProject_AllProjects_size() {
        int expected = 3;
        int actual = projectController.getAllProjects().getBody().size();

        assertEquals(expected, actual);
    }
}
