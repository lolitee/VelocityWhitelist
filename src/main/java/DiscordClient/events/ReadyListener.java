package DiscordClient.events;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import static com.lolitee.teesvelocitywhitelist.TeesVelocityWhitelist.logger;

public class ReadyListener implements EventListener
{
    @Override
    public void onEvent(GenericEvent event)
    {
        if (event instanceof ReadyEvent)
            logger.info(String.format("Discord bot is ready! %s#%s", event.getJDA().getSelfUser().getName(), event.getJDA().getSelfUser().getDiscriminator()));
    }
}