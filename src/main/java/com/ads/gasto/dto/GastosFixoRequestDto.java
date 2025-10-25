package com.ads.gasto.dto;

import lombok.Data;

@Data
public class GastosFixoRequestDto {

    private String nome;
    private Long quantia;
    private Long estadoId;
    private Long proveedoreId;
}