package com.project.delivery.api;


import com.project.libs.http.payment.PaymentMethod;

public record OrderPaymentRequest(
        PaymentMethod paymentMethod
) {}
