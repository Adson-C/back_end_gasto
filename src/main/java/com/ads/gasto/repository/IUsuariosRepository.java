package com.ads.gasto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ads.gasto.model.EstadosModel;
import com.ads.gasto.model.UsuariosModel;

public interface IUsuariosRepository extends JpaRepository<UsuariosModel, Long> {

    UsuariosModel findByCorreo(String correo);

    Optional<UsuariosModel> findByCorreoAndEstadoId(String correo, EstadosModel estadosId);
}
