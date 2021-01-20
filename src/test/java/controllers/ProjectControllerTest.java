package controllers;

import objects.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repositories.ProjectRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

class ProjectControllerTest {

    @InjectMocks
    ProjectController projectController;

    @Mock
    ProjectRepository projectRepository;

    List<Project> projects;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        this.projects = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            this.projects.add(Project.builder().build());
        }
    }

    @Test
    void getAllProject_AllProjects_size() {
        when(projectRepository.allProjects()).thenReturn(this.projects);

        int expected = 3;
        int actual = projectController.getAllProjects().getBody().size();

        assertEquals(expected, actual);
    }

    @Test
    void addProject_true() {
        when(projectRepository.createProject(anyObject())).thenReturn(true);

        boolean actual = projectController.createProject(Project.builder().build()).getBody();

        assertTrue(actual);
    }

    @Test
    void deleteProject_true() {
        when(projectRepository.deleteProject(anyObject())).thenReturn(true);

        boolean actual = projectController.deleteProject(Project.builder().build()).getBody();

        assertTrue(actual);
    }
}
