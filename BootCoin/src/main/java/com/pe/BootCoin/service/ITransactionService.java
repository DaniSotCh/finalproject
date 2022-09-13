package com.pe.BootCoin.service;

import com.pe.BootCoin.dto.Transactiondto;
import com.pe.BootCoin.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionService extends ICRUD<Transaction, String>{
    public Flux<Transaction> findTransactionByWalletId(String walletId);
}