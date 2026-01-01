package ru.httpinterfacesdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0) // Случайный порт для WireMock
@ActiveProfiles("test")
public class SummaryIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testSummaryEndpoint() {
        // Настройка мока для Pet
        stubFor(get(urlEqualTo("/pets/1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\":1, \"name\":\"Rex\"}")));

        // Настройка мока для Store
        stubFor(get(urlEqualTo("/stores/10"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\":10, \"address\":\"Main St\"}")));

        webTestClient.get()
                .uri("/api/summary/1/10")
                .exchange()
                .expectBody(String.class)
                .consumeWith(response -> System.out.println("🔥 REAL ERROR: " + response.getResponseBody()));

        webTestClient.get().uri("/api/summary/1/10")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.pet.name").isEqualTo("Rex")
                .jsonPath("$.store.address").isEqualTo("Main St");
    }
}