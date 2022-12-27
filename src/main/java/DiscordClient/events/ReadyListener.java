package DiscordClient.events;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class ReadyListener implements EventListener
{
    @Override
    public void onEvent(GenericEvent event)
    {
        if (event instanceof ReadyEvent)
            System.out.println("API is ready!");
    }
}