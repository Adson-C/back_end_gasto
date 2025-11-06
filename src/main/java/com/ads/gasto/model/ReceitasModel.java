package com.ads.gasto.model;

import java.util.Date;

import com.ads.gasto.enums.TipoReceitas;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

@Entity
@Table(name = "receitas")
@Data
public class ReceitasModel implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(name = "tipo_receita", length = 255, nullable = false)
    private TipoReceitas tipoReceita;
    
    @Column(name = "valor", nullable = false)
    private Long valor;
    
    @Column(name = "descricao", length = 255, nullable = false)
    private String descricao;

    @Column(name = "data", nullable = false)
    private Date data;


    public ReceitasModel(TipoReceitas tipoReceita, Long valor, String descricao, Date data) {
        this.tipoReceita = tipoReceita;
        this.valor = valor;
        this.descricao = descricao;
        this.data = data;
    }
    public ReceitasModel() {
        super();
    }
}
