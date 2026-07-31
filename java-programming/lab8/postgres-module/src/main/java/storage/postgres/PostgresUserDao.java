package storage.postgres;

import core.dao.UserDao;
import core.objects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

/**
 * Реализация UserDao через JDBC+HikariCP.
 */
public class PostgresUserDao implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
        String sql = "SELECT password_hash FROM users WHERE login = ?";
        try (Connection c = PostgresConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return Optional.empty();
            return Optional.of(new User(login, rs.getString(1)));
        } catch (Exception e) {
            throw new RuntimeException("Ошибка чтения пользователя", e);
        }
    }

    @Override
    public boolean create(User user) {
        String sql = "INSERT INTO users(login, password_hash) VALUES(?, ?)";
        try (Connection c = PostgresConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPasswordHash());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            // например, unique_violation
            return false;
        }
    }
}
