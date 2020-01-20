package ru.dragosh.tm.service;

import ru.dragosh.tm.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAll();
    List<Task> getAll(String projectId);
    Task getById(String projectId, String taskId);
    void persist(Task task);
    void merge(Task task);
    void deleteByTaskId(String id);
    void deleteByProjectId(String id);
    void deleteByProjectIdAndTaskId(String projectId, String taskId);
    Task getByProjectIdAndName(String projectId, String name);
}
