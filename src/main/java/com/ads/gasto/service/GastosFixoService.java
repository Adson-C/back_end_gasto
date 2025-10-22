package com.ads.gasto.service;

import java.util.List;

import com.ads.gasto.model.GastosFixoModel;

public interface GastosFixoService {

    public List<GastosFixoModel> listar();

    public GastosFixoModel guardar(GastosFixoModel gastoFixo);

    public GastosFixoModel buscarPorId(Long id);

    public void eliminar(Long id);
}
