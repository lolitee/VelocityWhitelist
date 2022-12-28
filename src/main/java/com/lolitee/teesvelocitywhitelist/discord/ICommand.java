package com.lolitee.teesvelocitywhitelist.discord;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public interface ICommand {
    public String name();
    public String description();
    OptionData options();
    void execute(SlashCommandInteractionEvent event);
    public default Permission[] permission() {
        return Permission.EMPTY_PERMISSIONS;
    }
    // add options
}
