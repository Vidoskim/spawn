package ru.vidoskim.spawn.command;

import dev.rollczi.litecommands.annotations.async.Async;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import ru.vidoskim.spawn.service.SpawnService;

@RequiredArgsConstructor
@Command(name = "spawn")
public class SpawnCommand {
    private final MiniMessage miniMessage;
    private final SpawnService spawnService;

    @Async
    @Execute
    void execute(@Context Player sender) {
        spawnService.teleportToSpawn(sender);
    }

    @Async
    @Execute(name = "set")
    @Permission("spawn.set")
    void set(@Context Player sender) {
        spawnService.setSpawn(sender.getLocation());
        sender.sendMessage(miniMessage.deserialize("<green>Spawn успешно установлен"));
    }
}
