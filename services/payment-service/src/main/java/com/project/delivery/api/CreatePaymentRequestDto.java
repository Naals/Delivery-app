package com.project.delivery.api;

import com.project.delivery.domain.db.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CreatePaymentRequestDto(
        Long orderId,
        PaymentMethod paymentMethod,
        BigDecimal amount
) {
}
