package com.ads.gasto.service;

import java.util.List;

import com.ads.gasto.model.UsuariosModel;

public interface UsuariosService {

    public List<UsuariosModel> listar();

    public UsuariosModel guardar(UsuariosModel usuario);

    public UsuariosModel buscarPorId(Long id);

    public void eliminar(Long id);
}
