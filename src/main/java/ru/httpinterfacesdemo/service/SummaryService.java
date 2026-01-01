package ru.httpinterfacesdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.httpinterfacesdemo.client.PetClient;
import ru.httpinterfacesdemo.client.StoreClient;
import ru.httpinterfacesdemo.model.SummaryResponse;

@Service
public class SummaryService {

    private final PetClient petClient;
    private final StoreClient storeClient;

    // Явный конструктор для внедрения зависимостей
    public SummaryService(PetClient petClient, StoreClient storeClient) {
        this.petClient = petClient;
        this.storeClient = storeClient;
    }

    public Mono<SummaryResponse> getSummary(Long petId, Long storeId) {
        return Mono.zip(petClient.getPetById(petId), storeClient.getStoreById(storeId))
                .map(tuple -> new SummaryResponse(tuple.getT1(), tuple.getT2()));
    }
}