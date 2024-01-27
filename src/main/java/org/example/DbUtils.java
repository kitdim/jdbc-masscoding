package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.stream.Collectors;

public class Utils {
    public static String readResourceFile(String fileName) throws IOException {
        var inputStream = Main.class.getClassLoader().getResourceAsStream(fileName);
        assert inputStream != null;
        try (var reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    static void executeScript(String sqlScript) {
        try (Connection connection = DriverManager.getConnection(Props.DB_URL, Props.DB_USER, Props.DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute(sqlScript);
        } catch (Exception e) {
            System.out.println("Something went wrong during script execution!");
        }
    }
}
