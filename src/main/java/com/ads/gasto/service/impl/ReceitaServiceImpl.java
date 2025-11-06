package com.ads.gasto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.ads.gasto.model.ReceitasModel;
import com.ads.gasto.repository.IReceitasRepository;
import com.ads.gasto.service.ReceitaService;

@Service
public class ReceitaServiceImpl implements ReceitaService {

    @Autowired
    private IReceitasRepository receitasRepository;

    @Override
    public List<ReceitasModel> listar() {
        return receitasRepository.findAll(Sort.by("id").ascending());
    }

    @Override
    public void guardar(ReceitasModel receita) {
        receitasRepository.save(receita);
    }

    @Override
    public ReceitasModel buscarPorId(Long id) {
        return receitasRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        receitasRepository.deleteById(id);
    }
    
}
