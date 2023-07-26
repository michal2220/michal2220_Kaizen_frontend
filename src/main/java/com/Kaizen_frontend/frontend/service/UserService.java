package com.Kaizen_frontend.frontend.service;

import com.Kaizen_frontend.frontend.domain.User;
import com.Kaizen_frontend.frontend.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;


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


    public User[] findByKaizenCount(int kaizenCount) throws UserNotFoundException {

        User[] users = webClient.get()
                .uri("users/kaizenQuantity/{kaizenCount}", kaizenCount)
                .retrieve()
                .bodyToMono(User[].class)
                .block();
        if (users == null) {
            throw new UserNotFoundException("No users with given amount of kaizens");
        }
        return users;
    }

    public User[] findWithLessKaizenThen(int kaizenCount) throws UserNotFoundException {

        User[] users = webClient.get()
                .uri("users/lessThen/{kaizenCount}", kaizenCount)
                .retrieve()
                .bodyToMono(User[].class)
                .block();
        if (users == null) {
            throw new UserNotFoundException("No such users");
        }
        return users;
    }

    public User[] findWithMoreKaizenThen(int kaizenCount) throws UserNotFoundException {

        User[] users = webClient.get()
                .uri("users/moreThen/{kaizenCount}", kaizenCount)
                .retrieve()
                .bodyToMono(User[].class)
                .block();
        if (users == null) {
            throw new UserNotFoundException("No such users");
        }
        return users;
    }

    public User[] finByBrigade(int brigade) throws UserNotFoundException {

        User[] users = webClient.get()
                .uri("users/brigade/{brigade}", brigade)
                .retrieve()
                .bodyToMono(User[].class)
                .block();
        if (users == null) {
            throw new UserNotFoundException("Wrong brigade");
        }
        return users;
    }



}
