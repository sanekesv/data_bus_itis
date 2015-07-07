package ru.kpfu.itis.auth;

import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;
import ru.kpfu.itis.service.TokenServiceMongo;
import ru.kpfu.itis.token.Token;

import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.UUID;

public class TokenService {

    //an 3 days
    public static final long TOKEN_LIVE_TIME = 24 * 3 * 60 * 60 * 1000l;
    //private static final Logger logger = LoggerFactory.getLogger(TokenService.class);
   // private static final Cache restApiAuthTokenCache = CacheManager.getInstance().getCache("restApiAuthTokenCache");

    @Autowired
    private TokenServiceMongo tokenService;

//    @Scheduled(fixedRate = TOKEN_LIVE_TIME)
//    public void evictExpiredTokens() {
//        logger.info("Evicting expired tokens");
//        restApiAuthTokenCache.evictExpiredElements();
//    }

    public String generateNewToken() {
        String token = UUID.randomUUID().toString();
        token += ":" + new Date().getTime();
        byte[] base64 = Base64.encode(token.getBytes());
        return new String(base64);
    }

    public void store(String token, Authentication authentication) {
        tokenService.saveToken(new Token(token, authentication));
        //restApiAuthTokenCache.put(new Element(token, authentication));
    }

    public boolean contains(String token) {
        return tokenService.findTokenByToken(token) != null;
    }

    public Authentication retrieve(String token) {
        return tokenService.findTokenByToken(token).getAuthentication();
    }

    public boolean isTokenValid(String token) {
        try {
            String decodedToken = new String(Base64.decode(token.getBytes()));
            String[] parts = decodedToken.split(":");
            Long.valueOf(parts[1]);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isTokenExpired(String token) {
        if (!isTokenValid(token)) {
            throw new IllegalStateException("No valid token");
        }
        String decodedToken = new String(Base64.decode(token.getBytes()));
        String[] parts = decodedToken.split(":");
        long tokenGenTime = Long.valueOf(parts[1]);
        return new Date().getTime() - tokenGenTime > TOKEN_LIVE_TIME;
    }

    @PreDestroy
    public void contextDestroyed() {
        CacheManager.getInstance().shutdown();
    }

}