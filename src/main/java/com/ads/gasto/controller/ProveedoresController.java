package com.ads.gasto.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ads.gasto.dto.ProveedoresRequestDto;
import com.ads.gasto.model.ProveedoreModel;
import com.ads.gasto.service.impl.ProveedoreServiceImpl;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
        RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
@RestController
@RequestMapping("/api/v1")
public class ProveedoresController {

    @Autowired
    private ProveedoreServiceImpl proveedoreServiceImpl;

    /*
     * Listar proveedores
     * 
     * @return ResponseEntity<List<ProveedoreModel>>
     * 
     * @throws Exception
     * author Adson Sá
     */
    @GetMapping("/proveedores")
    @SuppressWarnings({ "serial", "unchecked" })
    public ResponseEntity<List<ProveedoreModel>> getAllProveedores() {
        return ResponseEntity.status(HttpStatus.OK).body(proveedoreServiceImpl.listar());
    }

    // busacr por id
    /*
     * Buscar proveedor por id
     * 
     * @param id
     * 
     * @return ResponseEntity<ProveedoreModel>
     * 
     * @throws Exception
     * author Adson Sá
     */
    @SuppressWarnings({ "serial", "unchecked" })
    @GetMapping("/proveedores/{id}")
    public ResponseEntity<ProveedoreModel> getProveedorById(@PathVariable Long id) {
        ProveedoreModel proveedore = proveedoreServiceImpl.buscarPorId(id);
        if (proveedore == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(proveedore);
    }

    /*
     * Crear proveedor
     * 
     * @param dto
     * 
     * @return ResponseEntity
     * 
     * @throws Exception
     * author Adson Sá
     */
    @SuppressWarnings({ "serial", "unchecked" })
    @PostMapping("/proveedores")
    public ResponseEntity<?> createProveedor(@RequestBody ProveedoresRequestDto dto) {
        try {
            this.proveedoreServiceImpl.guardar(new ProveedoreModel(null, dto.getNome()));
            return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
                {
                    put("message", "Proveedor criado com sucesso");
                }
            });
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, String>() {
                {
                    put("message", "Ocorreu um erro ao criar o proveedor");
                }
            });
        }
    }

    /*
     * Atualizar proveedor
     * 
     * @param id
     * 
     * @param dto
     * 
     * @return ResponseEntity
     * 
     * @throws Exception
     * author Adson Sá
     */
    @SuppressWarnings({ "serial", "unchecked" })
    @PutMapping("/proveedores/{id}")
    public ResponseEntity<?> updateProveedor(@PathVariable Long id, @RequestBody ProveedoresRequestDto dto) {
        ProveedoreModel proveedore = this.proveedoreServiceImpl.buscarPorId(id);
        if (proveedore != null) {
            proveedore.setNome(dto.getNome());
            this.proveedoreServiceImpl.guardar(proveedore);
            return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
                {
                    put("message", "Proveedor atualizado com sucesso");
                }
            });
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
                {
                    put("message", "Proveedor não encontrado");
                }
            });
        }
    }
}
