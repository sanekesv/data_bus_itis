import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.kpfu.itis.config.SpringMongoConfig;
import ru.kpfu.itis.mongo.token.Token;

/**
 * Created by ermolaev.a on 26.06.2015.
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        Token token = new Token();
        token.setValue("test");
        token.setLiveTime(641658);
        mongoOperation.save(token);
        Query query = new Query(Criteria.where("value").is("test"));
        Token savedToken = mongoOperation.findOne(query, Token.class);
        System.out.println(savedToken.getLiveTime());
    }
}
