package storage;

import common.model.Person;
import common.model.User;
import storage.StorageException;

import java.util.*;

public interface DataStorage {
    void registerUser(User u) throws StorageException;
    Optional<User> authenticate(String login, String passwordHash) throws StorageException;
    String insertPerson(Person p, String ownerLogin) throws StorageException;
    List<Person> loadAllPersons() throws StorageException;
    boolean updatePerson(Person p, String ownerLogin) throws StorageException;
    boolean deletePerson(String id, String ownerLogin) throws StorageException;
    void deleteAll(String ownerLogin) throws StorageException;
    int deleteLowerPersons(String thresholdName, String ownerLogin) throws StorageException;
    long countLessThanLocation(String locationName) throws StorageException;
}

