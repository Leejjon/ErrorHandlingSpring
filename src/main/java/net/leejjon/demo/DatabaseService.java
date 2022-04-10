package net.leejjon.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.UUID;

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
            final String uuid = UUID.randomUUID().toString();
            log.error(uuid + " Got error " + e.getMessage() +
                    " on query select * from data where extraParam = " + extraParam, e);
            throw new AlreadyLoggedException(e, uuid);
        }
    }
}
