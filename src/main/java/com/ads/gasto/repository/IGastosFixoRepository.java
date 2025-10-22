package com.ads.gasto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ads.gasto.model.GastosFixoModel;

public interface IGastosFixoRepository extends JpaRepository<GastosFixoModel, Long> {
    
}
