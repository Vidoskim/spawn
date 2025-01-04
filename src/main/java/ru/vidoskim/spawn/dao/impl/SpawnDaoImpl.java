package ru.vidoskim.spawn.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import ru.vidoskim.spawn.dao.SpawnDao;
import ru.vidoskim.spawn.model.Spawn;

import java.sql.SQLException;

public class SpawnDaoImpl extends BaseDaoImpl<Spawn, Long> implements SpawnDao {
    public SpawnDaoImpl(ConnectionSource connectionSource, Class<Spawn> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    @Override
    public Spawn save(Spawn spawn) throws SQLException {
        createOrUpdate(spawn);
        return spawn;
    }

    @Override
    public Spawn get() throws SQLException {
        return queryForId(1L);
    }
}
