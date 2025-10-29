package com.ads.gasto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.gasto.model.ProveedoreModel;
import com.ads.gasto.repository.IProveedoreRepository;
import com.ads.gasto.service.ProveedoreService;

@Service
public class ProveedoreServiceImpl implements ProveedoreService {

    @Autowired
    private IProveedoreRepository proveedoreRepository;

    @Override
    public List<ProveedoreModel> listar() {
        return proveedoreRepository.findAll();
    }

    @Override
    public ProveedoreModel guardar(ProveedoreModel proveedore) {
        return proveedoreRepository.save(proveedore);
    }

    @Override
    public ProveedoreModel buscarPorId(Long id) {
        return proveedoreRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        proveedoreRepository.deleteById(id);
    }
}
