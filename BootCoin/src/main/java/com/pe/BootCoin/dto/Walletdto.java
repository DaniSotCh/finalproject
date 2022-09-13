package com.pe.BootCoin.dto;

import com.pe.BootCoin.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Walletdto {
    private String idUser;
    private List<Transaction> transactionList;
}
