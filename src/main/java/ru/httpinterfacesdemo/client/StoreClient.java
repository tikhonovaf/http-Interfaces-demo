package ru.httpinterfacesdemo.client;


import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;
import ru.httpinterfacesdemo.model.Store;

@HttpExchange("/stores")
public interface StoreClient {
    @GetExchange("/{id}")
    Mono<Store> getStoreById(@PathVariable("id") Long id);
}