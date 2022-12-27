package DiscordClient;

import DiscordClient.commands.LinkCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class CommandManager extends ListenerAdapter {
    private final JDA jda;

    ICommand[] commands = new ICommand[] {
            new LinkCommand()
    };

    Map<String, ICommand> cmdList = new HashMap<>();

    public CommandManager(JDA jda){
        this.jda = jda;
        Arrays.stream(commands).forEach(this::addCommand);
    }

    private void addCommand(ICommand cmd) {
        cmdList.put(cmd.name(), cmd);
        jda.updateCommands().addCommands(
                Commands.slash(cmd.name(), cmd.description())
        );
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        cmdList.get(event.getName()).execute();
    }
}
