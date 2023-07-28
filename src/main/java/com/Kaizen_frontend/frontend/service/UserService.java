package com.Kaizen_frontend.frontend.service;

import com.Kaizen_frontend.frontend.domain.User;
import com.Kaizen_frontend.frontend.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


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

    public User[] findByLastname(String lastname) throws UserNotFoundException {
        User[] users = webClient.get()
                .uri("users/userLastname/{lastname}", lastname)
                .retrieve()
                .bodyToMono(User[].class)
                .block();

        if (users == null) {
            throw new UserNotFoundException("Users with lastname " + lastname + " were not found.");
        }

        return users;
    }


    public User[] findByKaizenCount(int kaizenCount) {

        User[] users = webClient.get()
                .uri("users/kaizenQuantity/{kaizenCount}", kaizenCount)
                .retrieve()
                .bodyToMono(User[].class)
                .block();
        return users;
    }

    public User[] findWithLessKaizenThen(int kaizenCount) {

        User[] users = webClient.get()
                .uri("users/lessThen/{kaizenCount}", kaizenCount)
                .retrieve()
                .bodyToMono(User[].class)
                .block();
        return users;
    }

    public User[] findWithMoreKaizenThen(int kaizenCount) {

        User[] users = webClient.get()
                .uri("users/moreThen/{kaizenCount}", kaizenCount)
                .retrieve()
                .bodyToMono(User[].class)
                .block();
        return users;
    }

    public User[] findByBrigade(int brigade) {

        User[] users = webClient.get()
                .uri("users/brigade/{brigade}", brigade)
                .retrieve()
                .bodyToMono(User[].class)
                .block();
        return users;
    }


    public void saveUser(User user) {

        webClient.post()
                .uri("users")
                .body(Mono.just(user), User.class)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }


    public void deleteUser(User user) {
        webClient.delete()
                .uri("users/{userId}", user.getUserId())
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
