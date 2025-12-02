package com.test.repository;

import com.test.entities.VehicleEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

public class MySQLVehicleRepository implements Repository<VehicleEntity> {

    private final EntityManagerFactory emf;

    public MySQLVehicleRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Optional<VehicleEntity> findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            VehicleEntity vehicle = em.find(VehicleEntity.class, id);
            return Optional.ofNullable(vehicle);
        } finally {
            em.close();
        }
    }

    @Override
    public List<VehicleEntity> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT v FROM VehicleEntity v", VehicleEntity.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public VehicleEntity save(VehicleEntity entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            if (entity.getId() == null) {
                // New entity - persist it
                em.persist(entity);
            } else {
                // Existing entity - merge it
                entity = em.merge(entity);
            }

            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            VehicleEntity vehicle = em.find(VehicleEntity.class, id);
            if (vehicle != null) {
                em.remove(vehicle);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}