package com.example.api_rh.security;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private final String email;

    private final String senha;

    private final String ativo;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String email, String senha, String ativo, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return "S".equalsIgnoreCase(ativo) || "true".equalsIgnoreCase(ativo) || "1".equals(ativo);
    }
}
