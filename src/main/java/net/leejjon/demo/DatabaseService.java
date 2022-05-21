package net.leejjon.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

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
            final String uuid = UUID.randomUUID().toString();
            log.error(uuid + "Failed running query: " + sql);
            log.error(uuid + "Parameters: param=" + param);
            log.error(uuid + "Exception: ", e);
            throw new AlreadyLoggedException(e, uuid);
        }
    }
}

