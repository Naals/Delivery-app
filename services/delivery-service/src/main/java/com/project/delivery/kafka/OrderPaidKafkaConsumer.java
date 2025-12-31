package com.project.delivery.kafka;

import com.project.delivery.domain.DeliveryProcessor;
import com.project.libs.kafka.OrderPaidEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;


@EnableKafka
@Slf4j
@Configuration
@AllArgsConstructor
public class OrderPaidKafkaConsumer {

    private final DeliveryProcessor processor;

    @KafkaListener(
            topics = "${order-paid-topic}",
            containerFactory = "orderPaidEventListenerFactory"
    )
    public void listen(OrderPaidEvent order) {
        log.info("OrderPaidEvent received: {}", order);
        processor.processOrderPaid(order);
    }
}
