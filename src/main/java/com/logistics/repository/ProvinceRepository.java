package com.logistics.repository;

import com.logistics.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    
    boolean existsByProvinceCode(String provinceCode);
    
    Province findByProvinceCode(String provinceCode);
}
