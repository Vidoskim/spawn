package ru.vidoskim.spawn.util;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lombok.experimental.UtilityClass;
import ru.vidoskim.spawn.model.Spawn;

import java.sql.SQLException;

@UtilityClass
public class TableCreatorUtil {
    public void create(JdbcPooledConnectionSource connectionSource) throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, Spawn.class);
    }
}
