package com.logistics.repository;

import com.logistics.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
    
    boolean existsBySectorName(String sectorName);
}
