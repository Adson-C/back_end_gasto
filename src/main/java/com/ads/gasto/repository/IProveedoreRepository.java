package com.ads.gasto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ads.gasto.model.ProveedoreModel;

public interface IProveedoreRepository extends JpaRepository<ProveedoreModel, Long> {
    
}
