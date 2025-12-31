package com.project.delivery.domain;

import com.project.delivery.api.OrderPaymentRequest;
import com.project.delivery.domain.db.*;
import com.project.delivery.external.PaymentHttpClient;
import com.project.libs.http.order.*;
import com.project.libs.http.payment.CreatePaymentRequestDto;
import com.project.libs.http.payment.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@Service
public class OrderProcessor {

    private final OrderJpaRepository repository;
    private final OrderEntityMapper mapper;
    private final PaymentHttpClient paymentHttpClient;

    public OrderEntity create(CreateOrderRequestDto order) {
        var entity = mapper.toEntity(order);
        calculatePricingForOrder(entity);
        entity.setOrderStatus(OrderStatus.PENDING_PAYMENT);
        return repository.save(entity);
    }

    private void calculatePricingForOrder(OrderEntity entity) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for(OrderItemEntity item: entity.getItems()) {
            var randomPrice = ThreadLocalRandom.current().nextDouble(100,5000);
            item.setPriceAtPurchase(BigDecimal.valueOf(randomPrice));

            totalPrice = item.getPriceAtPurchase()
                    .multiply(BigDecimal.valueOf(item.getQuantity()))
                    .add(totalPrice);
        }
        entity.setTotalAmount(totalPrice);
    }

    public OrderEntity getOrderOrThrow(Long id) {
        var orderEntityOptional = repository.findById(id);
        return orderEntityOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Entity with id `%s` not found".formatted(id))
                );
    }

    public OrderEntity processPayment(Long id, OrderPaymentRequest request) {
        var entity = getOrderOrThrow(id);
        if(!entity.getOrderStatus().equals(OrderStatus.PENDING_PAYMENT)) {
            throw new RuntimeException("Order must be in status PENDING_PAYMENT");
        }

        var response = paymentHttpClient.createPayment(CreatePaymentRequestDto.builder()
                        .orderId(id)
                        .amount(entity.getTotalAmount())
                        .paymentMethod(request.paymentMethod())
                .build());

        var status = response.paymentStatus().equals(PaymentStatus.PAYMENT_SUCCEEDED)
                ? OrderStatus.PAID
                : OrderStatus.PAYMENT_FAILED;

        entity.setOrderStatus(status);

//        if(status.equals(OrderStatus.PAID)) {
//            sendOrderPaidEvent(entity, response);
//        }
        
        return repository.save(entity);

    }

}
