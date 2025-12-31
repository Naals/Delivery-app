package com.project.libs.http.order;

public record OrderItemRequestDto(
        Long itemId,
        Integer quantity,
        String name
) {
}
