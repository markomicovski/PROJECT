package com.example.renovationtracker.service;

import com.example.renovationtracker.model.Project;
import com.example.renovationtracker.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project updateProject(Long id, Project updatedProject) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setName(updatedProject.getName());
                    project.setDescription(updatedProject.getDescription());
                    project.setStartDate(updatedProject.getStartDate());
                    project.setEndDate(updatedProject.getEndDate());
                    project.setBudget(updatedProject.getBudget());
                    project.setContractor(updatedProject.getContractor());
                    project.setStatus(updatedProject.getStatus());
                    return projectRepository.save(project);
                })
                .orElseGet(() -> {
                    updatedProject.setId(id);
                    return projectRepository.save(updatedProject);
                });
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
