package com.lolitee.teesvelocitywhitelist.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.jetbrains.annotations.NotNull;

public class DiscordClient {
    private final String token;
    private final JDA jda;
    public DiscordClient(String token){
        this.token = token;
        jda = this.build();
        new CommandManager(jda);
        new EventManager(jda);
    }
    private @NotNull JDA build(){
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.setActivity(Activity.playing("Minecraft"));
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);

        return builder.build();
    }

    public void setActivity(Activity activity){
        jda.getPresence().setActivity(activity);
    }

    public void setStatus(OnlineStatus status){
        jda.getPresence().setStatus(status);
    }
}
