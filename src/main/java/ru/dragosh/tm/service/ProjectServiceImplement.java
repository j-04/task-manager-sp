package ru.dragosh.tm.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.entity.Project;
import ru.dragosh.tm.repository.ProjectRepository;

import java.util.List;

@Component
public class ProjectServiceImplement implements ProjectService {
    @Qualifier("getProjectRepository")
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> getAll() {
        return projectRepository.getAll();
    }

    @Override
    public Project getById(@NotNull final String id) {
        if (id == null)
            return null;
        return projectRepository.getById(id);
    }

    @Override
    public Project getByName(String name) {
        return projectRepository.getByName(name);
    }

    @Override
    public void persist(Project project) {
        if (project == null)
            return;
        projectRepository.persist(project);
    }

    @Override
    public void merge(Project project) {
        if (project == null)
            return;
        projectRepository.merge(project);
    }

    @Override
    public void delete(String id) {
        projectRepository.delete(id);
    }
}
