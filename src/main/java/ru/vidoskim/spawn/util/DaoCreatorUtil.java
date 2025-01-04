package ru.vidoskim.spawn.util;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import lombok.experimental.UtilityClass;
import ru.vidoskim.spawn.model.Spawn;

import java.sql.SQLException;

@UtilityClass
public class DaoCreatorUtil {
    public void create(JdbcPooledConnectionSource connectionSource) throws SQLException {
        DaoManager.createDao(connectionSource, Spawn.class);
    }
}
