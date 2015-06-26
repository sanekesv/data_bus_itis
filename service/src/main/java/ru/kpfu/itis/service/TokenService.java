package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.mongo.token.Token;

/**
 * Created by ermolaev.a on 26.06.2015.
 */
@Service
public interface TokenService {

    Token findTokenByToken(String token);

    void saveToken(Token token);

    void deleteToken(Token token);
}
