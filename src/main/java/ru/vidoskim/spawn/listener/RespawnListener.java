package ru.vidoskim.spawn.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import ru.vidoskim.spawn.service.SpawnService;

@RequiredArgsConstructor
public class RespawnListener implements Listener {
    private final SpawnService spawnService;

    @EventHandler
    private void onRespawn(PlayerRespawnEvent event) {
        spawnService.teleportToSpawn(event.getPlayer());
    }
}
