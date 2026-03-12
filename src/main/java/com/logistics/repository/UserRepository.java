package com.logistics.repository;

import com.logistics.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("SELECT u FROM User u WHERE u.village.cell.sector.district.province.provinceCode = :provinceCode")
    List<User> findUsersByProvinceCode(@Param("provinceCode") String provinceCode);
    
    @Query("SELECT u FROM User u WHERE u.village.cell.sector.district.province.provinceName = :provinceName")
    List<User> findUsersByProvinceName(@Param("provinceName") String provinceName);
    
    Page<User> findAll(Pageable pageable);
    
    boolean existsByUsername(String username);
}
