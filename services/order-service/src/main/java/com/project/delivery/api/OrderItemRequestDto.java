package com.project.delivery.api;

public record OrderItemRequestDto(
        Long itemId,
        Integer quantity,
        String name
) {
}
