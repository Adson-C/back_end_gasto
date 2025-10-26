package com.ads.gasto.service.impl;

import com.ads.gasto.service.UsuariosService;
import com.ads.gasto.model.UsuariosModel;
import com.ads.gasto.repository.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuariosServiceImpl implements UsuariosService {

    @Autowired
    private IUsuariosRepository usuariosRepository;

    @Autowired
    private EstadoServiceImpl estadoServiceImpl;

    @Override
    public List<UsuariosModel> listar() {
        return usuariosRepository.findAll();
    }   

    @Override
    public UsuariosModel guardar(UsuariosModel usuario) {
        return usuariosRepository.save(usuario);
    }

    @Override
    public UsuariosModel buscarPorId(Long id) {
        return usuariosRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        usuariosRepository.deleteById(id);
    }

    @Override
    public UsuariosModel buscarPorCorreo(String correo) {
        return usuariosRepository.findByCorreo(correo);
    }

    @Override
    public UsuariosModel buscarPorCorreoAtivo(String correo) {
        Optional<UsuariosModel> optional = this.usuariosRepository.findByCorreoAndEstadoId(correo,
        Optional.ofNullable(this.estadoServiceImpl.buscarPorId(1L)).get().orElse(null));
        return optional.get();
    }
}
