package com.pricepulse.core.infrastructure.messaging.consumer;

import com.pricepulse.core.application.usecase.UpdateProductPriceUseCase;
import com.pricepulse.core.infrastructure.configuration.RabbitMQConfig;
import com.pricepulse.core.infrastructure.messaging.SupplierPriceReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PriceUpdateConsumer {

    private static final Logger log = LoggerFactory.getLogger(PriceUpdateConsumer.class);
    private final UpdateProductPriceUseCase updateProductPriceUseCase;

    public PriceUpdateConsumer(UpdateProductPriceUseCase updateProductPriceUseCase) {
        this.updateProductPriceUseCase = updateProductPriceUseCase;
    }


    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consumerPriceUpdateEvent(SupplierPriceReceivedEvent event){
        log.info("Kuyruktan yeni fiyat güncelleme mesajı alındı - SKU: {}, Yeni Maliyet: {}",
                event.getSku(), event.getNewBasePrice());

        try {
            updateProductPriceUseCase.execute(
                    event.getSku(),
                    event.getNewBasePrice(),
                    event.getProfitMargin(),
                    event.getTaxRate()
            );
            log.info("SKU: {} için dinamik fiyatlama başarıyla hesaplandı ve veritabanına işlendi.", event.getSku());
        }catch (IllegalArgumentException | IllegalStateException e){
            log.error("Fiyat güncellenirken iş kuralı hatası oluştu: {}", e.getMessage());

        }catch (Exception e){
            log.error("Beklenmeyen bir sistem hatası oluştu: ", e);
        }

    }



}
