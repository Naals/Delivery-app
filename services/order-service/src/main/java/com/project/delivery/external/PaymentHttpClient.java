package com.project.delivery.external;


import com.project.libs.http.payment.CreatePaymentRequestDto;
import com.project.libs.http.payment.CreatePaymentResponseDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange(
        accept = "application/json",
        contentType = "application/json",
        url = "api/v1/payment"
)
public interface PaymentHttpClient {

    @PostExchange
    CreatePaymentResponseDto createPayment(@RequestBody CreatePaymentRequestDto createPaymentRequestDto);
}
