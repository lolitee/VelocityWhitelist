package com.lolitee.teesvelocitywhitelist.events;

import com.lolitee.teesvelocitywhitelist.classes.Code;
import com.lolitee.teesvelocitywhitelist.database.IDatabase;
import com.lolitee.teesvelocitywhitelist.database.commands.CheckPlayerCommand;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChooseInitialServerEvent;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.lolitee.teesvelocitywhitelist.TeesVelocityWhitelist.*;

public class PlayerJoin {
    IDatabase db;
    public static final HashMap<UUID, String> tokens = new HashMap<>();
    Random rnd = new Random();

    @Subscribe
    public void onPlayerJoin(PlayerChooseInitialServerEvent event){
        Player p = event.getPlayer();

        ResultSet rs = db.execute(new CheckPlayerCommand().values(new Object[] {p.getUniqueId()}));
        try {
            if(rs.next()){
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (!tokens.containsKey(p.getUniqueId())){
            tokens.put(p.getUniqueId(), getRandomString(7));
            server.getScheduler()
                    .buildTask(plugin, () -> {
                        tokens.remove(p.getUniqueId());
                    })
                    .delay(15L, TimeUnit.MINUTES)
                    .schedule();
        }
        String code = tokens.get(p.getUniqueId());

        final TextComponent textComponent = Component.text()
                .append(Component.text("Disconnected!")
                        .color(TextColor.color(NamedTextColor.GOLD))
                        .decoration(TextDecoration.BOLD, true))
                .appendNewline()
                .append(Component.text("Please execute the following command on Discord:"))
                .appendNewline()
                .appendNewline()
                .append(Component.text("/link " + code)
                        .color(TextColor.color(NamedTextColor.AQUA)))
                .appendNewline()
                .append(Component.text("Server: Aram Warriors 2.0")
                        .color(TextColor.fromHexString("#33404d")))
                .build();

        p.disconnect(textComponent);
    }
    private String getRandomString(int count){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        rnd.setSeed(System.currentTimeMillis());
        while (result.length() <= count){
            int index = (int) (rnd.nextFloat() * characters.length());
            result.append(characters.charAt(index));
            logger.info(result.toString());
        }
        return result.toString();
    }

}
