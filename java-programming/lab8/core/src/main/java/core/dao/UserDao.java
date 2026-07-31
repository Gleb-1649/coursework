package core.dao;

import core.objects.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findByLogin(String login);
    boolean create(User user);
}
