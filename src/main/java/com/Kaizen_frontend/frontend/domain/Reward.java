package com.Kaizen_frontend.frontend.domain;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "rewardId",
        "name",
        "price",
        "kaizenId"
})
public class Reward {

    @JsonProperty("rewardId")
    private Integer rewardId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("kaizenId")
    private List<Integer> kaizenId;

    @JsonProperty("rewardId")
    public Integer getRewardId() {
        return rewardId;
    }

    @JsonProperty("rewardId")
    public void setRewardId(Integer rewardId) {
        this.rewardId = rewardId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("price")
    public Integer getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Integer price) {
        this.price = price;
    }

    @JsonProperty("kaizenId")
    public List<Integer> getKaizenId() {
        return kaizenId;
    }

    @JsonProperty("kaizenId")
    public void setKaizenId(List<Integer> kaizenId) {
        this.kaizenId = kaizenId;
    }

}