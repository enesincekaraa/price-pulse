package com.pricepulse.core.infrastructure.messaging.producer;


import com.pricepulse.core.infrastructure.configuration.RabbitMQConfig;
import com.pricepulse.core.infrastructure.messaging.SupplierPriceReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PriceUpdateProducer {

    private static final Logger log = LoggerFactory.getLogger(PriceUpdateProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public PriceUpdateProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void publishPriceUpdate(SupplierPriceReceivedEvent event){
        log.info("Kuyruğa mesaj gönderiliyor - SKU: {}, Yeni Fiyat: {}", event.getSku(), event.getNewBasePrice());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                event
        );
        log.info("Mesaj başarıyla kuyruğa iletildi.");
    }
}
