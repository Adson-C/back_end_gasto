package com.ads.gasto.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "gastos_por_dia")
@Data
public class GastosPorDiaModel implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long liquido;
    private Long iva;
    private Long total;
    private Date fecha;
    @Column(name = "descripcion", length = 65535, columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedores_id")
    private ProveedoreModel proveedoreId;

    public GastosPorDiaModel(Long liquido, Long iva, Long total, Date fecha, String descripcion, ProveedoreModel proveedoreId) {
        this.liquido = liquido;
        this.iva = iva;
        this.total = total;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.proveedoreId = proveedoreId;
    }
    public GastosPorDiaModel() {
        super();
    }
}
