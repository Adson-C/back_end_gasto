package com.ads.gasto.service;

import java.util.List;

import com.ads.gasto.model.UsuariosModel;

public interface UsuariosService {

    List<UsuariosModel> listar();

    UsuariosModel guardar(UsuariosModel usuario);

    UsuariosModel buscarPorId(Long id);

    void eliminar(Long id);

    public UsuariosModel buscarPorCorreo(String correo);

    public UsuariosModel buscarPorCorreoAtivo(String correo);
}
