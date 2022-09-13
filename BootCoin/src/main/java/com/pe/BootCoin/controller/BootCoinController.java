package com.pe.BootCoin.controller;

import com.pe.BootCoin.dto.Transactiondto;
import com.pe.BootCoin.model.Transaction;
import com.pe.BootCoin.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bootcoin")
public class BootCoinController {
    private static final Logger logger = LoggerFactory.getLogger(BootCoinController.class);
    @Autowired
    private ITransactionService service;

    @GetMapping
    public ResponseEntity<Flux<Transaction>> listar(){
        logger.info("Inicio metodo list() de TransactionController");
        Flux<Transaction> lista = null;
        try {
            lista = service.list();

        } catch (Exception e) {
            logger.info("Ocurrio un error " + e.getMessage());

        }finally {
            logger.info( "Fin metodo list() de TransactionController");
        }
        return new ResponseEntity<Flux<Transaction>>(lista, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Mono<Transaction>> register(@RequestBody Transactiondto checkingdto){
        logger.info("Inicio metodo register() de TransactionController");
        Mono<Transaction> p = null;

        Transaction transactionObj = Transaction.builder()
                .transactionId(checkingdto.getTransactionId())
                .paymentType(checkingdto.getPaymentType())
                .amount(checkingdto.getAmount())
                .build();
        try {
            p = service.register(transactionObj);

        } catch (Exception e) {
            logger.info("Ocurrio un error " + e.getMessage());

        }finally {
            logger.info( "Fin metodo register() de TransactionController");
        }
        return new ResponseEntity<Mono<Transaction>>(p, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        logger.info("Inicio metodo delete() de TransactionController");
        return service.delete(id).map(r->ResponseEntity.ok().<Void>build()).defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PutMapping
    public ResponseEntity<Mono<Transaction>> update(@RequestBody Transaction account){
        logger.info("Inicio metodo update() de TransactionController");
        Mono<Transaction> p = null;
        try {
            p = service.modify(account);

        } catch (Exception e) {
            logger.info("Ocurrio un error " + e.getMessage());
        }finally {
            logger.info( "Fin metodo update() de TransactionController");
        }
        return new ResponseEntity<Mono<Transaction>>(p, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Flux<Transaction>> listTransactionByWalletId(@PathVariable("id") String id){
        logger.info("Inicio metodo listCreditById() de TransactionController");
        Flux<Transaction> transactionFlux = service.findTransactionByWalletId(id);
        logger.info("FIN metodo listCreditById() de TransactionController");
        return new ResponseEntity<Flux<Transaction>>(transactionFlux, HttpStatus.OK);
    }
}
