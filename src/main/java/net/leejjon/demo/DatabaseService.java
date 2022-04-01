package net.leejjon.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@Slf4j
public class DatabaseService {
    @SneakyThrows
    public void runQuery(boolean extraParam) {
        try {
            if (extraParam) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            log.error("Got error " + e.getMessage() +
                    " on query select * from data where extraParam = " + extraParam, e);
            throw e;
        }
    }
}
