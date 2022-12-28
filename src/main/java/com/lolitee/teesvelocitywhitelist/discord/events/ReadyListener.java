package com.lolitee.teesvelocitywhitelist.discord.events;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static com.lolitee.teesvelocitywhitelist.TeesVelocityWhitelist.logger;

public class ReadyListener extends ListenerAdapter
{
    @Override
    public void onReady(ReadyEvent event){
        logger.info(String.format("Discord bot is ready! %s#%s", event.getJDA().getSelfUser().getName(), event.getJDA().getSelfUser().getDiscriminator()));
    }

}