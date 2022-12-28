package com.lolitee.teesvelocitywhitelist.discord.events;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import static com.lolitee.teesvelocitywhitelist.TeesVelocityWhitelist.logger;
import static com.lolitee.teesvelocitywhitelist.discord.CommandManager.cmdList;

public class SlashCommandInteractionListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        cmdList.get(event.getName()).execute(event);
    }
}
