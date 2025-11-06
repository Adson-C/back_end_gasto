package com.ads.gasto.dto;

import com.ads.gasto.enums.TipoReceitas;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ReceitaRequestDto {
    
    private TipoReceitas tipoReceita;
    private Long valor;
    private String descricao;
}
