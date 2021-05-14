package com.training.sportsbetting.service.player.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.training.sportsbetting.domain.User;

public class DefaultUserDetails implements UserDetails {

    private static final long serialVersionUID = 4869172031278825922L;
    private String username;
    private String password;
    private GrantedAuthority authority;

    public DefaultUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authority = user.getAuthority();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(authority);
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
