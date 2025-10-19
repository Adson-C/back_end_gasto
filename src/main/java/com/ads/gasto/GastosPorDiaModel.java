package com.ads.gasto;

import java.util.Date;

import com.ads.gasto.model.ProveedoreModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gastos_por_dia")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    
}
