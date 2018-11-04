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

package de.linzn.cubit.internal.cubitRegion.region;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;

import java.util.UUID;

public class ManageRegions {

    public CubitLand newRegion(final int chunkX, final int chunkZ, final World world, final UUID playerUUID,
                               final String regionName) {

        BlockVector3 min;
        BlockVector3 max;
        min = BlockVector3.at(chunkX * 16, 0D, chunkZ * 16);
        max = min.add(15, world.getMaxHeight(), 15);
        ProtectedRegion region = new ProtectedCuboidRegion(regionName, min, max);

        if (playerUUID != null) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(playerUUID);
            LocalPlayer localplayer = CubitBukkitPlugin.inst().getWorldGuardPlugin().wrapOfflinePlayer(player);
            DefaultDomain domain = new DefaultDomain();
            region.getMembers().clear();
            region.getOwners().clear();
            domain.addPlayer(localplayer);
            region.setOwners(domain);

        }
        CubitLand cubitLand = new CubitLand(world);
        cubitLand.setWGRegion(region, true);
        return cubitLand;
    }

    public CubitLand removeRegion(CubitLand cubitLand, World world) {
        RegionManager manager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(new BukkitWorld(world));
        manager.removeRegion(cubitLand.getLandName());
        return cubitLand;

    }

}
