package Client.controller;

import Client.helper.productorKafka;
import Client.model.Client;
import Client.service.IClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private IClientService service;

    @GetMapping
    public ResponseEntity<Flux<Client>> list() {
        logger.info("Inicio metodo list() de ClientController");
        Flux<Client> lista = null;
        try {
            lista = service.list();

        } catch (Exception e) {
            logger.info("Ocurrio un error " + e.getMessage());

        } finally {
            logger.info("Fin metodo list() de ClientController");
        }
        return new ResponseEntity<Flux<Client>>(lista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Mono<Client>> register(@RequestBody Client client) {
        logger.info("Inicio metodo register() de ClientController");
        Mono<Client> p = null;
        try {
            p = service.register(client);

        } catch (Exception e) {
            logger.info("Ocurrio un error " + e.getMessage());

        } finally {
            logger.info("Fin metodo register() de ClientController");
        }
        return new ResponseEntity<Mono<Client>>(p, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        logger.info("Inicio metodo delete() de ClientController");
        return service.delete(id).map(r -> ResponseEntity.ok().<Void>build()).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/documentNumber/{documentNumber}")
    public ResponseEntity<Mono<Client>> clientbydocumentNumber(@PathVariable("documentNumber") String documentNumber) {
        logger.info("Inicio metodo clientbydocumentNumber() de ClientController");
        Mono<Client> lista = null;
        try {
            lista = service.clientbydocumentNumber(documentNumber);

        } catch (Exception e) {
            logger.info("Ocurrio un error " + e.getMessage());

        } finally {
            logger.info("Fin metodo clientbydocumentNumber() de ClientController");
        }
        return new ResponseEntity<Mono<Client>>(lista, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Mono<Client>> update(@RequestBody Client client) {
        logger.info("Inicio metodo update() de ClientController");
        Mono<Client> p = null;
        try {
            p = service.modify(client);

        } catch (Exception e) {
            logger.info("Ocurrio un error " + e.getMessage());

        } finally {
            logger.info("Fin metodo clientbydocumentNumber() de ClientController");
        }
        return new ResponseEntity<Mono<Client>>(p, HttpStatus.OK);
    }

    public Mono<String> buscarUno() {
        return Mono.just("hola");
    }

    @Autowired
    private productorKafka productorkafkaa;
    @GetMapping("/enviarmensaje/{mensaje}")
    public String enviarMensaje(@PathVariable String mensaje) {
        String mensajefi="mensaje exitoso";
        try {
            productorkafkaa.send(mensaje);
        }catch (Exception e){
            mensajefi="fallo";
        }
        return mensajefi;
    }
}
