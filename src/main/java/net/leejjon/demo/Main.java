package net.leejjon.demo;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        BusinessLogic businessLogic = new BusinessLogic(new DatabaseService());
        Controller controller = new Controller(businessLogic);
        controller.callApi();
    }

    static class Controller {
        private final BusinessLogic businessLogic;

        public Controller(BusinessLogic businessLogic) {
            this.businessLogic = businessLogic;
        }

        public String callApi() {
            try {
                businessLogic.doBusinessLogic();
                return "200";
            } catch (Exception e) {
                e.printStackTrace();
                return "500";
            }
        }
    }

    static class BusinessLogic {
        private final DatabaseService databaseService;

        public BusinessLogic(DatabaseService databaseService) {
            this.databaseService = databaseService;
        }

        public void doBusinessLogic() {
            databaseService.runQuery(true);
        }
    }

    static class DatabaseService {
        @SneakyThrows
        public void runQuery(boolean extraParam) {
            Connection con = DriverManager.getConnection(
                    "jdbc:hsqldb:mem:testdb;DB_CLOSE_DELAY=-1"
            ); // Some simple in memory db
            String sql = "select * from records where extraParam = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, Boolean.toString(extraParam));
            ps.executeQuery();
            con.close();
        }
    }
}
