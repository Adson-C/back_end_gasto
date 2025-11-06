package com.ads.gasto.service;

import java.util.List;

import com.ads.gasto.model.ReceitasModel;

public interface ReceitaService {


    public List<ReceitasModel> listar();

    public void guardar(ReceitasModel receita);

    public ReceitasModel buscarPorId(Long id);

    public void eliminar(Long id);
}
