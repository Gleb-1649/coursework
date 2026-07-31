package manager;

import common.model.User;
import storage.DataStorage;
import storage.StorageException;
import java.util.Optional;

public class UserManager {
    private final DataStorage storage;
    public UserManager(DataStorage s) { this.storage = s; }
    public void register(String login, String hash) throws StorageException { storage.registerUser(new User(login,hash)); }
    public Optional<User> authenticate(String login, String hash) throws StorageException { return storage.authenticate(login,hash); }
}
