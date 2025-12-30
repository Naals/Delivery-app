package com.project.delivery.api;

import java.util.Set;

public record CreateOrderRequestDto(
        Long customerId,
        String address,
        Set<OrderItemRequestDto> orderItemEntities
) {
}
