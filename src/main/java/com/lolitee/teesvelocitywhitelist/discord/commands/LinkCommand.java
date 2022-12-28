package com.lolitee.teesvelocitywhitelist.discord.commands;

import com.lolitee.teesvelocitywhitelist.discord.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import static com.lolitee.teesvelocitywhitelist.TeesVelocityWhitelist.logger;

public class LinkCommand implements ICommand {

    @Override
    public String name() {
        return "link";
    }

    @Override
    public String description() {
        return "Link your Discord client to Minecraft";
    }

    @Override
    public OptionData options() {
        return new OptionData(OptionType.STRING, "code", "Your generated token from Minecraft", true, true);
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("reply").setEphemeral(true).queue();
    }
}
