package com.project.delivery.domain.db;

public enum OrderStatus {
    PENDING_PAYMENT,
    PAID,
    PENDING_DELIVERY,
    DELIVERED,
    PAYMENT_FAILED,
}
