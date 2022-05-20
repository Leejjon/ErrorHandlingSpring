package net.leejjon.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
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
        try {
            List<String> ids = jdbcTemplate.query(sql,
                    new RowMapper<String>() {
                        @Override
                        @SneakyThrows
                        public String mapRow(ResultSet rs, int rowNum) {
                            return rs.getString("id");
                        }
                    }, param);
            log.info("We retrieved " + ids.size() + " records.");
        } catch (DataAccessException e) {
            log.error("Failed running query: " + sql);
            log.error("Parameters: param=" + param);
            log.error("Exception: ", e);
            throw e;
        }
    }
}
