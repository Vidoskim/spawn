package ru.vidoskim.spawn;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import lombok.SneakyThrows;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ru.vidoskim.spawn.command.SpawnCommand;
import ru.vidoskim.spawn.service.Service;
import ru.vidoskim.spawn.service.SpawnService;
import ru.vidoskim.spawn.service.impl.SpawnServiceImpl;
import ru.vidoskim.spawn.util.DaoCreatorUtil;
import ru.vidoskim.spawn.util.LiteCommandUtil;
import ru.vidoskim.spawn.util.TableCreatorUtil;

import java.util.HashMap;
import java.util.Map;

public final class Spawn extends JavaPlugin {
    private final Map<Class<?>, Object> serviceMap = new HashMap<>();

    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    private JdbcPooledConnectionSource connectionSource;
    private final FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        connectToDatabase(
                config.getString("mysql.host"),
                config.getString("mysql.database"),
                config.getString("mysql.user"),
                config.getString("mysql.pass"));

        registerService(SpawnService.class, new SpawnServiceImpl(this, getDao(ru.vidoskim.spawn.model.Spawn.class)));

        new LiteCommandUtil(config).create(
                new SpawnCommand(miniMessage, (SpawnService) getService(SpawnService.class))
        );
    }

    @Override
    @SneakyThrows
    public void onDisable() {
        if(connectionSource != null) connectionSource.close();
    }

    @SneakyThrows
    private void connectToDatabase(String host, String database, String user, String pass) {
        connectionSource = new JdbcPooledConnectionSource("jdbc:mysql://" + host + "/" + database + "?useSSL=true&autoReconnect=true");
        connectionSource.setUsername(user);
        connectionSource.setPassword(pass);
        connectionSource.setMaxConnectionsFree(5);

        TableCreatorUtil.create(connectionSource);
        DaoCreatorUtil.create(connectionSource);
    }

    @SuppressWarnings("all")
    public Object getService(Class<?> serviceClass) {
        Object object = serviceMap.get(serviceClass);
        if(object instanceof Service) {
            return object;
        }
        return null;
    }

    @SuppressWarnings("all")
    private void registerService(Class<?> serviceClass, Object service) {
        ((Service) service).enable();
        serviceMap.put(serviceClass, service);
    }

    @SuppressWarnings("all")
    private  <D extends Dao<T, ?>, T> D getDao(Class<T> daoClass) {
        return DaoManager.lookupDao(connectionSource, daoClass);
    }
}
