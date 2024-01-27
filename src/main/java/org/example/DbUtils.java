package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.stream.Collectors;

// 3. Написать код для чтения и запуска скриптов
public class DbUtils {

    public static String readResourceFile(String fileName) throws IOException {
        var inputStream = Main.class.getClassLoader().getResourceAsStream(fileName);
        assert inputStream != null;
        try (var reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    static void executeScript(String sqlScript) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DbProps.DB_URL, DbProps.DB_USER, DbProps.DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            Arrays.stream(sqlScript.split(";")).forEach(
                    s -> {
                        try {
                            statement.execute(s);
                            System.out.printf("Script ---%s--- was executed successfully %n", s.trim());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }
}
