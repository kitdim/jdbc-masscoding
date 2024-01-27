package org.example;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 1. Запустить postgres из docker-compose.yaml в Docker/Rancher
 * <p>
 * 2. Добавить необходимую зависиомсть для взаимодействия с бд из Java кода
 * <p>
 * 3. Написать код для чтения и запуска скриптов
 * <p>
 * 4. Запустить скрипт init.sql для инициализации бд
 * <p>
 * 5. Запустить скрипт data.sql для добавления записей в бд
 * <p>
 * 6. Написать метод для запуска prepared statement на отображение
 * всех записей из таблицы с песнями, у которых name будет равен
 * переданному в метод в качестве параметра
 * <p>
 * 7. Написать метод для запуска prepared statement на вставка в таблицу
 * с песнями (параметры: имя песни, длительность, id альбома)
 * <p>
 * 8. Написать метод для удаления по id из таблицы с песнями
 * <p>
 * 9. Написать метод для обновления песни по её имени
 * <p>
 * 10*. Продемонстрировать выполнение запроса, выводящего название альбома
 * и самую короткую композицию среди всех композиций для этого альбома,
 * исключая композиции, для которых данное число менее 50
 */
public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        String dropScript = DbUtils.readResourceFile("drop.sql");
        DbUtils.executeScript(dropScript);

        // 4. Запустить скрипт init.sql для инициализации бд
        String initScript = DbUtils.readResourceFile("init.sql");
        DbUtils.executeScript(initScript);

        // 5. Запустить скрипт data.sql для добавления записей в бд
        String dataScript = DbUtils.readResourceFile("data.sql");
        DbUtils.executeScript(dataScript);

        // 6. Написать метод для запуска prepared statement на отображение всех записей
        // из таблицы с песнями, у которых name будет равен переданному в метод в качестве параметра
        SongRepository.find("Come Together");

        // 7. Написать метод для запуска prepared statement на вставка в таблицу с песнями
        // (параметры: имя песни, длительность, id альбома)
        SongRepository.insert("New Song", 55, 2);

        // 8. Написать метод для удаления по id из таблицы с песнями
        SongRepository.delete(1);
        SongRepository.find("Come Together");

        // 9. Написать метод для обновления песни по её имени
        SongRepository.find("Bohemian Rhapsody");
        SongRepository.update("Bohemian Rhapsody", 999, 3, "Bohemian Rhapsody");
        SongRepository.find("Bohemian Rhapsody");

        // 10*. Продемонстрировать выполнение запроса, выводящего название альбома
        // и самую короткую композицию среди всех композиций для этого альбома, исключая композиции,
        // для которых данное число менее 50
        SongRepository.selectShortestSongFromAlbumGreaterThan5MinutesLong(1);

        DbUtils.executeScript(dropScript);
    }

}
