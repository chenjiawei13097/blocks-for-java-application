package org.firefly.provider.springboot.domain.dto;

import java.time.LocalDateTime;

public class BillDTO {
    private Long id;

    private String hotel;
    private String cashier;
    private double expenditure;
    private String good;
    private LocalDateTime createTime;
    private String status;

    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public BillDTO(Long id, String hotel, String cashier, double expenditure, String good,
                   LocalDateTime createTime, String status, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.id = id;
        this.hotel = hotel;
        this.cashier = cashier;
        this.expenditure = expenditure;
        this.good = good;
        this.createTime = createTime;
        this.status = status;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(double expenditure) {
        this.expenditure = expenditure;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }
}
