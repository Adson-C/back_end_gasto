package com.ads.gasto.service.impl;

import com.ads.gasto.service.UsuariosService;
import com.ads.gasto.model.UsuariosModel;
import com.ads.gasto.repository.IUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuariosServiceImpl implements UsuariosService {

    @Autowired
    private IUsuariosRepository usuariosRepository;

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
}
