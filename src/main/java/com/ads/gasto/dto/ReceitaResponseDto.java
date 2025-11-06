package com.ads.gasto.dto;

import java.util.Date;

import com.ads.gasto.enums.TipoReceitas;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ReceitaResponseDto {
    
    private Long id;
    private TipoReceitas tipoReceita;
    private Long valor;
    private String descricao;
    private Date data;
}
