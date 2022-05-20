package net.leejjon.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.*;
@Service
@Slf4j
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
        ps.setString(1, Boolean.toString(extraParam));
        ps.executeQuery();
    }
}
