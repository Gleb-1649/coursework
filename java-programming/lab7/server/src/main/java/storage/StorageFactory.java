package storage;

public class StorageFactory {
    public static DataStorage create() throws StorageException {
        return new MongoStorage();
    }
}
