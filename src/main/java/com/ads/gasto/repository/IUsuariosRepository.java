package com.ads.gasto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ads.gasto.model.UsuariosModel;

public interface IUsuariosRepository extends JpaRepository<UsuariosModel, Long> {
    
}
