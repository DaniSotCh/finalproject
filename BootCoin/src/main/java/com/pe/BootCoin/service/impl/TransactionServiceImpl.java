package com.pe.BootCoin.service.impl;

import com.pe.BootCoin.config.WebClientConfig;
import com.pe.BootCoin.dto.Transactiondto;
import com.pe.BootCoin.model.Transaction;
import com.pe.BootCoin.repo.ITransactionRepo;
import com.pe.BootCoin.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements ITransactionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    @Autowired
    private ITransactionRepo repo;

    @Override
    public Mono<Transaction> register(Transaction obj) {
        return repo.save(obj);
    }

    @Override
    public Mono<Transaction> modify(Transaction obj) {
        return repo.save(obj);
    }

    @Override
    public Flux<Transaction> list() {
        return repo.findAll();
    }

    @Override
    public Mono<Transaction> listofId(String id) {
        Mono<Transaction> tr = repo.findById(id);
        return tr;
    }

    @Override
    public Mono<Transaction> delete(String id) {
        return repo.findById(id).flatMap(r -> repo.delete(r).then(Mono.just(r)));
    }

    @Override
    public Flux<Transaction> findTransactionByWalletId(String walletId) {
        WebClientConfig webconfig = new WebClientConfig();
        return webconfig.setUriData("http://localhost:8087").flatMapMany(
                d -> {
                    logger.info("URL: " + d);
                    Flux<Transaction> transactionMono = webconfig.getWebclient().get().uri("/api/bootcoin/transaction/" + walletId).retrieve().bodyToFlux(Transaction.class);
                    return transactionMono;
                }
        );
    }
}
