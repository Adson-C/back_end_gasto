package com.ads.gasto.service;

import java.util.List;
import java.util.Optional;

import com.ads.gasto.model.EstadosModel;

public interface EstadoService {
    
   public List<EstadosModel> listar();

   public List<EstadosModel> listarParaGasto(List<Long> id);

    public void guardar(EstadosModel estado);

    public EstadosModel buscarPorId(Long id);

    public void eliminar(Long id);
}
