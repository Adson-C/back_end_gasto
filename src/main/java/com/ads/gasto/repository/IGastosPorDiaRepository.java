package com.ads.gasto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ads.gasto.model.GastosPorDiaModel;

public interface IGastosPorDiaRepository extends JpaRepository<GastosPorDiaModel, Long> {
    
}
