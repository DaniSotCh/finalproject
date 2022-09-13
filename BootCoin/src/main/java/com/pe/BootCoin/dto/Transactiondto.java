package com.pe.BootCoin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transactiondto {
    private String transactionId;
    private Double amount;
    private String paymentType;
}
