package storage;

import com.mongodb.client.*;
import com.mongodb.client.model.IndexOptions;
import common.model.Person;
import common.model.User;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.*;
import static com.mongodb.client.model.Filters.*;

public class MongoStorage implements DataStorage {
    private final MongoClient client = MongoClients.create("mongodb://localhost:27017");
    private final MongoDatabase db = client.getDatabase("lab7db");
    private final MongoCollection<Document> usersCol = db.getCollection("users");
    private final MongoCollection<Document> personsCol = db.getCollection("persons");

    public MongoStorage() throws StorageException {

        usersCol.createIndex(new Document("login",1), new IndexOptions().unique(true));
    }

    @Override
    public void registerUser(User u) throws StorageException {
        try {
            usersCol.insertOne(new Document("login", u.getLogin())
                    .append("pass_hash", u.getPasswordHash()));
        } catch (Exception e) {
            throw new StorageException(e);
        }
    }

    @Override
    public Optional<User> authenticate(String login, String passwordHash) throws StorageException {
        try {
            Document d = usersCol.find(and(eq("login", login), eq("pass_hash", passwordHash))).first();
            return d == null ? Optional.empty() : Optional.of(new User(login, passwordHash));
        } catch (Exception e) {
            throw new StorageException(e);
        }
    }

    @Override
    public String insertPerson(Person p, String ownerLogin) throws StorageException {
        try {
            Document d = new Document("owner", ownerLogin)
                    .append("name", p.getName())
                    .append("coord_x", p.getCoordinates().getX())
                    .append("coord_y", p.getCoordinates().getY())
                    .append("height", p.getHeight())
                    .append("weight", p.getWeight())
                    .append("eye_color", p.getEyeColor() != null ? p.getEyeColor().name() : null)
                    .append("nationality", p.getNationality().name())
                    .append("loc_x", p.getLocation().getX())
                    .append("loc_y", p.getLocation().getY())
                    .append("loc_z", p.getLocation().getZ())
                    .append("loc_name", p.getLocation().getName())
                    // сохраняем настоящий Date, не строку
                    .append("created_at", new java.util.Date());
            personsCol.insertOne(d);
            return d.getObjectId("_id").toHexString();
        } catch (Exception e) {
            throw new StorageException(e);
        }
    }

    @Override
    public List<Person> loadAllPersons() throws StorageException {
        try {
            List<Person> list = new ArrayList<>();
            for (Document d : personsCol.find()) {
                list.add(MongoUtils.documentToPerson(d));
            }
            return list;
        } catch (Exception e) {
            throw new StorageException(e);
        }
    }

    @Override
    public boolean updatePerson(Person p, String ownerLogin) throws StorageException {
        try {
            Document upd = new Document("$set", new Document()
                    .append("name", p.getName())
                    .append("coord_x", p.getCoordinates().getX())
                    .append("coord_y", p.getCoordinates().getY())
                    .append("height", p.getHeight())
                    .append("weight", p.getWeight())
                    .append("eye_color", p.getEyeColor() != null ? p.getEyeColor().name() : null)
                    .append("nationality", p.getNationality().name())
                    .append("loc_x", p.getLocation().getX())
                    .append("loc_y", p.getLocation().getY())
                    .append("loc_z", p.getLocation().getZ())
                    .append("loc_name", p.getLocation().getName())
            );
            var res = personsCol.updateOne(
                    and(eq("_id", new ObjectId(p.getId())), eq("owner", ownerLogin)),
                    upd
            );
            return res.getModifiedCount() > 0;
        } catch (Exception e) {
            throw new StorageException(e);
        }
    }

    @Override
    public boolean deletePerson(String id, String ownerLogin) throws StorageException {
        try {
            var res = personsCol.deleteOne(
                    and(eq("_id", new ObjectId(id)), eq("owner", ownerLogin))
            );
            return res.getDeletedCount() > 0;
        } catch (Exception e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void deleteAll(String ownerLogin) throws StorageException {
        try {
            personsCol.deleteMany(eq("owner", ownerLogin));
        } catch (Exception e) {
            throw new StorageException(e);
        }
    }

    @Override
    public int deleteLowerPersons(String thresholdName, String ownerLogin) throws StorageException {
        try {
            var res = personsCol.deleteMany(
                    and(lt("name", thresholdName), eq("owner", ownerLogin))
            );
            return (int) res.getDeletedCount();
        } catch (Exception e) {
            throw new StorageException(e);
        }
    }

    @Override
    public long countLessThanLocation(String locationName) throws StorageException {
        try {
            return personsCol.countDocuments(lt("loc_name", locationName));
        } catch (Exception e) {
            throw new StorageException(e);
        }
    }
}