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

package de.linzn.cubit.bukkit.command.admin.main;

import de.linzn.cubit.api.events.CubitLandSellEvent;
import de.linzn.cubit.bukkit.command.ICommand;
import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.cubitRegion.CubitType;
import de.linzn.cubit.internal.cubitRegion.region.CubitLand;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteShopAdmin implements ICommand {

    private CubitBukkitPlugin plugin;

    private String permNode;

    public DeleteShopAdmin(CubitBukkitPlugin plugin, String permNode) {
        this.plugin = plugin;
        this.permNode = permNode;

    }

    @Override
    public boolean runCmd(final Command cmd, final CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            /* This is not possible from the server console */
            sender.sendMessage(plugin.getYamlManager().getLanguage().noConsoleMode);
            return true;
        }

        /* Build and get all variables */
        Player player = (Player) sender;

        /* Permission Check */
        if (!player.hasPermission(this.permNode)) {
            sender.sendMessage(plugin.getYamlManager().getLanguage().errorNoPermission);
            return true;
        }

        final Location loc = player.getLocation();
        final Chunk chunk = loc.getChunk();
        final String regionName = CubitBukkitPlugin.inst().getRegionManager().buildLandName(CubitType.SHOP.toString(),
                chunk.getX(), chunk.getZ());

        /*
         * Check if the player has permissions for this land or hat landadmin
         * permissions
         */
        CubitLand cubitLand = plugin.getRegionManager().praseRegionData(loc.getWorld(), chunk.getX(), chunk.getZ());

        if (cubitLand.getCubitType() != CubitType.SHOP) {
            sender.sendMessage(plugin.getYamlManager().getLanguage().errorNoValidLandFound.replace("{type}",
                    CubitType.SHOP.toString()));
            return true;
        }

        /* Remove offer from Database */
        if (!plugin.getDataAccessManager().databaseType.set_remove_offer(cubitLand.getLandName(), loc.getWorld())) {
            /* If this task failed! This should never happen */
            sender.sendMessage(
                    plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "SHOP-REMOVEOFFER"));
            plugin.getLogger()
                    .warning(plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "SHOP-REMOVEOFFER"));
            return true;
        }

        if (!plugin.getRegionManager().removeLand(cubitLand, loc.getWorld())) {
            /* If this task failed! This should never happen */
            sender.sendMessage(plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "DELETE-REGION"));
            plugin.getLogger()
                    .warning(plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "DELETE-REGION"));
            return true;
        }

        /* Call cubit sell land event */
        CubitLandSellEvent cubitLandSellEvent = new CubitLandSellEvent(loc.getWorld(), regionName);
        Bukkit.getScheduler().runTask(plugin, () -> plugin.getServer().getPluginManager().callEvent(cubitLandSellEvent));

        sender.sendMessage(plugin.getYamlManager().getLanguage().deleteShopLand.replace("{regionID}", regionName));

        return true;
    }

}
