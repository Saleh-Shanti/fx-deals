package com.sshanti.datawarehouse.dto;

import com.sshanti.datawarehouse.entity.FXDeal;

import java.math.BigDecimal;
import java.time.Instant;

public class FXDealDTO {

    private long dealId;

    private String from;

    private String to;

    private Instant date;

    private BigDecimal dealAmount;

    public FXDealDTO() {
    }

    public FXDealDTO(FXDeal deal) {
        this.dealId = deal.getId();
        this.from = deal.getFromCurrency();
        this.to = deal.getToCurrency();
        this.date = deal.getTimestamp();
        this.dealAmount = deal.getAmount();
    }

    public long getDealId() {
        return dealId;
    }

    public void setDealId(long dealId) {
        this.dealId = dealId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public BigDecimal getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(BigDecimal dealAmount) {
        this.dealAmount = dealAmount;
    }
}
