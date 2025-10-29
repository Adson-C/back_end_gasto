package com.ads.gasto.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GastosDiaResponseDto {

    // private Long liquido;
    // private Long iva;
    // private Long total;
    // private Date fecha;
    // private String descripcion;
    // private Long proveedoreId;
    // private Long estadoId;
    
    private Long id;
    private Long liquido;
    private Long iva;
    private Long total;
    private Date fecha;
    private String descripcion;
    private Long proveedoreId;
    private String proveedoreNome;
}
