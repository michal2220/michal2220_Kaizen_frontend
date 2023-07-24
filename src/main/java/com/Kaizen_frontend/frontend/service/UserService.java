package com.Kaizen_frontend.frontend.service;

import com.Kaizen_frontend.frontend.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class UserService {

    private final WebClient webClient;

    public UserService(WebClient.Builder builder) {
        webClient = builder.baseUrl("http://localhost:8080/v1/").build();
    }

    public User[] getUsers() {
        return webClient.get()
                .uri("users")
                .retrieve()
                .bodyToMono(User[].class).block();
    }
}
