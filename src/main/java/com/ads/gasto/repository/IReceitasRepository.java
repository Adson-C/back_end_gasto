package com.ads.gasto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ads.gasto.model.ReceitasModel;

public interface IReceitasRepository extends JpaRepository<ReceitasModel, Long> {
    List<ReceitasModel> findAll(org.springframework.data.domain.Sort sort);

}
