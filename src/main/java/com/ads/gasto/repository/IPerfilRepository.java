package com.ads.gasto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ads.gasto.model.PerfilModel;

public interface IPerfilRepository extends JpaRepository<PerfilModel, Long> {
    
}
