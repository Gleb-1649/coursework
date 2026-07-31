package storage.mongo;

import core.dao.LabWorkDao;
import core.objects.Coordinates;
import core.objects.LabWork;
import core.objects.Person;
import core.enums.Color;
import core.enums.Country;
import core.enums.Difficulty;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MongoLabWorkDao implements LabWorkDao {
    private final MongoCollection<Document> col =
            MongoConfig.getDatabase().getCollection("labworks");

    @Override
    public Optional<Long> insert(LabWork lw) {
        Document d = new Document()
                .append("appId", lw.getId())
                .append("name", lw.getName())
                .append("x", lw.getCoordinates().getX())
                .append("y", lw.getCoordinates().getY())
                .append("creationDate", lw.getCreationDate().toString())
                .append("minimalPoint", lw.getMinimalPoint())
                .append("description", lw.getDescription())
                .append("difficulty", lw.getDifficulty()==null ? null : lw.getDifficulty().name())
                .append("authorName", lw.getAuthor().getName())
                .append("authorWeight", lw.getAuthor().getWeight())
                .append("authorEyeColor", lw.getAuthor().getEyeColor()==null?"":lw.getAuthor().getEyeColor().name())
                .append("authorHairColor", lw.getAuthor().getHairColor().name())
                .append("authorNationality", lw.getAuthor().getNationality().name())
                .append("owner", lw.getOwnerLogin());
        col.insertOne(d);
        return Optional.of((long) lw.getId());
    }

    @Override
    public boolean update(LabWork lw) {
        Bson filter = Filters.and(
                Filters.eq("appId", lw.getId()),
                Filters.eq("owner", lw.getOwnerLogin())
        );
        Bson updates = Updates.combine(
                Updates.set("name", lw.getName()),
                Updates.set("x", lw.getCoordinates().getX()),
                Updates.set("y", lw.getCoordinates().getY()),
                Updates.set("minimalPoint", lw.getMinimalPoint()),
                Updates.set("description", lw.getDescription()),
                Updates.set("difficulty", lw.getDifficulty()==null?null:lw.getDifficulty().name()),
                Updates.set("authorName", lw.getAuthor().getName()),
                Updates.set("authorWeight", lw.getAuthor().getWeight()),
                Updates.set("authorEyeColor", lw.getAuthor().getEyeColor()==null?"":lw.getAuthor().getEyeColor().name()),
                Updates.set("authorHairColor", lw.getAuthor().getHairColor().name()),
                Updates.set("authorNationality", lw.getAuthor().getNationality().name())
        );
        UpdateResult res = col.updateOne(filter, updates);
        return res.getModifiedCount() == 1;
    }

    @Override
    public boolean delete(long id, String owner) {
        Bson filter = Filters.and(
                Filters.eq("appId", id),
                Filters.eq("owner", owner)
        );
        DeleteResult res = col.deleteOne(filter);
        return res.getDeletedCount() == 1;
    }

    @Override
    public List<LabWork> fetchAll() {
        List<LabWork> out = new ArrayList<>();
        for (Document d : col.find()) {
            int    id    = d.getInteger("appId");
            String name  = d.getString("name");
            double x     = d.getDouble("x");
            long   y     = d.getLong("y");
            LocalDate dt = LocalDate.parse(d.getString("creationDate"));
            long   minp  = d.getLong("minimalPoint");
            String desc  = d.getString("description");
            String diffs = d.getString("difficulty");
            Difficulty diff = diffs==null?null:Difficulty.valueOf(diffs);
            Person author = new Person(
                    d.getString("authorName"),
                    d.getInteger("authorWeight"),
                    d.getString("authorEyeColor").isEmpty() ? null : Color.valueOf(d.getString("authorEyeColor")),
                    Color.valueOf(d.getString("authorHairColor")),
                    Country.valueOf(d.getString("authorNationality"))
            );
            LabWork lw = new LabWork(id, name, new Coordinates(x,y), dt, minp, desc, diff, author);
            out.add(lw);
        }
        out.sort(null);
        return out;
    }

    @Override
    public Optional<LabWork> findById(long id) {
        Document d = col.find(Filters.eq("appId", id)).first();
        if (d==null) return Optional.empty();
        int    iid   = d.getInteger("appId");
        String name  = d.getString("name");
        double x     = d.getDouble("x");
        long   y     = d.getLong("y");
        LocalDate dt = LocalDate.parse(d.getString("creationDate"));
        long   minp  = d.getLong("minimalPoint");
        String desc  = d.getString("description");
        String diffs = d.getString("difficulty");
        Difficulty diff = diffs==null?null:Difficulty.valueOf(diffs);
        Person author = new Person(
                d.getString("authorName"),
                d.getInteger("authorWeight"),
                d.getString("authorEyeColor").isEmpty() ? null : Color.valueOf(d.getString("authorEyeColor")),
                Color.valueOf(d.getString("authorHairColor")),
                Country.valueOf(d.getString("authorNationality"))
        );
        LabWork lw = new LabWork(iid, name, new Coordinates(x,y), dt, minp, desc, diff, author);
        return Optional.of(lw);
    }
}
