package ru.dragosh.tm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.entity.Task;
import ru.dragosh.tm.repository.TaskRepository;

import java.util.List;

@Component
public class TaskServiceImplement implements TaskService {
    @Qualifier("getTaskRepository")
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAll() {
        return taskRepository.getAll();
    }

    @Override
    public List<Task> getAll(String projectId) {
        return taskRepository.getAll(projectId);
    }

    @Override
    public Task getById(String projectId, String taskId) {
        if (projectId == null || projectId.isEmpty())
            return null;
        if (taskId == null || taskId.isEmpty())
            return null;
        return taskRepository.getById(projectId, taskId);
    }

    @Override
    public void persist(Task task) {
        if (task == null)
            return;
        taskRepository.persist(task);
    }

    @Override
    public void merge(Task task) {
        if (task == null)
            return;
        taskRepository.merge(task);
    }

    @Override
    public void deleteByTaskId(String id) {
        if (id == null || id.isEmpty())
            return;
        taskRepository.deleteByTaskId(id);
    }

    @Override
    public void deleteByProjectId(String id) {
        if (id == null || id.isEmpty())
            return;
        taskRepository.deleteByProjectId(id);
    }

    @Override
    public void deleteByProjectIdAndTaskId(String projectId, String taskId) {
        taskRepository.deleteByProjectIdAndTaskId(projectId, taskId);
    }

    @Override
    public Task getByProjectIdAndName(String projectId, String name) {
        return taskRepository.getByProjectIdAndName(projectId, name);
    }

}
