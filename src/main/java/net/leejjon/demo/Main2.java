package net.leejjon.demo;

import java.sql.SQLException;
import java.util.Random;

public class Main2 {
    public static void main(String[] args) {
        try {
            justAMethodInBetween();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void justAMethodInBetween() throws SQLException {
        checkedException();
    }

    private static void checkedException() throws SQLException {
        if (new Random().nextBoolean()) {
            throw new SQLException();
        }
    }
}
