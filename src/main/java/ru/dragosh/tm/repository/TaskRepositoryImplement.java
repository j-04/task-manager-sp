package ru.dragosh.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.entity.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Component
public class TaskRepositoryImplement implements TaskRepository {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<Task> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select t from Task t");
        return (List<Task>) query.getResultList();
    }

    @Override
    public List<Task> getAll(@NotNull final String projectId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select t from Task t where t.id = :id");
        query.setParameter("id", projectId);
        return (List<Task>) query.getResultList();
    }

    @Override
    public Task getById(@NotNull final String projectId, @NotNull final String taskId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select t from Task t where t.projectId = :projectId and t.id = :taskId");
        return (Task) query.getSingleResult();
    }

    @Override
    public void persist(@NotNull final Task task) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(task);
        entityManager.getTransaction().commit();
    }

    @Override
    public void merge(@NotNull final Task task) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(task);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteByTaskId(String id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("delete from Task t where t.id = :id");
        query.setParameter("id", id);
        entityManager.getTransaction().begin();
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteByProjectId(String id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("delete from Task t where t.projectId = :id");
        query.setParameter("id", id);
        entityManager.getTransaction().begin();
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteByProjectIdAndTaskId(String projectId, String taskId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("delete from Task t where t.projectId = :projectId and t.id = :taskId");
        query.setParameter("projectId", projectId);
        query.setParameter("taskId", taskId);
        entityManager.getTransaction().begin();
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public Task getByProjectIdAndName(String projectId, String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select t from Task t where t.projectId = :id and t.name = :name");
        query.setParameter("id", projectId);
        query.setParameter("name", name);
        return (Task) query.getSingleResult();
    }
}
