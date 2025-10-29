package com.ads.gasto.service;

import java.util.List;

import com.ads.gasto.model.ProveedoreModel;

public interface ProveedoreService {
    
    public List<ProveedoreModel> listar();

    public ProveedoreModel guardar(ProveedoreModel proveedore);

    public ProveedoreModel buscarPorId(Long id);

    public void eliminar(Long id);
}
