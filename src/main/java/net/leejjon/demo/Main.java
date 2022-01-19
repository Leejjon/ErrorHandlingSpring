package net.leejjon.demo;

import lombok.SneakyThrows;
import java.sql.SQLException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        try {
            justAMethodInBetween();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void justAMethodInBetween() {
        checkedException();
    }

    @SneakyThrows
    private static void checkedException() {
        if (new Random().nextBoolean()) {
            throw new SQLException();
        }
    }
}
