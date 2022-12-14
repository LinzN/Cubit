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

package de.linzn.cubit.bukkit.command.universal;

import de.linzn.cubit.bukkit.command.ICommand;
import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.cubitRegion.CubitType;
import de.linzn.cubit.internal.cubitRegion.region.CubitLand;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class WhoUniversal implements ICommand {

    private CubitBukkitPlugin plugin;
    private String permNode;
    private CubitType type;

    public WhoUniversal(CubitBukkitPlugin plugin, String permNode, CubitType type) {
        this.plugin = plugin;
        this.permNode = permNode;
        this.type = type;
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

        Location loc = player.getLocation();
        Chunk chunk = loc.getChunk();
        CubitLand cubitLand = plugin.getRegionManager().praseRegionData(loc.getWorld(), chunk.getX(),
                chunk.getZ());

        /*
         * Check if the player has permissions for this land or hat landadmin
         * permissions
         */
        if (!plugin.getRegionManager().isValidRegion(loc.getWorld(), chunk.getX(), chunk.getZ())) {
            sender.sendMessage(plugin.getYamlManager().getLanguage().errorNoLandFound);
            return true;
        }

        if (cubitLand.getCubitType() != type && type != CubitType.NOTYPE) {
            sender.sendMessage(
                    plugin.getYamlManager().getLanguage().errorNoValidLandFound.replace("{type}", type.toString()));
            return true;
        }

        if (!plugin.getRegionManager().hasLandPermission(cubitLand, player.getUniqueId())) {
            sender.sendMessage(plugin.getYamlManager().getLanguage().errorNoLandPermission.replace("{regionID}",
                    cubitLand.getLandName()));
            return true;
        }


        if (plugin.getRegionManager().pvpFlag.getStatus(cubitLand)) {
            sender.sendMessage(plugin.getYamlManager().getLanguage().errorPvpIsEnabled);
            return true;
        }


        /* Run in sync */
        synchronizeTask(this.plugin, () -> {
            HashSet<String> playersOnChunk = new HashSet<>();
            for (Entity entity : chunk.getEntities()) {
                if (entity instanceof Player) {
                    playersOnChunk.add(entity.getName());
                }
            }
            sender.sendMessage(
                    plugin.getYamlManager().getLanguage().whoInfo.replace("{regionID}", cubitLand.getLandName()).replace("{players}", playersOnChunk.toString()));
        });
        return true;
    }

}
