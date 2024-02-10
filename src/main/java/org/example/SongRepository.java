package org.example;

import java.sql.*;

@SuppressWarnings("SqlDialectInspection")
public class SongRepository {
    protected static void find(String name) throws SQLException {
        var sql = "SELECT * FROM song WHERE name = ?";
        try (Connection connection = DriverManager.getConnection(DbProps.DB_URL, DbProps.DB_USER, DbProps.DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            var result = stmt.executeQuery();
            if (result.next()) {
                while (result.next()) {
                    var songId = result.getInt("id");
                    var songName = result.getString("name");
                    var songDuration = result.getString("duration");
                    var albumId = result.getInt("album_id");
                    System.out.printf("Song info: [" +
                            "id = %s, " +
                            "songName = %s, " +
                            "songDuration = %s, " +
                            "albumId = %s]%n", songId, songName, songDuration, albumId);
                }
            } else {
                System.out.printf("Song ''%s'' not found", name);
            }
        }
    }

    //7. Написать метод для запуска prepared statement на вставка в таблицу с песнями (параметры: имя песни, длительность, id альбома)
    protected static void insert(String nameSong, int durationSong, int idAlbum) throws SQLException {
        var sql = "INSERT INTO song (name, duration, album_id) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DbProps.DB_URL, DbProps.DB_USER, DbProps.DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nameSong);
            stmt.setInt(2, durationSong);
            stmt.setInt(3, idAlbum);

            if (stmt.executeUpdate() == 1) {
                System.out.println("The value inserted successfully");
            } else {
                System.out.println("The value is not inserted");
            }
        }
    }

    // 8. Написать метод для удаления по id из таблицы с песнями
    protected static void delete(int id) throws SQLException {
        String sql = "DELETE FROM song WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DbProps.DB_URL, DbProps.DB_USER, DbProps.DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            if (stmt.executeUpdate() == 1) {
                System.out.println("The value deleted successfully");
            } else {
                System.out.println("The value is not deleted");
            }
        }
    }

    // 9. Написать метод для обновления песни по её имени
    protected static void update(String newName, int newDuration,
                                 int newIdAlbum, String oldName) throws SQLException {
        var sql = "UPDATE public.song SET name = ?, duration = ?, album_id = ? WHERE name = ?";
        try (Connection connection = DriverManager.getConnection(DbProps.DB_URL, DbProps.DB_USER, DbProps.DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newName);
            stmt.setInt(2, newDuration);
            stmt.setInt(3, newIdAlbum);
            stmt.setString(4, oldName);

            if (stmt.executeUpdate() == 1) {
                System.out.println("The value deleted successfully");
            } else {
                System.out.println("The value is not deleted");
            }
        }
    }

    // 10*. Продемонстрировать выполнение запроса,
    // выводящего название альбома и самую короткую композицию
    // среди всех композиций для этого альбома,
    // исключая композиции, для которых данное число менее 50
    protected static void selectShortestSongFromAlbumGreaterThan5MinutesLong(int albumId) throws SQLException {
        var sql = """
                SELECT a.name AS album_name, s.name AS song_name, s.duration AS song_duration
                FROM album a
                JOIN song s ON a.id = s.album_id
                JOIN (
                    SELECT album_id, MIN(duration) AS min_duration
                    FROM song
                    WHERE duration >= 50
                    GROUP BY album_id
                ) AS sub ON a.id = sub.album_id AND s.duration = sub.min_duration
                WHERE a.id = ?
                """;
        try (Connection connection = DriverManager.getConnection(DbProps.DB_URL, DbProps.DB_USER, DbProps.DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, albumId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                String albumName = resultSet.getString("album_name");
                String songName = resultSet.getString("song_name");
                String songDuration = resultSet.getString("song_duration");
                System.out.printf("Song info: [" +
                        "albumName = %s, " +
                        "songName = %s, " +
                        "songDuration = %s]%n", albumName, songName, songDuration);
            }
        }
    }
}
