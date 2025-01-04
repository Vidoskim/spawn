package ru.vidoskim.spawn.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vidoskim.spawn.dao.impl.SpawnDaoImpl;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DatabaseTable(tableName = "spawn", daoClass = SpawnDaoImpl.class)
public class Spawn {
    @DatabaseField(generatedId = true, unique = true)
    private int id;

    @DatabaseField(canBeNull = false, columnName = "world_uid")
    private String worldUUID;

    @DatabaseField(canBeNull = false)
    private double x;

    @DatabaseField(canBeNull = false)
    private double y;

    @DatabaseField(canBeNull = false)
    private double z;

    @DatabaseField(canBeNull = false)
    private float yaw;

    @DatabaseField(canBeNull = false)
    private float pitch;
}
