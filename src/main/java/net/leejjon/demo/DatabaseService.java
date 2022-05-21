package net.leejjon.demo;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class DatabaseService {
    final Connection con;

    public DatabaseService() throws SQLException {
        con = DriverManager.getConnection(
            "jdbc:hsqldb:mem:testdb;DB_CLOSE_DELAY=-1"
        );
    }

    @SneakyThrows
    public void runQuery(boolean extraParam) {
        String sql = "select * from records where extraParam = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setBoolean(1, extraParam);
        ps.executeQuery();
    }
}
