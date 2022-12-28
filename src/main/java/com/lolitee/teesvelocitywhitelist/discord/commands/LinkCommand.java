package com.lolitee.teesvelocitywhitelist.discord.commands;

import com.lolitee.teesvelocitywhitelist.database.IDatabase;
import com.lolitee.teesvelocitywhitelist.database.commands.CreateUserCommand;
import com.lolitee.teesvelocitywhitelist.database.providers.MariaDB;
import com.lolitee.teesvelocitywhitelist.discord.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Optional;
import java.util.UUID;

import static com.lolitee.teesvelocitywhitelist.TeesVelocityWhitelist.server;
import static com.lolitee.teesvelocitywhitelist.configuration.ConfigurationManager.*;
import static com.lolitee.teesvelocitywhitelist.configuration.ConfigurationManager.database;
import static com.lolitee.teesvelocitywhitelist.events.PlayerJoin.tokens;

public class LinkCommand implements ICommand {

    IDatabase db;
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

        if(!uuid.isPresent()){
            event.reply("Couldn't link your account to Discord");
        }

        if(server.getPlayer(uuid.get()).isPresent()){
            String username = server.getPlayer(uuid.get()).get().getUsername();
            event.reply(String.format("Linked %s to your discord account!", username));
            return;
        }
        event.reply("Linked!").setEphemeral(true).queue();

        db = new MariaDB(address, username, password, database);
        db.executeNonQuery(new CreateUserCommand().values(new Object[] {uuid.get(), event.getId()}));


    }
}
