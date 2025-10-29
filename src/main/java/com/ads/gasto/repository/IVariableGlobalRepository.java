package com.ads.gasto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ads.gasto.model.VariableGlobalModel;

public interface IVariableGlobalRepository extends JpaRepository<VariableGlobalModel, Long> {
    
}
