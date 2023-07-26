package com.Kaizen_frontend.frontend.domain;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "kaizenId",
        "fillingDate",
        "completed",
        "completionDate",
        "problem",
        "solution",
        "rewarded",
        "userId",
        "rewardId"
})
public class Kaizen {

    @JsonProperty("kaizenId")
    private Integer kaizenId;
    @JsonProperty("fillingDate")
    private LocalDate fillingDate;
    @JsonProperty("completed")
    private Boolean completed;
    @JsonProperty("completionDate")
    private LocalDate completionDate;
    @JsonProperty("problem")
    private String problem;
    @JsonProperty("solution")
    private String solution;
    @JsonProperty("rewarded")
    private Boolean rewarded;
    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("rewardId")
    private Integer rewardId;

    @JsonProperty("kaizenId")
    public Integer getKaizenId() {
        return kaizenId;
    }

    @JsonProperty("kaizenId")
    public void setKaizenId(Integer kaizenId) {
        this.kaizenId = kaizenId;
    }

    @JsonProperty("fillingDate")
    public LocalDate getFillingDate() {
        return fillingDate;
    }

    @JsonProperty("fillingDate")
    public void setFillingDate(LocalDate fillingDate) {
        this.fillingDate = fillingDate;
    }

    @JsonProperty("completed")
    public Boolean getCompleted() {
        return completed;
    }

    @JsonProperty("completed")
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @JsonProperty("completionDate")
    public LocalDate getCompletionDate() {
        return completionDate;
    }

    @JsonProperty("completionDate")
    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    @JsonProperty("problem")
    public String getProblem() {
        return problem;
    }

    @JsonProperty("problem")
    public void setProblem(String problem) {
        this.problem = problem;
    }

    @JsonProperty("solution")
    public String getSolution() {
        return solution;
    }

    @JsonProperty("solution")
    public void setSolution(String solution) {
        this.solution = solution;
    }

    @JsonProperty("rewarded")
    public Boolean getRewarded() {
        return rewarded;
    }

    @JsonProperty("rewarded")
    public void setRewarded(Boolean rewarded) {
        this.rewarded = rewarded;
    }

    @JsonProperty("userId")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonProperty("rewardId")
    public Integer getRewardId() {
        return rewardId;
    }

    @JsonProperty("rewardId")
    public void setRewardId(Integer rewardId) {
        this.rewardId = rewardId;
    }

}