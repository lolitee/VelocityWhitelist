package com.lolitee.teesvelocitywhitelist;

import Database.commands.TableInitializationCommand;
import Database.providers.H2;
import Database.IDatabase;
import Database.providers.MariaDB;
import com.google.inject.Inject;
import com.lolitee.teesvelocitywhitelist.configuration.ConfigurationManager;
import com.lolitee.teesvelocitywhitelist.events.PlayerChat;
import com.lolitee.teesvelocitywhitelist.events.PlayerJoin;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.UUID;

import static com.lolitee.teesvelocitywhitelist.configuration.ConfigurationManager.*;

@Plugin(
        id = "teesvelocitywhitelist",
        name = "Tees Velocity Whitelist",
        version = BuildConstants.VERSION
)
public class TeesVelocityWhitelist {
    public static ProxyServer server;
    public static Logger logger;

    private final HashMap<UUID, String> tokens = new HashMap<>();
    IDatabase db;
    @Inject
    public TeesVelocityWhitelist(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) throws IOException {
        TeesVelocityWhitelist.server = server;
        TeesVelocityWhitelist.logger = logger;
        Files.createDirectories(dataDirectory);
        new ConfigurationManager(dataDirectory);

        switch (provider) {

            case MYSQL:
                db = new MariaDB(address, username, password, database);
                break;
            case H2:
                db = new H2();
                break;
        }

    }
    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        logger.info("Starting " + TeesVelocityWhitelist.class.getName() + "-" + BuildConstants.VERSION);
        db.executeNonQuery(new TableInitializationCommand());

        server.getEventManager().register(this, new PlayerJoin());
        server.getEventManager().register(this, new PlayerChat());
    }
}
