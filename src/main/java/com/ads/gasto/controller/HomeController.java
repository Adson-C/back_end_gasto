package com.ads.gasto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class HomeController {
    
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Bienvenido al sistema de gestión de gastos");
    }
}
