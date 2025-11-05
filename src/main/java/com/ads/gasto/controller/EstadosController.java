package com.ads.gasto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads.gasto.model.EstadosModel;
import com.ads.gasto.service.impl.EstadoServiceImpl;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class EstadosController {

    @Autowired
    private EstadoServiceImpl estadoServiceImpl;

    @GetMapping("/estados")
    public ResponseEntity<List<EstadosModel>> getAllEstados() {
        return ResponseEntity.status(HttpStatus.OK).body(estadoServiceImpl.listar());
    }

    @GetMapping("/estados-gastos")
    public ResponseEntity<?> getEstadosParaGasto() {

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(3L);
        return ResponseEntity.status(HttpStatus.OK).body(estadoServiceImpl.listarParaGasto(ids));
    }

}
