package com.malmap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malmap.api.model.Gps;

public interface GpsRepository extends JpaRepository<Gps, Long> {
    
}
