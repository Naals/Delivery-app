package com.project.delivery.api;

import com.project.delivery.domain.db.PaymentMethod;
import com.project.delivery.domain.db.PaymentStatus;

import java.math.BigDecimal;

public record CreatePaymentResponseDto(
        Long paymentId,
        PaymentStatus paymentStatus,
        Long orderId,
        PaymentMethod paymentMethod,
        BigDecimal amount
) {
}