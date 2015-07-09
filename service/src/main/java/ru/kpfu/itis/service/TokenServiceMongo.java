package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.token.Token;

/**
 * Created by ermolaev.a on 26.06.2015.
 */
@Service
public interface TokenServiceMongo {

    Token findTokenByToken(String token);

    void saveToken(Token token);

    void removeToken(String token);

    void removeDocumentsSeniorLiveTime(long l);
}
