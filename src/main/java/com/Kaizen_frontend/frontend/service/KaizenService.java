package com.Kaizen_frontend.frontend.service;

import com.Kaizen_frontend.frontend.domain.Kaizen;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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

    public Kaizen findKaizenById(int kaizenId) {
        Kaizen kaizens = webClient.get()
                .uri("/kaizens/kaizenId/{kaizenId}", kaizenId)
                .retrieve()
                .bodyToMono(Kaizen.class)
                .block();

        return kaizens;
    }
}
