package com.lolitee.teesvelocitywhitelist.events;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChooseInitialServerEvent;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.Random;

import static com.lolitee.teesvelocitywhitelist.TeesVelocityWhitelist.logger;

public class PlayerJoin {
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
                .append(Component.text("/link " + getRandomString(8))
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
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());
        while (characters.length() <= count){
            int index = (int) (rnd.nextFloat() * characters.length());
            result.append(characters.charAt(index));
        }
        return result.toString();
    }

}
