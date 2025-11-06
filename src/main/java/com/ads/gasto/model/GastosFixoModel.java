package com.ads.gasto.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "gastos_fixos")
@Data
public class GastosFixoModel implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    private Long quantia;

    private Date fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estados_id")
    private EstadosModel estadoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedores_id")
    private ProveedoreModel proveedoreId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "gastos_fixos_id")
    private List<ReceitasModel> receitas;
    
    public GastosFixoModel(String nome, Long quantia, Date fecha, EstadosModel estadoId, ProveedoreModel proveedoreId, List<ReceitasModel> receitas) {
        this.nome = nome;
        this.quantia = quantia;
        this.fecha = fecha;
        this.estadoId = estadoId;
        this.proveedoreId = proveedoreId;
        this.receitas = receitas;
    }
    public GastosFixoModel() {
        super();
    }
}
