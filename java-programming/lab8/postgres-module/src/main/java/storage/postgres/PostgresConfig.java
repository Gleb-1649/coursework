package storage.postgres;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Настройка и пул соединений к PostgreSQL.
 */
public class PostgresConfig {
    private static HikariDataSource ds;

    public static synchronized Connection getConnection() throws SQLException {
        if (ds == null) {
            HikariConfig cfg = new HikariConfig();
            cfg.setJdbcUrl("jdbc:postgresql://localhost:5432/lab7db");
            cfg.setUsername("postgres");
            cfg.setPassword("8F1kXFax2Y");
            cfg.setMaximumPoolSize(10);
            ds = new HikariDataSource(cfg);
        }
        return ds.getConnection();
    }
}
