package com.ads.gasto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ads.gasto.model.GastosFixoModel;

public interface IGastosFixoRepository extends JpaRepository<GastosFixoModel, Long> {

    // HQL
    @Query(
        value = "SELECT E FROM GastosFixoModel E WHERE FUNCTION('MONTH', E.fecha) = :mes AND FUNCTION('YEAR', E.fecha) = :ano ORDER BY E.id DESC"
    )
    List<GastosFixoModel> findByAllMonth(@Param("mes") Integer mes, @Param("ano") Integer ano);

}
