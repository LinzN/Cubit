/*
 * Copyright (C) 2018. MineGaming - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 *  You should have received a copy of the LGPLv3 license with
 *  this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.cubit.bukkit.command.cubit.main;

import de.linzn.cubit.bukkit.command.ICommand;
import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.cubitRegion.CubitType;
import de.linzn.cubit.internal.cubitRegion.region.CubitLand;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RebuildFlag implements ICommand {

    private CubitBukkitPlugin plugin;
    private String permNode;

    public RebuildFlag(CubitBukkitPlugin plugin, String permNode) {
        this.plugin = plugin;
        this.permNode = permNode;
    }

    @Override
    public boolean runCmd(final Command cmd, final CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            /* Build and get all variables */
            Player player = (Player) sender;
            /* Permission Check */
            if (!player.hasPermission(this.permNode)) {
                sender.sendMessage(plugin.getYamlManager().getLanguage().errorNoPermission);
                return true;
            }

            if (args.length >= 1) {
                String worldName = args[0];

                World world = Bukkit.getWorld(worldName);

                for (CubitLand cubitLand : this.plugin.getRegionManager().getAllRegions(world, CubitType.WORLD)) {
                    this.plugin.getRegionManager().firePacket.refreshPacket(cubitLand, false);
                    this.plugin.getRegionManager().lockPacket.refreshPacket(cubitLand, false);
                    this.plugin.getRegionManager().monsterPacket.refreshPacket(cubitLand, false);
                    this.plugin.getRegionManager().potionPacket.refreshPacket(cubitLand, false);
                    this.plugin.getRegionManager().pvpPacket.refreshPacket(cubitLand, false);
                    this.plugin.getRegionManager().tntPacket.refreshPacket(cubitLand, false);
                    this.plugin.getLogger().info("PACKET-REFRESHING LAND: " + cubitLand.getWGRegion().getId());
                }
                CubitBukkitPlugin.inst().getRegionManager().getRegionSaver().save(world);
            } else {
                sender.sendMessage(plugin.getYamlManager().getLanguage().errorCommand);
            }


        }

        return true;

    }

}
