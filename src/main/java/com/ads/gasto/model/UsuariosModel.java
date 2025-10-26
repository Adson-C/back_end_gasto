package com.ads.gasto.model;
import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "usuarios")
@Data
public class UsuariosModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "correo", length = 255, nullable = false, unique = true)
    private String correo;

    private String password;

    private String token;

    private Date fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_id")
    private PerfilModel perfilId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estados_id")
    private EstadosModel estadosId;
    
    public UsuariosModel(String nome, String correo, String password, Date fecha, PerfilModel perfilId, EstadosModel estadosId) {
        this.nome = nome;
        this.correo = correo;
        this.password = password;
        this.fecha = fecha;
        this.perfilId = perfilId;
        this.estadosId = estadosId;
    }
    public UsuariosModel() {
        super();
    }
}
