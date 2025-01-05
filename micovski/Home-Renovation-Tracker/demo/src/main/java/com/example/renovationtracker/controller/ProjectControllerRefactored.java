package com.example.renovationtracker.controller;

import com.example.renovationtracker.model.Project;
import com.example.renovationtracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectControllerRefactored extends GenericController<Project, Long> {
    @Autowired
    private ProjectService projectService;

    @Override
    protected Project createEntity(Project project) {
        return projectService.createProject(project);
    }

    @Override
    protected List<Project> getAllEntities() {
        return projectService.getAllProjects();
    }

    @Override
    protected Optional<Project> getEntityById(Long id) {
        return projectService.getProjectById(id);
    }

    @Override
    protected Project updateEntity(Long id, Project project) {
        return projectService.updateProject(id, project);
    }

    @Override
    protected void deleteEntity(Long id) {
        projectService.deleteProject(id);
    }
}
