package com.sshanti.datawarehouse.boundary.request;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public class FXDealRequest {

    @NotBlank(message = "From Currency Iso Code must not be blank")
    private String fromCurrency;

    @NotBlank(message = "To Currency Iso Code must not be blank")
    private String toCurrency;

    @NotNull(message = "Deal timestamp must not be null")
    @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    @NotNull(message = "Deal amount in ordering currency must not be null")
    private BigDecimal amount;

    public FXDealRequest() {
    }

    public FXDealRequest(String fromCurrency, String toCurrency, Date timestamp, BigDecimal amount) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
