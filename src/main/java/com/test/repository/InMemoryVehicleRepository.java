package com.test.repository;

import com.test.entities.VehicleEntity;
import java.util.*;

public class InMemoryVehicleRepository implements Repository<VehicleEntity> {

    private final Map<Long, VehicleEntity> storage = new HashMap<>();
    private long nextId = 1;

    @Override
    public Optional<VehicleEntity> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<VehicleEntity> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public VehicleEntity save(VehicleEntity entity) {
        if (entity.getId() == null) {
            // New entity - assign ID
            entity.setId(nextId++);
        }
        // Store in HashMap
        storage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}

