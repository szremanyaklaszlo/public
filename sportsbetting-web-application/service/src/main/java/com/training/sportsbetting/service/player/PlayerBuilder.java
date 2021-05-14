package com.training.sportsbetting.service.player;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.training.sportsbetting.domain.Currency;
import com.training.sportsbetting.domain.Player;

@Service
public class PlayerBuilder {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private LocalDate birth;
    private Currency currency;
    private BigDecimal balance;
    private GrantedAuthority grantedAuthority;

    public PlayerBuilder() {
        super();
    }

    public PlayerBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PlayerBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PlayerBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public PlayerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public PlayerBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public PlayerBuilder setBirth(LocalDate birth) {
        this.birth = birth;
        return this;
    }

    public PlayerBuilder setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public PlayerBuilder setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public PlayerBuilder setGrantedAuthority(GrantedAuthority grantedAuthority) {
        this.grantedAuthority = grantedAuthority;
        return this;
    }

    public Player build() {
        return new Player(
                username,
                email,
                password,
                grantedAuthority,
                firstName,
                lastName,
                balance,
                currency,
                birth);
    }

}
