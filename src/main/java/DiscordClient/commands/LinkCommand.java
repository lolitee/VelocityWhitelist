package DiscordClient.commands;

import DiscordClient.ICommand;

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
    public void execute() {

    }
}
