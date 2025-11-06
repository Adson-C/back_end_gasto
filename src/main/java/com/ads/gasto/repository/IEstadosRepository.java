package com.ads.gasto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ads.gasto.model.EstadosModel;

public interface IEstadosRepository extends JpaRepository<EstadosModel, Long> {

}
