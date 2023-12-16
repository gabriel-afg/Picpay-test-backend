package com.picpay.backend.dto;

import java.math.BigDecimal;

public record TransactionDTO(
        BigDecimal value,
        Long senderId,
        Long receiverId
) {
}
