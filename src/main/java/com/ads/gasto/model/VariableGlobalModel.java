package com.ads.gasto.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "variable_global")
@Data
public class VariableGlobalModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "valor", length = 500, nullable = false)
    private String valor;


    public VariableGlobalModel() {
        super();
    }

    public VariableGlobalModel(Long id, String nome, String valor) {
        super();
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }
    
}
