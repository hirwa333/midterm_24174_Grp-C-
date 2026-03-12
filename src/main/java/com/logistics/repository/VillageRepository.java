package com.logistics.repository;

import com.logistics.entity.Village;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VillageRepository extends JpaRepository<Village, Long> {
    
    boolean existsByVillageName(String villageName);
}
