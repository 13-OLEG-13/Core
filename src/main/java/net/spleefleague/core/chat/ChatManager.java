/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.spleefleague.core.chat;

import java.util.Collection;
import java.util.HashSet;
import net.spleefleague.core.SpleefLeague;
import net.spleefleague.core.player.SLPlayer;
import org.bukkit.Bukkit;

/**
 *
 * @author Jonas
 */
public class ChatManager {
    
    public static void sendMessage(final String m, final String c) {
        Bukkit.getScheduler().runTask(SpleefLeague.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (SLPlayer slp : SpleefLeague.getInstance().getPlayerManager().getAll()) {
                    if (slp.isInChatChannel(c)) {
                        slp.getPlayer().sendMessage(m);
                    }
                }
            }
        });
    }

    public static void sendMessage(final String p, final String m, final String c) {
        Bukkit.getScheduler().runTask(SpleefLeague.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (SLPlayer slp : SpleefLeague.getInstance().getPlayerManager().getAll()) {
                    if (slp.isInChatChannel(c)) {
                        slp.getPlayer().sendMessage(p + " " + m);
                    }
                }
            }
        });
    }
    
    private static HashSet<ChatChannel> channels = new HashSet<>();
    
    public static void registerChannel(ChatChannel channel) {
        channels.add(channel);
        if(channel.isDefault()) {
            for(SLPlayer slp : SpleefLeague.getInstance().getPlayerManager().getAll()) {
                if(!slp.isInChatChannel(channel.getName())) {
                    slp.addChatChannel(channel.getName());
                }
            }
        }
    }
    
    public static void unregisterChannel(ChatChannel channel) {
        channels.remove(channel);
        for(SLPlayer slp : SpleefLeague.getInstance().getPlayerManager().getAll()) {
            if(!slp.isInChatChannel(channel.getName())) {
                slp.removeChatChannel(channel.getName());
            }
        }
    }
    
    public static Collection<ChatChannel> getAvailableChatChannels(SLPlayer slp) {
        HashSet<ChatChannel> channels = new HashSet<>();
        for(ChatChannel channel : channels) {
            if(!channel.isTemporary() && slp.getRank().hasPermission(channel.getMinRank())) {
                channels.add(channel);
            }
        }
        return channels;
    }
}