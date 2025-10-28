package com.ads.gasto.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads.gasto.dto.LoginDto;
import com.ads.gasto.dto.jwtResponseDto;
import com.ads.gasto.jwt.JwtService;
import com.ads.gasto.model.UsuariosModel;
import com.ads.gasto.service.impl.UsuariosServiceImpl;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {
    
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsuariosServiceImpl usuariosServiceImpl;

    @Autowired
    private JwtService jwtService;

    //loginl
    @SuppressWarnings("serial")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {

        UsuariosModel usuario = this.usuariosServiceImpl.buscarPorCorreoAtivo(dto.getCorreo());
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Usuario no encontrado");    
                }
            });
        }else
        {
        if (this.bCryptPasswordEncoder.matches(dto.getPassword(), usuario.getPassword())) {
            String token = this.jwtService.generateToken(usuario.getCorreo());

            return ResponseEntity.status(HttpStatus.OK).body(
                new jwtResponseDto(
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getPerfilId().getNome(),
                    usuario.getPerfilId().getId(),
                    token
                )
            );

            // return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){
            //     {
            //         put("id", usuario.getId().toString());
            //         put("user", usuario.getNome());
            //         put("perfil_id", usuario.getPerfilId().getId().toString());
            //         put("email", usuario.getCorreo());
            //         put("token", token);
            //     }
            // });
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "As credenciais estão incorretas");    
                }
            });
        }
        }
    }
    //fesh
    @SuppressWarnings("serial")
    @GetMapping("/refresh/{id}")
    public ResponseEntity<?> refresh(@PathVariable("id") Long id) {
        UsuariosModel usuario = this.usuariosServiceImpl.buscarPorId(id);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>(){
                {
                    put("message", "Usuario no encontrado");
                }
            });
        }else{
           return ResponseEntity.status(HttpStatus.OK).body(new jwtResponseDto(
            usuario.getId(),
            usuario.getNome(),
            usuario.getPerfilId().getNome(),
            usuario.getPerfilId().getId(),
            this.jwtService.generateToken(usuario.getCorreo())));
        
        }
    }    
}
