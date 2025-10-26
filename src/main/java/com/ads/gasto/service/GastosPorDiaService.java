package com.ads.gasto.service;

import java.util.List;

import com.ads.gasto.model.GastosPorDiaModel;

public interface GastosPorDiaService {

    public List<GastosPorDiaModel> listar();

    public GastosPorDiaModel guardar(GastosPorDiaModel gastoPorDia);

    public GastosPorDiaModel buscarPorId(Long id);

    public void eliminar(Long id);

    public List<GastosPorDiaModel> listarPorMes(Integer mes, Integer ano);
}
