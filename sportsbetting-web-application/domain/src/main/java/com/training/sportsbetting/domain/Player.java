package com.training.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;

import com.training.sportsbetting.domain.converter.LocalDateAttributeConverter;

@Entity
public class Player extends User {
    
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @ColumnDefault(value = "0")
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate birth;

    public Player() {
        super();
    }

    public Player(String username, String email, String password, GrantedAuthority authority, String firstName, String lastName, BigDecimal balance,
            Currency currency,
            LocalDate birth) {
        super(username, email, password, authority);
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.currency = currency;
        this.birth = birth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getBirth() {
        return birth;
    }

}
