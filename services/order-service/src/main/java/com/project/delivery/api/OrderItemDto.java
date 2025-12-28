package com.project.delivery.api;

import java.math.BigDecimal;

public record OrderItemDto(
        Long id,
        Long itemId,
        Integer quantity,
        BigDecimal priceAtPurchase
) {
}
