package net.leejjon.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
public class DatabaseService {
    private JdbcTemplate jdbcTemplate;

    public DatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void runQuery(boolean param) {
        String sql = "select * from records where param = ?";
        List<String> ids = jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("id");
            }
        }, param);

        log.info("We retrieved " + ids.size() + " records.");
    }
}
