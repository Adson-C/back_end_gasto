package com.ads.gasto.dto;

import lombok.Data;

@Data
public class GastosDiaRequestDto {

    // private Long liquido;
    // private Long iva;
    // private Long total;
    // private String descripcion;
    // private ProveedoreModel proveedoreId;
    
    private Long liquido;
    private Long iva;
    private Long total;
    private String descripcion;
    private Long proveedoreId;
}
