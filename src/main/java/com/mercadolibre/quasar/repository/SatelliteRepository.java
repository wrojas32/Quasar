package com.mercadolibre.quasar.repository;

import com.mercadolibre.quasar.domain.SatelliteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SatelliteRepository extends CrudRepository<SatelliteEntity, String> {
    List<SatelliteEntity> findAll();
}
