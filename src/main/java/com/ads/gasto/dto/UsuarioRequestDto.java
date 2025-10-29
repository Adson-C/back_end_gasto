package com.ads.gasto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UsuarioRequestDto {
    
    private String nome;
    private String correo;
    private String password;
}
