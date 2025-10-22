package com.ads.gasto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ads.gasto.model.GastosPorDiaModel;
import com.ads.gasto.repository.IGastosPorDiaRepository;
import com.ads.gasto.service.GastosPorDiaService;

@Service
public class GastosPorDiaServiceImpl implements GastosPorDiaService {

    @Autowired
    private IGastosPorDiaRepository gastosPorDiaRepository;

    @Override
    public List<GastosPorDiaModel> listar() {
        return gastosPorDiaRepository.findAll();
    }

    @Override
    public GastosPorDiaModel guardar(GastosPorDiaModel gastoPorDia) {
        
        return gastosPorDiaRepository.save(gastoPorDia);
    }

    @Override
    public GastosPorDiaModel buscarPorId(Long id) {
        return gastosPorDiaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        gastosPorDiaRepository.deleteById(id);
    }
}
