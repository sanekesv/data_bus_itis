package ru.kpfu.itis.mongo.token;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tokens")
public class Token {

    @Id
    private String value;

    private long liveTime;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(long live_time) {
        this.liveTime = live_time;
    }
}
