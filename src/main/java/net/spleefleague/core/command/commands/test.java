/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.spleefleague.core.command.commands;

import java.util.Arrays;
import net.spleefleague.core.CorePlugin;
import net.spleefleague.core.command.BasicCommand;
import net.spleefleague.core.player.SLPlayer;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

/**
 *
 * @author Jonas
 */
public class test extends BasicCommand {

    public test(CorePlugin plugin, String name, String usage) {
        super(plugin, name, usage);
    }

    @Override
    protected void run(Player p, SLPlayer slp, Command cmd, String[] args) {
        p.sendMessage(Arrays.toString(args));
    }
}