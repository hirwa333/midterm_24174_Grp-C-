package com.logistics.repository;

import com.logistics.entity.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellRepository extends JpaRepository<Cell, Long> {
    
    boolean existsByCellName(String cellName);
}
