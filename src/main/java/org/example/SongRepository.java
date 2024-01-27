package org.example;

import java.sql.*;

@SuppressWarnings("SqlDialectInspection")
public class SongRepository {
    // 6. Написать метод для запуска prepared statement на отображение всех записей из таблицы с песнями, у которых name будет равен переданному в метод в качестве параметра
    protected static void find(String name) throws SQLException {
        try (//получить коннекшен
             //подготовить стейтмент
            ) {
            //установить значение в стейтмент
            //распечатать результат
        }
    }

    // 7. Написать метод для запуска prepared statement на вставка в таблицу с песнями (параметры: имя песни, длительность, id альбома)
    protected static void insert(String nameSong, int durationSong, int idAlbum) throws SQLException {
        try (//получить коннекшен
             //подготовить стейтмент
        ) {
            //установить значения в стейтмент
            //распечатать результат
        }
    }

    // 8. Написать метод для удаления по id из таблицы с песнями
    protected static void delete(int id) throws SQLException {
        try (//получить коннекшен
             //подготовить стейтмент
        ) {
            //установить значение в стейтмент
            //распечатать результат
        }
    }

    // 9. Написать метод для обновления песни по её имени
    protected static void update(String newName, int newDuration, int newIdAlbum, String oldName) throws SQLException {
        try (//получить коннекшен
             //подготовить стейтмент
        ) {
            //установить значение в стейтмент
            //распечатать результат
        }
    }

    // 10*. Продемонстрировать выполнение запроса, выводящего название альбома и самую короткую композицию среди всех композиций для этого альбома, исключая композиции, для которых данное число менее 5
    protected static void selectShortestSongFromAlbumGreaterThan5MinutesLong(int albumId) throws SQLException {
        try (//получить коннекшен
             //подготовить стейтмент
        ) {
            //установить значение в стейтмент
            //распечатать результат
        }
    }
}
