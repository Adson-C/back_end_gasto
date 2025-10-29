package com.ads.gasto.service;

import java.util.List;

import com.ads.gasto.model.PerfilModel;

public interface PerfilService {

    public List<PerfilModel> listar();

    public PerfilModel guardar(PerfilModel perfil);

    public PerfilModel buscarPorId(Long id);

    public void eliminar(Long id);
    
}
