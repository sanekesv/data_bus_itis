package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.service.TokenServiceMongo;
import ru.kpfu.itis.token.Token;

@Service("TokenService")
@Transactional
public class TokenServiceMongoImpl implements TokenServiceMongo {

    @Autowired
    @Qualifier(value = "mongoTemplate")
    MongoOperations mongoOperations;

    @Override
    public Token findTokenByToken(String token) {
        Query query = new Query(Criteria.where("value").is(token));
        return mongoOperations.findOne(query, Token.class);
    }

    @Override
    public void saveToken(Token token) {
        mongoOperations.save(token);
    }

    @Override
    public void removeToken(String token) {
        Query query = new Query(Criteria.where("value").is(token));
        mongoOperations.remove(query, Token.class);
    }

    @Override
    public void removeDocumentsSeniorLiveTime(long l) {
        Query query = new Query(Criteria.where("liveTime").lt(l));
        mongoOperations.remove(query, Token.class);
    }
}
