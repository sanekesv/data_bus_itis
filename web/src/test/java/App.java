import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.kpfu.jbl.auth.config.SpringMongoConfig;
import ru.kpfu.jbl.auth.domain.Token;

public class App {
    public static void main(String[] args) {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
//        AuthenticationWithToken authToken =
//                new AuthenticationWithToken(
//                        new User(),
//                        null,
//                        null);

//        Authentication authentication = new AuthenticationWithToken(new User(), null);
//
//        Token token = new Token("test", authentication);
//        token.setValue();
//        token.setAuthentication(authentication);
//        token.setLiveTime(641658);
//        mongoOperation.save(token);
        Query query = new Query(Criteria.where("value").is("NzIxNzNmYTAtNjE0My00MjM1LWE1ODctYjA0OWY5MzJhMzc4OjE0MzYyNzI4Mjg0OTU="));
        Token savedToken = mongoOperation.findOne(query, Token.class);
        System.out.println(savedToken.getAuthentication());
    }
}