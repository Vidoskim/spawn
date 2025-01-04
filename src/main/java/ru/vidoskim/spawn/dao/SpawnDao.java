package ru.vidoskim.spawn.dao;

import com.j256.ormlite.dao.Dao;
import ru.vidoskim.spawn.model.Spawn;

import java.sql.SQLException;

@SuppressWarnings("all")
public interface SpawnDao extends Dao<Spawn, Long> {
    Spawn save(Spawn spawn) throws SQLException;

    Spawn get() throws SQLException;
}
