package com.ads.gasto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ads.gasto.model.EstadosModel;

public interface IEstadosRepository extends JpaRepository<EstadosModel, Long> {

}
