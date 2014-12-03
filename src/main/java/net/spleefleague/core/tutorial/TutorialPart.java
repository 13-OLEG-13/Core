/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.spleefleague.core.tutorial;

import java.util.Random;
import net.spleefleague.core.SpleefLeague;
import net.spleefleague.core.chat.Theme;
import net.spleefleague.core.player.SLPlayer;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author Jonas
 */
public abstract class TutorialPart {
    
    private final SLPlayer slp;
    protected static final Random random = new Random();
    protected int currentStep = 0;
    protected final int entityID;
    
    public TutorialPart(SLPlayer slp, int entityID) {
        this.slp = slp;
        this.entityID = entityID;
    }
    
    public SLPlayer getPlayer() {
        return slp;
    }
    
    protected void sendMessages(final String[] messages, final boolean increaseStep) {
        BukkitRunnable br = new BukkitRunnable() {
            private int tick = 0;
            @Override
            public void run() {
                if(messages.length <= tick) {
                    if(increaseStep) {
                        currentStep++;
                    }
                }
                else {
                    getPlayer().getPlayer().sendMessage(Theme.INFO + messages[0]);
                    tick++;
                }
            }
        };
        br.runTaskTimer(SpleefLeague.getInstance(), 20, 60);
    }
    
    public abstract void onComplete();
    public abstract void onCancel();
    public abstract void start();
    public void onPlayerMessage(String message) {
        //Do nothing
    }
}
