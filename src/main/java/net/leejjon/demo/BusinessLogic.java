package net.leejjon.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Random;

@Component
public class BusinessLogic {
    private final DatabaseService databaseService;

    @Autowired
    public BusinessLogic(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public void doBusinessLogic() {
        // TODO: Pretend there is happening some logic
        databaseService.runQuery(new Random().nextBoolean());
    }
}
