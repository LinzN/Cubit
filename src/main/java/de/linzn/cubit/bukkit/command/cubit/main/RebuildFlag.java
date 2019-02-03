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
            sender.sendMessage(plugin.getYamlManager().getLanguage().errorNoPermission);
            return true;
        }

        String worldName = args[1];
        this.plugin.getLogger().info("Start FLAG-Rebuild for world " + worldName);
        World world = Bukkit.getWorld(worldName);

        for (CubitLand cubitLand : this.plugin.getRegionManager().getAllRegions(world, CubitType.WORLD)) {
            this.plugin.getRegionManager().fireFlag.refresh(cubitLand, false);
            this.plugin.getRegionManager().lockFlag.refresh(cubitLand, false);
            this.plugin.getRegionManager().monsterFlag.refresh(cubitLand, false);
            this.plugin.getRegionManager().potionFlag.refresh(cubitLand, false);
            this.plugin.getRegionManager().pvpFlag.refresh(cubitLand, false);
            this.plugin.getRegionManager().tntFlag.refresh(cubitLand, false);
            this.plugin.getRegionManager().cubitPacket.refresh(cubitLand, false);
            this.plugin.getLogger().info("PACKET-REFRESHING LAND: " + cubitLand.getWGRegion().getId());
        }
        CubitBukkitPlugin.inst().getRegionManager().getRegionSaver().save(world);


        return true;

    }

}
