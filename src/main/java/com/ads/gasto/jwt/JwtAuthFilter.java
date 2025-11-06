package com.ads.gasto.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ads.gasto.model.UsuariosModel;
import com.ads.gasto.service.impl.UsuariosServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuariosServiceImpl usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // Bearer: 42556
            String authHeader = request.getHeader("Authorization");
            String token = null;
            String username = null;
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                try {
                    username = jwtService.extractUsername(token);
                } catch (Exception e) {
                    // Token inválido ou expirado
                    System.out.println("Erro ao extrair username do token: " + e.getMessage());
                }
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsuariosModel useerDetails = usuarioService.buscarPorCorreo(username);
                if (useerDetails != null && this.jwtService.validateToken(token, useerDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            useerDetails, null, null);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro no filtro JWT: " + e.getMessage());
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }
    // @Override
    // protected void doFilterInternal(HttpServletRequest request,
    // HttpServletResponse response, FilterChain filterChain)
    // throws ServletException, IOException {
    // // Bearer: 42556
    // String authHeader = request.getHeader("Authorization");
    // String token = null;
    // String username = null;
    // if (authHeader != null && authHeader.startsWith("Bearer ")) {
    // token = authHeader.substring(7);
    // username = jwtService.extractUsername(token);
    // }
    // if (username != null &&
    // SecurityContextHolder.getContext().getAuthentication() == null) {

    // UsuariosModel userDetails = usuarioService.buscarPorCorreo(username);
    // if (userDetails != null && this.jwtService.validateToken(token, userDetails))
    // {

    // UsernamePasswordAuthenticationToken authenticationToken = new
    // UsernamePasswordAuthenticationToken(
    // userDetails, null, userDetails.getAuthorities());
    // authenticationToken.setDetails(new
    // WebAuthenticationDetailsSource().buildDetails(request));
    // SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    // filterChain.doFilter(request, response);
    // }
    // }
    // }
}
