package com.lolitee.teesvelocitywhitelist.discord;

import com.lolitee.teesvelocitywhitelist.discord.commands.LinkCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandManager extends ListenerAdapter {
    private final JDA jda;

    ICommand[] commands = new ICommand[]{
            new LinkCommand()
    };

    public static Map<String, ICommand> cmdList = new HashMap<>();

    public CommandManager(JDA jda) {
        this.jda = jda;
        Arrays.stream(commands).forEach(this::addCommand);
    }

    private void addCommand(ICommand cmd) {
        cmdList.put(cmd.name(), cmd);
        jda.updateCommands().addCommands(
                Commands.slash(cmd.name(), cmd.description())
                        .addOptions(cmd.options())
        ).queue();
    }
}
