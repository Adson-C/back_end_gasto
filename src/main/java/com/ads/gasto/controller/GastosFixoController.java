package com.ads.gasto.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ads.gasto.dto.GastosFixoRequestDto;
import com.ads.gasto.dto.GastosFixoResponseDto;
import com.ads.gasto.mapper.GastosFixoMapper;
import com.ads.gasto.model.GastosFixoModel;
import com.ads.gasto.model.ProveedoreModel;
import com.ads.gasto.service.impl.EstadoServiceImpl;
import com.ads.gasto.service.impl.GastosFixoServiceImpl;
import com.ads.gasto.service.impl.ProveedoreServiceImpl;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class GastosFixoController {

    @Autowired
    private GastosFixoServiceImpl gastosFixoServiceImpl;

    @Autowired
    private EstadoServiceImpl estadoServiceImpl;

    @Autowired
    private ProveedoreServiceImpl proveedoreServiceImpl;

    @Autowired
    private GastosFixoMapper gastosFixoMapper;

    /*
     * Listar gastos fixos por mês e ano
     * @return ResponseEntity<?>
     * @throws Exception
     * author Adson Sá
     */
    @SuppressWarnings({ "serial", "unchecked" })
    @GetMapping("/gastos-fixos")
    public ResponseEntity<?> getMothYear() {
        LocalDate dataAtual = LocalDate.now();
        Integer mes = dataAtual.getMonthValue();
        Integer ano = dataAtual.getYear();
        
        List<GastosFixoModel> gastos = gastosFixoServiceImpl.listarPorMes(mes, ano);
        List<GastosFixoResponseDto> gastosDto = gastosFixoMapper.toDtoList(gastos);
        
        return ResponseEntity.status(HttpStatus.OK).body(gastosDto);
    }

    /*
     * Listar gastos fixos por mês exato
     * @return ResponseEntity<?>
     * @throws Exception
     * author Adson Sá
     */
    @SuppressWarnings({ "serial", "unchecked" })
    @GetMapping("/gastos-fixos/{mes}")
    public ResponseEntity<?> getMothExact(@PathVariable Integer mes) {
        LocalDate dataAtual = LocalDate.now();
        Integer ano = dataAtual.getYear();
        
        List<GastosFixoModel> gastos = gastosFixoServiceImpl.listarPorMes(mes, ano);
        List<GastosFixoResponseDto> gastosDto = gastosFixoMapper.toDtoList(gastos);
        
        return ResponseEntity.status(HttpStatus.OK).body(gastosDto);
    }
    /*{
    "nome": "Conta de Luz",
    "quantia": 90,
    "proveedoreId": 1,
    "estadoId": 1-4
} */
      /*
     * Criar gastos fixos
     * @return ResponseEntity<?>
     * @throws Exception
     * author Adson Sá
     */
    @SuppressWarnings({ "serial", "unchecked" })
    @PostMapping("/gastos-fixos")
    public ResponseEntity<?> createGastoFixo(@RequestBody GastosFixoRequestDto dto) {
        ProveedoreModel proveedore = proveedoreServiceImpl.buscarPorId(dto.getProveedoreId());
        if(proveedore == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Proveedor não encontrado");
                }
            });

        }else{
            try{
                this.gastosFixoServiceImpl.guardar(
                    new GastosFixoModel(
                        dto.getNome(),
                        dto.getQuantia(),
                        new Date(),
                        this.estadoServiceImpl.buscarPorId(4L),
                        proveedore
                    )
                );
                return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>(){
                    
                });
            }catch(Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, String>(){
                    {
                        put("message", "Erro ao criar gasto fixo");
                    }
                });
            }
        }   
    }
/*{
    "nome": "Conta de Luz",
    "quantia": 90,
    "proveedoreId": 1,
    "estadoId": 3-4
} */
     /*
     * Atualizar gastos fixos
     * @param id
     * @param dto
     * @return ResponseEntity<?>
     * @throws Exception
     * author Adson Sá
     */
    @SuppressWarnings({ "serial", "unchecked" })
    @PutMapping("/gastos-fixos/{id}")
    public ResponseEntity<?> updateGastoFixo(@PathVariable Long id, @RequestBody GastosFixoRequestDto dto) {
        GastosFixoModel gastoFixo = this.gastosFixoServiceImpl.buscarPorId(id);
        ProveedoreModel proveedore = proveedoreServiceImpl.buscarPorId(dto.getProveedoreId());
        if(gastoFixo == null || proveedore == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Gasto fixo ou proveedor não encontrado");
                }
            });
        }else{
            try{
                gastoFixo.setEstadoId(this.estadoServiceImpl.buscarPorId(dto.getEstadoId()));
                gastoFixo.setQuantia(dto.getQuantia());
                gastoFixo.setNome(dto.getNome());
                gastoFixo.setProveedoreId(proveedore);
                this.gastosFixoServiceImpl.guardar(gastoFixo);
                return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){});
            }catch(Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, String>(){
                    {
                        put("message", "Erro ao atualizar gasto fixo");
                    }
                });
            }
        }
    }

      /*
     * Deletar gastos fixos
     * @param id
     * @param dto
     * @return ResponseEntity<?>
     * @throws Exception
     * author Adson Sá
     */
    @SuppressWarnings({ "serial", "unchecked" })
    @DeleteMapping("/gastos-fixos/{id}")
    public ResponseEntity<?> deleteGastoFixo(@PathVariable Long id) {
        GastosFixoModel gastoFixo = this.gastosFixoServiceImpl.buscarPorId(id);
        if(gastoFixo != null){
            this.gastosFixoServiceImpl.eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){
                {
                    put("message", "Gasto fixo deletado com sucesso");
                }
            });
            
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Gasto fixo não encontrado");
                }
            });
        }
    }
}