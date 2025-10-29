package com.ads.gasto.service;

import java.util.List;

import com.ads.gasto.model.VariableGlobalModel;

public interface VariableGlobalService {
    
    public List<VariableGlobalModel> listar();

    public VariableGlobalModel guardar(VariableGlobalModel variableGlobal);

    public VariableGlobalModel buscarPorId(Long id);

    public void eliminar(Long id);
}
