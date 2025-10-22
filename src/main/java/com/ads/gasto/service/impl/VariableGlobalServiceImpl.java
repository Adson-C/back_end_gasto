package com.ads.gasto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.gasto.model.VariableGlobalModel;
import com.ads.gasto.repository.IVariableGlobalRepository;
import com.ads.gasto.service.VariableGlobalService;

@Service
public class VariableGlobalServiceImpl implements VariableGlobalService {

    @Autowired
    private IVariableGlobalRepository variableGlobalRepository;

    @Override
    public List<VariableGlobalModel> listar() {
        return variableGlobalRepository.findAll();
    }

    @Override
    public VariableGlobalModel guardar(VariableGlobalModel variableGlobal) {
        return variableGlobalRepository.save(variableGlobal);
    }

    @Override
    public VariableGlobalModel buscarPorId(Long id) {
        return variableGlobalRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        variableGlobalRepository.deleteById(id);
    }
}
