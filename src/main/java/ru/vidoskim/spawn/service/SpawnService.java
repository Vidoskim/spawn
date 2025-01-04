package ru.vidoskim.spawn.service;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface SpawnService extends Service {
    void setSpawn(Location location);

    Location getSpawnLocation();

    void teleportToSpawn(Player player);
}
