package com.project.delivery.api;

import com.project.delivery.domain.db.OrderStatus;

import java.math.BigDecimal;
import java.util.Set;

public record OrderDto(
        Long id,
        Long customerId,
        String address,
        BigDecimal totalAmount,
        String courierName,
        OrderStatus status,
        Set<OrderItemDto> orderItemEntities
) {
}
