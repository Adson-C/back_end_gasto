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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "perfil_id")
    private PerfilModel perfilId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estados_id")
    private EstadosModel estadosId;

    // Método para obtenção de autoridades, se necessário
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    //     return Arrays.stream(this.perfilId.getNome().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    // }
}