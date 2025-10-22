package com.ads.gasto.service.impl;

import com.ads.gasto.service.PerfilService;
import com.ads.gasto.model.PerfilModel;
import com.ads.gasto.repository.IPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PerfilServiceImpl implements PerfilService {

    @Autowired
    private IPerfilRepository perfilRepository;

    @Override
    public List<PerfilModel> listar() {
        return perfilRepository.findAll();
    }

    @Override
    public PerfilModel guardar(PerfilModel perfil) {
        return perfilRepository.save(perfil);
    }

    @Override
    public PerfilModel buscarPorId(Long id) {
        return perfilRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        perfilRepository.deleteById(id);
    }
}
