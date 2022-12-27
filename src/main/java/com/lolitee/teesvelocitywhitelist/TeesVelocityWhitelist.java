package com.lolitee.teesvelocitywhitelist;

import Database.commands.TableInitializationCommand;
import Database.providers.H2;
import Database.IDatabase;
import Database.providers.MariaDB;
import com.google.inject.Inject;
import com.lolitee.teesvelocitywhitelist.configuration.ConfigurationManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.event.player.PlayerChooseInitialServerEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
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
    private final Path dataDirectory;
    IDatabase db;

    @Inject
    public TeesVelocityWhitelist(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) throws IOException {
        TeesVelocityWhitelist.server = server;
        TeesVelocityWhitelist.logger = logger;
        this.dataDirectory = dataDirectory;
        Files.createDirectories(dataDirectory);
        new ConfigurationManager(dataDirectory);

        switch(provider){

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
    }

    @Subscribe
    public void onPlayerChat(PlayerChatEvent event) {
        Player p = event.getPlayer();
        logger.info(p.getUsername());
    }

    @Subscribe
    public void onPlayerJoin(PlayerChooseInitialServerEvent event){
        Player p = event.getPlayer();
        logger.info(p.getUsername() + " has joined");

        final TextComponent textComponent = Component.text()
                .append(Component.text("Disconnected!")
                        .color(TextColor.color(NamedTextColor.GOLD))
                        .decoration(TextDecoration.BOLD, true))
                .appendNewline()
                .append(Component.text("Please execute the following command on Discord:"))
                .appendNewline()
                .appendNewline()
                .append(Component.text("/link 12s1j1")
                        .color(TextColor.color(NamedTextColor.AQUA)))
                .appendNewline()
                .append(Component.text("Server: Aram Warriors 2.0")
                        .color(TextColor.fromHexString("#33404d")))
                .build();

        p.disconnect(textComponent);
    }

}
