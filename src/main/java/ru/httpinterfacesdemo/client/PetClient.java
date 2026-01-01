package ru.httpinterfacesdemo.client;


import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;
import ru.httpinterfacesdemo.model.Pet;

@HttpExchange("/pets")
public interface PetClient {
    @GetExchange("/{id}")
    Mono<Pet> getPetById(@PathVariable("id") Long id);
}