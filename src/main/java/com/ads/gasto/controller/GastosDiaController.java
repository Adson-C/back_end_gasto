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

import com.ads.gasto.dto.GastosDiaRequestDto;
import com.ads.gasto.dto.GastosDiaResponseDto;
import com.ads.gasto.mapper.GastosDiaMapper;
import com.ads.gasto.model.GastosPorDiaModel;
import com.ads.gasto.model.ProveedoreModel;
import com.ads.gasto.service.impl.GastosPorDiaServiceImpl;
import com.ads.gasto.service.impl.ProveedoreServiceImpl;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class GastosDiaController {

    @Autowired
    private GastosPorDiaServiceImpl gastosPorDiaServiceImpl;

    @Autowired
    private ProveedoreServiceImpl proveedoreServiceImpl;

    @Autowired
    private GastosDiaMapper gastosDiaMapper;

      /*
     * Listar gastos por dia por mês e ano
     * @return ResponseEntity<?>
     * @throws Exception
     * author Adson Sá
     */
    @SuppressWarnings({ "serial", "unchecked" })
    @GetMapping("/gastos-dia")
    public ResponseEntity<?> getGastosDia() {
        LocalDate dataAtual = LocalDate.now();
        Integer mes = dataAtual.getMonthValue();
        Integer ano = dataAtual.getYear();
        
        List<GastosPorDiaModel> gastos = gastosPorDiaServiceImpl.listarPorMes(mes, ano);
        List<GastosDiaResponseDto> gastosDto = gastosDiaMapper.toDtoList(gastos);
        
        return ResponseEntity.status(HttpStatus.OK).body(gastosDto);
    }

    /*
     * Listar gastos por dia por mês exato
     * @return ResponseEntity<?>
     * @throws Exception
     * author Adson Sá
     */
    @SuppressWarnings({ "serial", "unchecked" })
    @GetMapping("/gastos-dia/{dia}")
    public ResponseEntity<?> getDayExact(@PathVariable Integer dia) {
        LocalDate dataAtual = LocalDate.now();
        Integer ano = dataAtual.getYear();
        
        List<GastosPorDiaModel> gastos = gastosPorDiaServiceImpl.listarPorMes(dia, ano);
        List<GastosDiaResponseDto> gastosDto = gastosDiaMapper.toDtoList(gastos);
        
        return ResponseEntity.status(HttpStatus.OK).body(gastosDto);
    }

    /*
    {
     "liquido": 100
     "iva": 19
     "total": 119
     "descripcion": "Gasto de teste"
     "proveedoreId": 1
    }
    */
     /*
     * Criar gastos por dia
     * @return ResponseEntity<?>
     * @throws Exception
     * author Adson Sá
     */
    @SuppressWarnings({ "serial", "unchecked" })
    @PostMapping("/gastos-dia")
    public ResponseEntity<?> createGastoDia(@RequestBody GastosDiaRequestDto dto) {

        ProveedoreModel proveedore = this.proveedoreServiceImpl.buscarPorId(dto.getProveedoreId());
         if (proveedore != null) {
            try{
                this.gastosPorDiaServiceImpl.guardar(
                    new GastosPorDiaModel(
                        dto.getLiquido(),
                        dto.getIva(),
                        dto.getTotal(),
                        new Date(),
                        dto.getDescripcion(),
                        proveedore
                    )
                );
                return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<String, String>(){
                    {
                        put("message", "Gasto por dia criado com sucesso");
                    }
                });
            }catch(Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HashMap<String, String>(){
                    {
                        put("message", "Erro ao criar gasto por dia");
                    }
                });
            }
         }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Proveedor não encontrado");
                }
            });
         }
    }

    /*
     * Atualizar gastos por dia
     * @return ResponseEntity<?>
     * @throws Exception
     * author Adson Sá
     */
    @SuppressWarnings({ "serial", "unchecked" })
    @PutMapping("/gastos-dia/{id}")
    public ResponseEntity<?> updateGastoDia(@PathVariable Long id, @RequestBody GastosDiaRequestDto dto) {
        GastosPorDiaModel gastoPorDia = this.gastosPorDiaServiceImpl.buscarPorId(id);
        ProveedoreModel proveedore = proveedoreServiceImpl.buscarPorId(dto.getProveedoreId());
        if(gastoPorDia == null || proveedore == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Gasto por dia ou proveedor não encontrado");
                }
            });
        }else{
            try{
                gastoPorDia.setLiquido(dto.getLiquido());
                gastoPorDia.setIva(dto.getIva());
                gastoPorDia.setTotal(dto.getTotal());
                gastoPorDia.setDescripcion(dto.getDescripcion());
                gastoPorDia.setProveedoreId(proveedore);
                this.gastosPorDiaServiceImpl.guardar(gastoPorDia);
                return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){
                    {
                        put("message", "Gasto fixo atualizado com sucesso");
                    }
                });
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
    @DeleteMapping("/gastos-dia/{id}")
    public ResponseEntity<?> deleteGastoDia(@PathVariable Long id) {
        GastosPorDiaModel gastoPorDia = this.gastosPorDiaServiceImpl.buscarPorId(id);
        if(gastoPorDia != null){
            this.gastosPorDiaServiceImpl.eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){
                {
                    put("message", "Gasto por dia deletado com sucesso");
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
