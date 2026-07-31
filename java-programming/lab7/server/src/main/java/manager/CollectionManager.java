package manager;

import common.model.Person;
import storage.DataStorage;
import storage.StorageException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CollectionManager {
    private final DataStorage storage;
    private final CopyOnWriteArrayList<Person> cache = new CopyOnWriteArrayList<>();
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public CollectionManager(DataStorage s) throws StorageException {
        this.storage = s;
        // При старте грузим из Mongo и сохраняем owner внутри utility
        cache.addAll(s.loadAllPersons());
    }

    // --- Методы чтения (readLock) ---
    public String getInfo() {
        rwLock.readLock().lock();
        try {
            return "Type:COWArrayList size=" + cache.size();
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public List<Person> getCollection() {
        rwLock.readLock().lock();
        try {
            return List.copyOf(cache);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public long countLessThanLocation(String locName) {
        rwLock.readLock().lock();
        try {
            return cache.stream()
                    .filter(p -> p.getLocation().getName().compareTo(locName) < 0)
                    .count();
        } finally {
            rwLock.readLock().unlock();
        }
    }

    // --- Методы изменения (writeLock) ---
    public void add(Person p, String login) throws StorageException {
        rwLock.writeLock().lock();
        try {
            // проставляем владельца
            p.setOwner(login);
            String id = storage.insertPerson(p, login);
            p.setId(id);
            cache.add(p);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public boolean update(String id, Person p, String login) throws StorageException {
        rwLock.writeLock().lock();
        try {
            p.setId(id);
            boolean ok = storage.updatePerson(p, login);
            if (ok) {
                for (int i = 0; i < cache.size(); i++) {
                    if (cache.get(i).getId().equals(id)) {
                        // сохраняем owner прежний в объекте
                        p.setOwner(login);
                        cache.set(i, p);
                        break;
                    }
                }
            }
            return ok;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public boolean removeById(String id, String login) throws StorageException {
        rwLock.writeLock().lock();
        try {
            boolean ok = storage.deletePerson(id, login);
            if (ok) {
                cache.removeIf(p -> p.getId().equals(id));
            }
            return ok;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void clear(String login) throws StorageException {
        rwLock.writeLock().lock();
        try {
            storage.deleteAll(login);
            cache.clear();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void shuffle(String login) {
        rwLock.writeLock().lock();
        try {
            java.util.Collections.shuffle(cache);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void sort(String login) {
        rwLock.writeLock().lock();
        try {
            java.util.Collections.sort(cache);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public int removeLowerPersons(String thresholdName, String login) throws StorageException {
        rwLock.writeLock().lock();
        try {
            int cnt = storage.deleteLowerPersons(thresholdName, login);
            cache.removeIf(p ->
                    p.getName().compareTo(thresholdName) < 0 &&
                            login.equals(p.getOwner())
            );
            return cnt;

        } finally {
            rwLock.writeLock().unlock();
        }
    }
}
