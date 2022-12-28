package com.lolitee.teesvelocitywhitelist.events;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.proxy.Player;

import java.util.Random;

import static com.lolitee.teesvelocitywhitelist.TeesVelocityWhitelist.logger;

public class PlayerChat {
    @Subscribe
    public void onPlayerChat(PlayerChatEvent event) {
//        Player p = event.getPlayer();
//        logger.info(p.getUsername());
    }



}
