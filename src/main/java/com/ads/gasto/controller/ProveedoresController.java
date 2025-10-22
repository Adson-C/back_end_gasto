package com.ads.gasto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads.gasto.model.ProveedoreModel;
import com.ads.gasto.service.impl.ProveedoreServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class ProveedoresController {
    
    @Autowired
    private ProveedoreServiceImpl proveedoreServiceImpl;

    @GetMapping("/proveedores")
    public ResponseEntity<List<ProveedoreModel>> getAllProveedores() {
        return ResponseEntity.status(HttpStatus.OK).body(proveedoreServiceImpl.listar());
    }
}
