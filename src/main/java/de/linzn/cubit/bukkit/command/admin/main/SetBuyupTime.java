/*
 *  Copyright (C) 2019. MineGaming - All Rights Reserved
 *  You may use, distribute and modify this code under the
 *  terms of the LGPLv3 license, which unfortunately won't be
 *  written for another century.
 *
 *  You should have received a copy of the LGPLv3 license with
 *  this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.cubit.bukkit.command.admin.main;

import de.linzn.cubit.bukkit.command.ICommand;
import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetBuyupTime implements ICommand {

    private CubitBukkitPlugin plugin;
    private String permNode;

    public SetBuyupTime(CubitBukkitPlugin plugin, String permNode) {
        this.plugin = plugin;
        this.permNode = permNode;

    }

    @Override
    public boolean runCmd(final Command cmd, final CommandSender sender, String[] args) {

        /* Build and get all variables */
        Player player = (Player) sender;

        /* Permission Check */
        if (!player.hasPermission(this.permNode)) {
            sender.sendMessage(plugin.getYamlManager().getLanguage().errorNoPermission);
            return true;
        }

        if (args.length < 3) {
            sender.sendMessage(plugin.getYamlManager().getLanguage().wrongArguments.replace("{usage}",
                    "/" + cmd.getLabel() + " " + args[0].toLowerCase() + " [Player] [DAYS]"));
            return true;
        }


        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
        int days;
        try {
            days = Integer.valueOf(args[2]);
        } catch (Exception e) {
            player.sendMessage(plugin.getYamlManager().getLanguage().noNumberFound);
            return true;
        }

        if (this.plugin.getDataAccessManager().databaseType.set_buyup_time(offlinePlayer.getUniqueId(), days)) {
            sender.sendMessage(plugin.getYamlManager().getLanguage().setbuyuptime.replace("{player}", offlinePlayer.getName()).replace("{time}", "" + days));
        }
        return true;
    }

}
