package com.ads.gasto.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads.gasto.dto.UsuarioRequestDto;
import com.ads.gasto.dto.UsuarioResponseDto;
import com.ads.gasto.model.UsuariosModel;
import com.ads.gasto.service.impl.EstadoServiceImpl;
import com.ads.gasto.service.impl.PerfilServiceImpl;
import com.ads.gasto.service.impl.UsuariosServiceImpl;

import jakarta.transaction.Transactional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuariosServiceImpl usuariosServiceImpl;

    @Autowired
    private PerfilServiceImpl perfilServiceImpl;

    @Autowired
    private EstadoServiceImpl estadoServiceImpl;

       /*
     * Listar todos os usuários
     * @return ResponseEntity
     * @throws Exception
     * author Adson Santos
     */
    @SuppressWarnings("serial")
    @GetMapping("/usuarios")
    public ResponseEntity<?> getAllUsuarios() {
        List<UsuarioResponseDto> lista = new ArrayList<>();
        List<UsuariosModel> usuarios = this.usuariosServiceImpl.listar();
        usuarios.forEach(usuario -> {
            lista.add(new UsuarioResponseDto(
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getCorreo(),
                    usuario.getPerfilId().getNome(),
                    usuario.getPerfilId().getId(),
                    usuario.getEstadosId().getId(),
                    usuario.getEstadosId().getNome()));
        });
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    /*
     * {
     * "nome": "John John",
     * "correo": "john@exemplo.com.br",
     * "password": "123456"
     * }
     */

   /*
     * Criar usuário
     * @param dto
     * @param id
     * @return ResponseEntity
     * @throws Exception
     * author Adson Sá
     */
    @SuppressWarnings("serial")
    @Transactional
    @PostMapping("/usuarios")
    public ResponseEntity<?> createUsuario(@RequestBody UsuarioRequestDto dto) {
        UsuariosModel usuario = this.usuariosServiceImpl.buscarPorCorreo(dto.getCorreo());
        if (usuario == null) {
            UsuariosModel novoUsuario = new UsuariosModel();
            novoUsuario.setNome(dto.getNome());
            novoUsuario.setCorreo(dto.getCorreo());
            novoUsuario.setPassword(this.passwordEncoder.encode(dto.getPassword()));
            novoUsuario.setToken(""); // token
            novoUsuario.setFecha(new Date(System.currentTimeMillis()));
            novoUsuario.setPerfilId(this.perfilServiceImpl.buscarPorId(2L));
            novoUsuario.setEstadosId(this.estadoServiceImpl.buscarPorId(1L));

            this.usuariosServiceImpl.guardar(novoUsuario);
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
                {
                    put("message", "Usuário criado com sucesso");
                }
            });
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
                {
                    put("message", "Ocorreu um erro ao criar o usuário");
                }
            });
        }
    }
       /*
     * Atualizar usuário
     * @param id
     * @param dto
     * @return ResponseEntity
     * @throws Exception
     * author Adson Sá
     */
    @SuppressWarnings("serial")
    @Transactional
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDto dto) {
        UsuariosModel usuario = this.usuariosServiceImpl.buscarPorId(id);
        if (usuario != null) {
            usuario.setNome(dto.getNome());
            usuario.setCorreo(dto.getCorreo());
            usuario.setPassword(this.passwordEncoder.encode(dto.getPassword()));
            this.usuariosServiceImpl.guardar(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
                {
                    put("message", "Usuário atualizado com sucesso");
                }
            });
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
                {
                    put("message", "Ocorreu um erro ao atualizar o usuário");
                }
            });
        }
    }
    /*
     * Deletar usuário
     * @param id
     * @return ResponseEntity
     * @throws Exception
     * author Adson Sá
     */
    @SuppressWarnings("serial")
    @Transactional
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        UsuariosModel usuario = this.usuariosServiceImpl.buscarPorId(id);
        if (usuario != null) {
            this.usuariosServiceImpl.eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>() {
                {
                    put("message", "Usuário deletado com sucesso");
                }
            });
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HashMap<String, String>() {
                {
                    put("message", "Usuário não encontrado");
                }
            });
        }
    }
}