package com.logistics.repository;

import com.logistics.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    
    boolean existsByDistrictName(String districtName);
}
