package ru.vidoskim.spawn.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.vidoskim.spawn.service.SpawnService;

@RequiredArgsConstructor
public class JoinListener implements Listener {
    private final SpawnService spawnService;

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        spawnService.teleportToSpawn(event.getPlayer());
    }
}
