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

package de.linzn.cubit.bukkit.command.cubit.main;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import de.linzn.cubit.bukkit.command.ICommand;
import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.cubitRegion.CubitType;
import de.linzn.cubit.internal.cubitRegion.region.CubitLand;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UpgradeHeight implements ICommand {

    private CubitBukkitPlugin plugin;
    private String permNode;

    public UpgradeHeight(CubitBukkitPlugin plugin, String permNode) {
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
        this.plugin.getLogger().info("Start Height rebuild 1.17.1 for world " + worldName);
        World world = Bukkit.getWorld(worldName);
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(new BukkitWorld(world));
        int upgraded = 0;
        int ignored = 0;
        for (CubitType type : CubitType.values()) {
            if (type != CubitType.NOTYPE) {
                for (CubitLand cubitLand : this.plugin.getRegionManager().getAllRegions(world, type)) {
                    ProtectedRegion oldRegion = cubitLand.getWGRegion();

                    BlockVector3 oldMin = oldRegion.getMinimumPoint();
                    BlockVector3 oldMax = oldRegion.getMaximumPoint();

                    if (oldMin.getBlockY() == world.getMinHeight() && oldMax.getBlockY() == world.getMaxHeight()) {
                        this.plugin.getLogger().info("Ignoring region " + cubitLand.getLandName() + ". Already min/max height!");
                        ignored++;
                    } else {
                        this.plugin.getLogger().info("Upgrade region " + cubitLand.getLandName() + " in " + worldName);
                        BlockVector3 newMin = BlockVector3.at(oldMin.getBlockX(), world.getMinHeight(), oldMin.getBlockZ());
                        BlockVector3 newMax = BlockVector3.at(oldMax.getBlockX(), world.getMaxHeight(), oldMax.getBlockZ());

                        ProtectedRegion region = new ProtectedCuboidRegion(oldRegion.getId(), newMin, newMax);
                        region.copyFrom(cubitLand.getWGRegion());
                        cubitLand.setWGRegion(region, true);
                        CubitBukkitPlugin.inst().getRegionManager().getRegionSaver().saveData(cubitLand, world);

                        if (regionManager.hasRegion(region.getId())) {
                            regionManager.removeRegion(region.getId());
                        }
                        regionManager.addRegion(region);

                        upgraded++;
                    }
                }
            }
        }
        this.plugin.getLogger().info("Upgrade done...");
        this.plugin.getLogger().info("Upgraded: " + upgraded + " - Ignored: " + ignored);
        try {
            this.plugin.getLogger().info("Saving data...");
            regionManager.saveChanges();
        } catch (StorageException e) {
            this.plugin.getLogger().info("Error while saving data...");
            e.printStackTrace();
        }
        this.plugin.getLogger().info("Complete!");


        return true;

    }

}
