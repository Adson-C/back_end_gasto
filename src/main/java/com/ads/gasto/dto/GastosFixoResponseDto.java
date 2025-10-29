package com.ads.gasto.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GastosFixoResponseDto {
    
    private Long id;
    private String nome;
    private Long quantia;
    private Date fecha;
    private Long estadoId;
    private String estadoNome;
    private Long proveedoreId;
    private String proveedoreNome;
}
