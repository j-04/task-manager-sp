package ru.dragosh.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dragosh.tm.entity.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class ProjectRepositoryImplement implements ProjectRepository {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<Project> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select p from Project p");
        return (List<Project>) query.getResultList();
    }

    @Override
    public Project getById(@NotNull final String id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select p from Project p where p.id = :id");
        query.setParameter("id", id);
        return (Project) query.getSingleResult();
    }

    @Override
    public Project getByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select p from Project p where p.name = :name");
        query.setParameter("name", name);
        return (Project) query.getSingleResult();
    }

    @Override
    public void persist(@NotNull final Project project) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(project);
        entityManager.getTransaction().commit();
    }

    @Override
    public void merge(@NotNull final Project project) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(project);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(@NotNull final String id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("delete from Project p where p.id = :id");
        query.setParameter("id", id);
        entityManager.getTransaction().begin();
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }
}
