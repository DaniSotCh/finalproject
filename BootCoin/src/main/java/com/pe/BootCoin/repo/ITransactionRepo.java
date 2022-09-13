package com.pe.BootCoin.repo;

import com.pe.BootCoin.model.Transaction;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ITransactionRepo extends ReactiveMongoRepository<Transaction, String> {
    @Query(value = "{'transaction.transactionId' : ?0}")
    Flux<Transaction> findByTransactionId(String transactionId);
}