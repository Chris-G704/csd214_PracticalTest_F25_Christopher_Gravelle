package com.test.repository;

import java.util.List;
import java.util.Optional;

/**
 * Generic Repository interface for CRUD operations
 * @param <T> The entity type
 */
public interface Repository<T> {

    /**
     * Find an entity by its ID
     * @param id The ID to search for
     * @return An Optional containing the entity if found
     */
    Optional<T> findById(Long id);

    /**
     * Find all entities
     * @return A list of all entities
     */
    List<T> findAll();

    /**
     * Save an entity (create or update)
     * @param entity The entity to save
     * @return The saved entity
     */
    T save(T entity);

    /**
     * Delete an entity by its ID
     * @param id The ID of the entity to delete
     */
    void deleteById(Long id);
}