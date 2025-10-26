package com.ads.gasto.jwt;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ads.gasto.model.UsuariosModel;

public class UserInfoDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String name;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoDetails(UsuariosModel userInfo) {
        this.name = userInfo.getCorreo();
        this.password = userInfo.getPassword();
        this.authorities = Arrays.stream(userInfo.getNome().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }
    
}
