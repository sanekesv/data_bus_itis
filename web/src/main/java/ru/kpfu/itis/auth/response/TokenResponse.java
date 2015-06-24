package ru.kpfu.itis.auth.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse {
    @JsonProperty
    private String token;

    @JsonProperty("live_time")
    private Long liveTime;

    public TokenResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(Long liveTime) {
        this.liveTime = liveTime;
    }

    public TokenResponse(String token, Long liveTime) {
        this.token = token;
        this.liveTime = liveTime;
    }
}