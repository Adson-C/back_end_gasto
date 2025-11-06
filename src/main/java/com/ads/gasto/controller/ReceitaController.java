package com.ads.gasto.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ads.gasto.dto.ReceitaRequestDto;
import com.ads.gasto.dto.ReceitaResponseDto;
import com.ads.gasto.mapper.RecitasMapper;
import com.ads.gasto.model.ReceitasModel;
import com.ads.gasto.service.impl.ReceitaServiceImpl;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
    RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
@RestController
@RequestMapping("/api/v1")
public class ReceitaController {

    @Autowired
    private ReceitaServiceImpl receitaServiceImpl;

    @Autowired
    private RecitasMapper recitasMapper;



    @SuppressWarnings({ "serial", "unchecked" })
    @GetMapping("/receitas")
    public ResponseEntity<?> listarReceitas() {
        List<ReceitasModel> receitas = receitaServiceImpl.listar();
        List<ReceitaResponseDto> receitasDto = recitasMapper.toDtoList(receitas);
        return ResponseEntity.status(HttpStatus.OK).body(receitasDto);
    }

    /*
     * Criar receita
     * @param dto
     * @param dto.tipoReceita - Tipo de receita
     * @param dto.valor
     * @param dto.descricao
     * @return ResponseEntity<?>
     * author Adson Sá (adaptado)
     */
    @SuppressWarnings({ "serial", "unchecked" })
    @PostMapping("/receitas")
    public ResponseEntity<?> createReceita(@RequestBody ReceitaRequestDto dto) {
        try {
            receitaServiceImpl.guardar(new ReceitasModel(dto.getTipoReceita(), dto.getValor(), dto.getDescricao(), new Date()));
            return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>() {
                {
                    put("message", "Receita criada com sucesso");
                }
            });
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, String>(){
                {
                    put("message", "Erro ao criar receita");
                }
            });
        }
    }

    /*
     * Atualizar receita
     * @param id
     * @param receita
     * @return ResponseEntity<?>
     * author Adson Sá (adaptado)
     */
    @SuppressWarnings({ "serial", "unchecked" })
    @PutMapping("/receitas/{id}")
    public ResponseEntity<?> updateReceita(@PathVariable Long id, @RequestBody ReceitaRequestDto dto) {
        ReceitasModel receitaExistente = receitaServiceImpl.buscarPorId(id);
        if (receitaExistente == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Receita não encontrada");
                }
            });
        } else {
            try {
                receitaExistente    .setDescricao(dto.getDescricao());
                receitaExistente.setValor(dto.getValor());
                receitaExistente.setTipoReceita(dto.getTipoReceita());
                receitaServiceImpl.guardar(receitaExistente);
                return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){});
            } catch(Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, String>(){
                    {
                        put("message", "Erro ao atualizar receita");
                    }
                });
            }
        }
    }

    /*
     * Deletar receita
     * @param id
     * @return ResponseEntity<?>
     * author Adson Sá (adaptado)
     */
    @SuppressWarnings({ "serial", "unchecked" })
    @DeleteMapping("/receitas/{id}")
    public ResponseEntity<?> deleteReceita(@PathVariable Long id) {
        ReceitasModel receita = receitaServiceImpl.buscarPorId(id);
        if (receita != null) {
            receitaServiceImpl.eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){
                {
                    put("message", "Receita deletada com sucesso");
                }
            });
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Receita não encontrada");
                }
            });
        }
    }
}
