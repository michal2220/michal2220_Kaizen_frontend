package com.Kaizen_frontend.frontend.domain;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "userId",
        "name",
        "lastname",
        "brigade",
        "kaizenList"
})
public class User {

    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("brigade")
    private Integer brigade;
    @JsonProperty("kaizenList")
    private List<Integer> kaizenList;

    @JsonProperty("userId")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("lastname")
    public String getLastname() {
        return lastname;
    }

    @JsonProperty("lastname")
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @JsonProperty("brigade")
    public Integer getBrigade() {
        return brigade;
    }

    @JsonProperty("brigade")
    public void setBrigade(Integer brigade) {
        this.brigade = brigade;
    }

    @JsonProperty("kaizenList")
    public List<Integer> getKaizenList() {
        return kaizenList;
    }

    @JsonProperty("kaizenListSize")
    public int getKaizenListSize() {return kaizenList.size();}

    @JsonProperty("kaizenList")
    public void setKaizenList(List<Integer> kaizenList) {
        this.kaizenList = kaizenList;
    }

}
