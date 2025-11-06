package com.ads.gasto.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ads.gasto.model.EstadosModel;
import com.ads.gasto.repository.IEstadosRepository;
import com.ads.gasto.service.EstadoService;

@Service
public class EstadoServiceImpl implements EstadoService {

    @Autowired
    private IEstadosRepository estadosRepository;

    @Override
    public List<EstadosModel> listar() {
        return estadosRepository.findAll(Sort.by("id").ascending());
    }

    @Override
    public void guardar(EstadosModel estado) {
        estadosRepository.save(estado);
    }

    @Override
    public EstadosModel buscarPorId(Long id) {
        Optional<EstadosModel> optional = estadosRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void eliminar(Long id) {
        estadosRepository.deleteById(id);
    }

    @Override
    public List<EstadosModel> listarParaGasto(List<Long> id) {
        return estadosRepository.findAll(Sort.by("id").ascending());
    }

}
