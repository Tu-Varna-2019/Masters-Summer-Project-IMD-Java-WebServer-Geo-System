package com.tuvarna.geo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tuvarna.geo.entity.Soil;

@Repository
public interface SoilRepository extends JpaRepository<Soil, Integer> {

    Soil findByLongitudeAndLatitude(long longitude, long latitude);
}
