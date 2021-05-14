package com.training.sportsbetting.view.registration.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.training.sportsbetting.domain.Currency;
import com.training.sportsbetting.view.registration.validation.BirthDateConstraint;
import com.training.sportsbetting.view.registration.validation.EmailConstraint;
import com.training.sportsbetting.view.registration.validation.UsernameConstraint;

public final class RegistrationModel {

    @NotNull
    @Size(min = 2, max = 30, message = "Size must be between 2 and 30")
    @Pattern(regexp = "^([\\p{L}])?([\\p{L}]+\\s?)*([\\p{L}])$", message = "First name must contain only letters and single whitespaces between the words.")
    private String firstName;
    @NotNull
    @Size(min = 2, max = 30, message = "Size must be between 2 and 30")
    @Pattern(regexp = "^([\\p{L}])?([\\p{L}]+\\s?)*([\\p{L}])$", message = "Last name must contain only letters and single whitespaces between the words.")
    private String lastName;
    @UsernameConstraint
    private String username;
    @EmailConstraint
    private String email;
    @NotNull
    @Size(min = 8, max = 30, message = "Size must be between 8 and 30")
    @Pattern(regexp = "^(?=.*?\\p{Lu})(?=(.*\\p{Ll})+)(?=(.*[\\d])+).*$", message = "Password must contain at least one uppercase letter one lowercase letter and one digit.")
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @BirthDateConstraint
    private LocalDate birth;
    @NotNull
    private Currency currency;
    private BigDecimal balance;

    public RegistrationModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
