package ru.dragosh.tm.service;

import ru.dragosh.tm.entity.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getAll();
    Project getById(String id);
    Project getByName(String name);
    void persist(Project project);
    void merge(Project project);
    void delete(String id);
}
