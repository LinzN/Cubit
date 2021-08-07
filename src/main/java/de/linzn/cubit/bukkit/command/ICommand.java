/*
 * Copyright (C) 2021. Kekshaus - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.cubit.bukkit.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public interface ICommand {
    boolean runCmd(Command cmd, CommandSender sender, String[] args);

    default void synchronizeTask(Plugin plugin, Runnable runnable){
        Bukkit.getScheduler().runTask(plugin, runnable);
    }
}
