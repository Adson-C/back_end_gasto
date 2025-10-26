package com.ads.gasto.jwt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ads.gasto.model.UsuariosModel;
import com.ads.gasto.repository.IUsuariosRepository;
import com.ads.gasto.service.impl.EstadoServiceImpl;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private IUsuariosRepository usuariosRepository;

    @Autowired
    private EstadoServiceImpl estadoServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuariosModel> userdetails = this.usuariosRepository.findByCorreoAndEstadoId(username,
        Optional.ofNullable(this.estadoServiceImpl.buscarPorId(1L)).get().orElse(null));

        return userdetails.map(UserInfoDetails::new).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado" + username));
    }
    
}
