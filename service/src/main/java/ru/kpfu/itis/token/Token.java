package ru.kpfu.itis.token;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.Authentication;

@Document(collection = "tokens")
public class Token {

    @Id
    private String value;

    private Authentication authentication;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Authentication getAuthentication() {
        return authentication;
    }
//
    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public Token(String value, Authentication authentication) {
        this.value = value;
        this.authentication = authentication;
    }
}
