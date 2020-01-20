package ru.dragosh.tm.repository;

import ru.dragosh.tm.entity.Project;

import java.util.List;

public interface ProjectRepository {
    List<Project> getAll();
    Project getById(String id);
    Project getByName(String name);
    void persist(Project project);
    void merge(Project project);
    void delete(String id);
}
