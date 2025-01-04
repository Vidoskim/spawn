package ru.vidoskim.spawn.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import ru.vidoskim.spawn.dao.SpawnDao;
import ru.vidoskim.spawn.listener.JoinListener;
import ru.vidoskim.spawn.listener.RespawnListener;
import ru.vidoskim.spawn.model.Spawn;
import ru.vidoskim.spawn.service.SpawnService;

import java.util.UUID;

@RequiredArgsConstructor
public class SpawnServiceImpl implements SpawnService {
    private final ru.vidoskim.spawn.Spawn plugin;
    private final SpawnDao spawnDao;

    @Override
    public void enable() {
        plugin.getServer().getPluginManager().registerEvents(new JoinListener(this), plugin);
        plugin.getServer().getPluginManager().registerEvents(new RespawnListener(this), plugin);
    }

    @SneakyThrows
    private void save(Spawn spawn) {
        spawnDao.save(spawn);
    }

    @SneakyThrows
    private Spawn get() {
        return spawnDao.get();
    }

    @Override
    @SneakyThrows
    public void setSpawn(Location location) {
        Spawn spawn = get();

        double x = location.getX();
        double y = location.getY()  ;
        double z = location.getZ();

        float yaw = location.getYaw();
        float pitch = location.getPitch();

        if(spawn == null) {
            spawn = Spawn.builder()
                    .worldUUID(location.getWorld().getUID().toString())
                    .x(x).y(y).z(z)
                    .yaw(yaw)
                    .pitch(pitch)
                    .build();
            save(spawn);
        } else {
            spawn.setX(x);
            spawn.setY(y);
            spawn.setZ(z);
            spawn.setYaw(yaw);
            spawn.setPitch(pitch);
        }

        save(spawn);
    }

    @Override
    @SneakyThrows
    public Location getSpawnLocation() {
        Spawn spawn = spawnDao.get();

        if(spawn == null) return null;

        World world = Bukkit.getWorld(UUID.fromString(spawn.getWorldUUID()));

        if(world == null) return null;

        return new Location(world, spawn.getX(), spawn.getY(), spawn.getZ(), spawn.getYaw(), spawn.getPitch());
    }

    @Override
    public void teleportToSpawn(Player player) {
        Location spawn = getSpawnLocation();

        if(spawn == null) return;

        player.teleportAsync(spawn);
    }
}
