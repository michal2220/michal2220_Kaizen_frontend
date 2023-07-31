package com.Kaizen_frontend.frontend.service;

import com.Kaizen_frontend.frontend.domain.Kaizen;
import com.Kaizen_frontend.frontend.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class KaizenService {

    private final WebClient webClient;

    public KaizenService(WebClient.Builder builder) {
        webClient = builder.baseUrl("http://localhost:8080/v1/").build();
    }

    public Kaizen[] getKaizens() {
        return webClient.get()
                .uri("kaizens")
                .retrieve()
                .bodyToMono(Kaizen[].class).block();
    }

    public Kaizen[] findKaizenById(int kaizenId)  {

        return webClient.get()
                .uri("/kaizens/kaizenId/{kaizenId}", kaizenId)
                .retrieve()
                .bodyToMono(Kaizen[].class)
                .block();
    }

    public Kaizen[] getKaizensOlderThan(LocalDate date) {
        return webClient.get()
                .uri("kaizens/olderThen/{date}", date)
                .retrieve()
                .bodyToMono(Kaizen[].class).block();
    }

    public Kaizen saveKaizen(Kaizen kaizen) {

        return webClient.post()
                .uri("kaizens")
                .body(Mono.just(kaizen), Kaizen.class)
                .retrieve()
                .bodyToMono(Kaizen.class)
                .block();
    }

    public void deleteKaizen(Kaizen kaizen) {
        webClient.delete()
                .uri("kaizens/{kaizenId}", kaizen.getKaizenId())
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public Kaizen[] getKaizensCreatedBy(String name, String lastname) {
        return webClient.get()
                .uri("kaizens/creator?name={name}&lastname={lastname}", name, lastname)
                .retrieve()
                .bodyToMono(Kaizen[].class)
                .block();
    }

    public String getTranslation(int kaizenId) {
        return webClient.get()
                .uri("kaizens/translate/{kaizenId}", kaizenId)
                .retrieve()
                .bodyToMono(String.class)
                .block();

    }
}
