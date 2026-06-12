package com.pricepulse.core.infrastructure.web.controller;


import com.pricepulse.core.infrastructure.messaging.SupplierPriceReceivedEvent;
import com.pricepulse.core.infrastructure.messaging.producer.PriceUpdateProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/simulations")
public class PriceSimulationController {
    private final PriceUpdateProducer priceUpdateProducer;


    public PriceSimulationController(PriceUpdateProducer priceUpdateProducer) {
        this.priceUpdateProducer = priceUpdateProducer;
    }

    @PostMapping("/price-update")
    public ResponseEntity<String> simulatePriceUpdate(@RequestBody SupplierPriceReceivedEvent event){
        priceUpdateProducer.publishPriceUpdate(event);

        return ResponseEntity.accepted().body("Fiyat güncelleme isteği asenkron olarak işlenmek üzere kuyruğa alındı.");    }
}
