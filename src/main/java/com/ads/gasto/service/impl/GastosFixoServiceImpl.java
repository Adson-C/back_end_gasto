package com.ads.gasto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ads.gasto.model.GastosFixoModel;
import com.ads.gasto.repository.IGastosFixoRepository;
import com.ads.gasto.service.GastosFixoService;

@Service
public class GastosFixoServiceImpl implements GastosFixoService {

    @Autowired
    private IGastosFixoRepository gastosFixoRepository;
    
    @Override
    public List<GastosFixoModel> listar() {
        return gastosFixoRepository.findAll(
            org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.DESC, "id"));
}

    @Override
    public GastosFixoModel guardar(GastosFixoModel gastoFixo) {
        return gastosFixoRepository.save(gastoFixo);
    }

    @Override
    public GastosFixoModel buscarPorId(Long id) {
        return gastosFixoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        gastosFixoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GastosFixoModel> listarPorMes(Integer mes, Integer ano) {
        if (mes == null || ano == null) {
            throw new RuntimeException("O mês e o ano são obrigatórios");
        }
        return gastosFixoRepository.findByAllMonth(mes, ano);
    }
}
