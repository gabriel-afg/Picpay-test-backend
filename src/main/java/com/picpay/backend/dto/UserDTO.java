package com.picpay.backend.dto;

import com.picpay.backend.domain.user.UserType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


public record UserDTO(
        String firstName,
        String lastName,
        String document,
        BigDecimal balance,
        String email,
        String password,
        UserType userType

){
    public UserDTO(UserDTO user, String password) {
        this(
                user.firstName(),
                user.lastName(),
                user.document(),
                user.balance(),
                user.email(),
                password,
                user.userType()
        );
    }
}
