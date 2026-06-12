package com.pricepulse.core.infrastructure.messaging;

import java.io.Serializable;
import java.math.BigDecimal;

public class SupplierPriceReceivedEvent implements Serializable {

    private String sku;
    private BigDecimal newBasePrice;
    private double profitMargin;
    private double taxRate;

    public SupplierPriceReceivedEvent() {
    }

    public SupplierPriceReceivedEvent(String sku, BigDecimal newBasePrice, double profitMargin, double taxRate) {
        this.sku = sku;
        this.newBasePrice = newBasePrice;
        this.profitMargin = profitMargin;
        this.taxRate = taxRate;
    }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public BigDecimal getNewBasePrice() { return newBasePrice; }
    public void setNewBasePrice(BigDecimal newBasePrice) { this.newBasePrice = newBasePrice; }

    public double getProfitMargin() { return profitMargin; }
    public void setProfitMargin(double profitMargin) { this.profitMargin = profitMargin; }

    public double getTaxRate() { return taxRate; }
    public void setTaxRate(double taxRate) { this.taxRate = taxRate; }

}
