package com.lolitee.teesvelocitywhitelist.discord.commands;

import com.lolitee.teesvelocitywhitelist.classes.Code;
import com.lolitee.teesvelocitywhitelist.discord.ICommand;
import com.lolitee.teesvelocitywhitelist.events.PlayerJoin;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Optional;
import java.util.UUID;

import static com.lolitee.teesvelocitywhitelist.TeesVelocityWhitelist.logger;
import static com.lolitee.teesvelocitywhitelist.events.PlayerJoin.tokens;

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

        String code = event.getOption("code", OptionMapping::getAsString);

        Optional<String> playerCode = tokens.values().stream()
                .filter(c -> c.equals(code))
                .findFirst();

        if(!playerCode.isPresent()){
            event.reply(String.format("Code `%s` is invalid.", code)).setEphemeral(true).queue();
            return;
        }

        Optional<UUID> uuid = tokens.keySet().stream()
                .filter(c -> c.equals(playerCode))
                .findFirst();

        logger.info(uuid.toString());

        event.reply(code).setEphemeral(true).queue();
    }
}
