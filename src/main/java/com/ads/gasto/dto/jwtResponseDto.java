package com.ads.gasto.dto;

import lombok.Data;

@Data
public class jwtResponseDto {
    private Long id;
    private String nome;
    private String perfil_nome;
    private Long perfil_id;
    // private Collection<? extends GrantedAuthority> authorities;
    private String token;

    public jwtResponseDto(Long id, String nome, String perfil_nome, Long perfil_id, String token) {
        this.id = id;
        this.nome = nome;
        this.perfil_nome = perfil_nome;
        this.perfil_id = perfil_id;
        // this.authorities = authorities;
        this.token = token;
    }
}
