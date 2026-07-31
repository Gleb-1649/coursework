package storage.mongo;

import core.dao.UserDao;
import core.objects.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Optional;

/**
 * Реализация UserDao на MongoDB.
 */
public class MongoUserDao implements UserDao {
    private final MongoCollection<Document> col =
            MongoConfig.getDatabase().getCollection("users");

    @Override
    public Optional<User> findByLogin(String login) {
        Document doc = col.find(Filters.eq("login", login)).first();
        if (doc == null) return Optional.empty();
        return Optional.of(new User(
                doc.getString("login"),
                doc.getString("passwordHash")
        ));
    }

    @Override
    public boolean create(User user) {
        try {
            col.insertOne(new Document("login", user.getLogin())
                    .append("passwordHash", user.getPasswordHash()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
