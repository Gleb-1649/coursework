package storage.postgres;

import core.dao.LabWorkDao;
import core.objects.Coordinates;
import core.objects.LabWork;
import core.objects.Person;
import core.enums.Difficulty;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JDBC‐реализация LabWorkDao для PostgreSQL.
 */
public class PostgresLabWorkDao implements LabWorkDao {

    @Override
    public Optional<Long> insert(LabWork lw) {
        String sql = "INSERT INTO labworks " +
                "(name,x,y,creation_date,minimal_point,description,difficulty," +
                "author_name,author_weight,author_eye_color,author_hair_color,author_nationality,owner_login) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING id";
        try (Connection c = PostgresConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, lw.getName());
            ps.setDouble(2, lw.getCoordinates().getX());
            ps.setLong(3, lw.getCoordinates().getY());
            ps.setDate(4, Date.valueOf(LocalDate.now()));
            ps.setLong(5, lw.getMinimalPoint());
            ps.setString(6, lw.getDescription());
            ps.setString(7, lw.getDifficulty() == null ? null : lw.getDifficulty().name());

            Person a = lw.getAuthor();
            ps.setString(8, a.getName());
            ps.setInt(9, a.getWeight());
            ps.setString(10, a.getEyeColor() == null ? "" : a.getEyeColor().name());
            ps.setString(11, a.getHairColor().name());
            ps.setString(12, a.getNationality().name());

            ps.setString(13, lw.getOwnerLogin());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(rs.getLong(1));
            } else {
                return Optional.empty();
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Ошибка при вставке LabWork", ex);
        }
    }

    @Override
    public boolean update(LabWork lw) {
        String sql = "UPDATE labworks SET " +
                "name=?, x=?, y=?, minimal_point=?, description=?, difficulty=? " +
                "WHERE id=? AND owner_login=?";
        try (Connection c = PostgresConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, lw.getName());
            ps.setDouble(2, lw.getCoordinates().getX());
            ps.setLong(3, lw.getCoordinates().getY());
            ps.setLong(4, lw.getMinimalPoint());
            ps.setString(5, lw.getDescription());
            ps.setString(6, lw.getDifficulty() == null ? null : lw.getDifficulty().name());
            ps.setLong(7, lw.getId());
            ps.setString(8, lw.getOwnerLogin());

            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            throw new RuntimeException("Ошибка при обновлении LabWork", ex);
        }
    }

    @Override
    public boolean delete(long id, String ownerLogin) {
        String sql = "DELETE FROM labworks WHERE id=? AND owner_login=?";
        try (Connection c = PostgresConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.setString(2, ownerLogin);

            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            throw new RuntimeException("Ошибка при удалении LabWork", ex);
        }
    }

    @Override
    public List<LabWork> fetchAll() {
        String sql = "SELECT * FROM labworks ORDER BY name";
        List<LabWork> list = new ArrayList<>();
        try (Connection c = PostgresConfig.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                LabWork lw = new LabWork(
                        rs.getInt("id"),
                        rs.getString("name"),
                        new Coordinates(
                                rs.getDouble("x"),
                                rs.getLong("y")
                        ),
                        rs.getDate("creation_date").toLocalDate(),
                        rs.getLong("minimal_point"),
                        rs.getString("description"),
                        rs.getString("difficulty") == null
                                ? null
                                : Difficulty.valueOf(rs.getString("difficulty")),
                        new Person(
                                rs.getString("author_name"),
                                rs.getInt("author_weight"),
                                rs.getString("author_eye_color").isEmpty()
                                        ? null
                                        : Enum.valueOf(core.enums.Color.class, rs.getString("author_eye_color")),
                                Enum.valueOf(core.enums.Color.class, rs.getString("author_hair_color")),
                                Enum.valueOf(core.enums.Country.class, rs.getString("author_nationality"))
                        )
                );
                list.add(lw);
            }
            return list;

        } catch (SQLException ex) {
            throw new RuntimeException("Ошибка при выборке всех LabWork", ex);
        }
    }

    @Override
    public Optional<LabWork> findById(long id) {
        String sql = "SELECT * FROM labworks WHERE id=?";
        try (Connection c = PostgresConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return Optional.empty();
            }
            LabWork lw = new LabWork(
                    rs.getInt("id"),
                    rs.getString("name"),
                    new Coordinates(rs.getDouble("x"), rs.getLong("y")),
                    rs.getDate("creation_date").toLocalDate(),
                    rs.getLong("minimal_point"),
                    rs.getString("description"),
                    rs.getString("difficulty") == null
                            ? null
                            : Difficulty.valueOf(rs.getString("difficulty")),
                    new Person(
                            rs.getString("author_name"),
                            rs.getInt("author_weight"),
                            rs.getString("author_eye_color").isEmpty()
                                    ? null
                                    : Enum.valueOf(core.enums.Color.class, rs.getString("author_eye_color")),
                            Enum.valueOf(core.enums.Color.class, rs.getString("author_hair_color")),
                            Enum.valueOf(core.enums.Country.class, rs.getString("author_nationality"))
                    )
            );
            return Optional.of(lw);

        } catch (SQLException ex) {
            throw new RuntimeException("Ошибка при поиске LabWork по id", ex);
        }
    }
}
