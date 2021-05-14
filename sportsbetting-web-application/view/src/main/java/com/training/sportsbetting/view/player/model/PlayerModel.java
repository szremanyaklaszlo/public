package com.training.sportsbetting.view.player.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.training.sportsbetting.domain.Currency;

public final class PlayerModel {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate birth;
    private final Currency currency;
    private final BigDecimal balance;

    public PlayerModel(Long id, String firstName, String lastName, String username, String email, LocalDate birth, Currency currency,
            BigDecimal balance) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.birth = birth;
        this.currency = currency;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

}
