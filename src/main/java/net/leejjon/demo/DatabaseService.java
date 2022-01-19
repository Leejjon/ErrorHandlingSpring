package net.leejjon.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Random;

@Service
@Slf4j
public class DatabaseService {
    @SneakyThrows
    public void runQuery(boolean withFilter) {
        try {
            if (new Random().nextBoolean()) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            log.debug("Got error " + e.getMessage() +
                    " on query select * from data where filter = " + withFilter, e);
            throw new AlreadyLoggedException(e);
        }
    }
}
