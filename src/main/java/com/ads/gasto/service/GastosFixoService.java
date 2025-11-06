package com.ads.gasto.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ads.gasto.model.GastosFixoModel;

public interface GastosFixoService {

    public List<GastosFixoModel> listar();

    public Page<GastosFixoModel> listarPaginado(Pageable pageable);

    public GastosFixoModel guardar(GastosFixoModel gastoFixo);

    public GastosFixoModel buscarPorId(Long id);

    public void eliminar(Long id);

    // lista por mes e ano
    public List<GastosFixoModel> listarPorMes(Integer mes, Integer ano);

    // lista por mes e ano com paginação
    public Page<GastosFixoModel> listarPorMesPaginado(Integer mes, Integer ano, Pageable pageable);
}
