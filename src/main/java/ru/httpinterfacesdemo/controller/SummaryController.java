package ru.httpinterfacesdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.httpinterfacesdemo.model.SummaryResponse;
import ru.httpinterfacesdemo.service.SummaryService;

@RestController
@RequestMapping("/api")
public class SummaryController {

    private final SummaryService summaryService;

    // Явный конструктор для внедрения зависимости
    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @GetMapping("/summary/{petId}/{storeId}")
    public Mono<SummaryResponse> getSummary(@PathVariable Long petId, @PathVariable Long storeId) {
        return summaryService.getSummary(petId, storeId);
    }
}