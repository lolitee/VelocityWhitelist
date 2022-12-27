package DiscordClient;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class Client {

    private final String token;
    private final JDA jda;

    public Client(String token){
        this.token = token;
        jda = this.build();
    }

    private JDA build(){
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
