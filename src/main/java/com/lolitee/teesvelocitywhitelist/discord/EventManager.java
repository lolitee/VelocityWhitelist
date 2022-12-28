package com.lolitee.teesvelocitywhitelist.discord;

import com.lolitee.teesvelocitywhitelist.discord.events.ReadyListener;
import com.lolitee.teesvelocitywhitelist.discord.events.SlashCommandInteractionListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;

public class EventManager {
    private JDA jda;
    ListenerAdapter[] listeners = new ListenerAdapter[] {
            new ReadyListener(),
            new SlashCommandInteractionListener()
    };
    public EventManager(JDA jda) {
        Arrays.stream(listeners).forEach(jda::addEventListener);
    }
}
