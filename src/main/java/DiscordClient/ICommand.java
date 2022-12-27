package DiscordClient;

import net.dv8tion.jda.api.Permission;

public interface ICommand {
    public String name();
    public String description();
    void execute();
    public default Permission[] permission() {
        return Permission.EMPTY_PERMISSIONS;
    }
    // add options
}
