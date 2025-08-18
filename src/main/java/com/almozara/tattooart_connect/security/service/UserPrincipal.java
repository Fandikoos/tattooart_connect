package com.almozara.tattooart_connect.security.service;

import com.almozara.tattooart_connect.security.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPrincipal implements UserDetails {

    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    // Creamos un objeto UserDetailsImpl a partir del user entity, modificando los roles para que sean una coleccion de Granted Authortires
    public static UserPrincipal builder(UserEntity userEntity){
        Collection<GrantedAuthority> authorities = userEntity.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
        return new UserPrincipal(userEntity.getUsername(), userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
