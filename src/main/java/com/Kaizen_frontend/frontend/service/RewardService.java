package com.Kaizen_frontend.frontend.service;

import com.Kaizen_frontend.frontend.domain.Reward;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RewardService {
    private final WebClient webClient;

    public RewardService(WebClient.Builder builder) {
        webClient = builder.baseUrl("http://localhost:8080/v1/").build();
    }

    public Reward[] getRewards() {
        return webClient.get()
                .uri("rewards")
                .retrieve()
                .bodyToMono(Reward[].class).block();
    }

    public Reward getRewardsById(int rewardId) {
        return webClient.get()
                .uri("rewards/rewardId/{rewardId}", rewardId)
                .retrieve()
                .bodyToMono(Reward.class).block();
    }

    public void saveReward(Reward reward) {

        webClient.post()
                .uri("rewards")
                .body(Mono.just(reward), Reward.class)
                .retrieve()
                .bodyToMono(Reward.class)
                .block();
    }

    public void deleteUser(Reward reward) {
        webClient.delete()
                .uri("rewards/{rewardId}", reward.getRewardId())
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
