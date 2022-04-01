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
    public void runQuery() {
        if (new Random().nextBoolean()) {
            throw new SQLException();
        }
    }
}
