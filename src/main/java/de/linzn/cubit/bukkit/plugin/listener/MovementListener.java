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

package de.linzn.cubit.bukkit.plugin.listener;

import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.cubitRegion.CubitType;
import de.linzn.cubit.internal.cubitRegion.region.CubitLand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementListener implements Listener {

    @EventHandler
    public void onPlayerMovement(final PlayerMoveEvent event) {
        Location from = event.getFrom();
        Location to = event.getTo();
        if (from.getChunk() == to.getChunk()) {
            return;
        }

        CubitLand cubitLandFrom = CubitBukkitPlugin.inst().getRegionManager().praseRegionData(from.getWorld(), from.getChunk().getX(), from.getChunk().getZ(), false);
        CubitLand cubitLandTo = CubitBukkitPlugin.inst().getRegionManager().praseRegionData(to.getWorld(), to.getChunk().getX(), to.getChunk().getZ(), false);

        if (cubitLandTo.getWGRegion() != null) {
            if (cubitLandTo.getCubitType() == CubitType.WORLD) {
                if (cubitLandFrom.getWGRegion() != null) {
                    if (cubitLandFrom.getOwnersUUID()[0].equals(cubitLandTo.getOwnersUUID()[0])) {
                        return;
                    }
                }
                Bukkit.getScheduler().runTaskAsynchronously(CubitBukkitPlugin.inst(), () -> {
                    String landName = cubitLandTo.getLandName();

                    String pvp = CubitBukkitPlugin.inst().getRegionManager().pvpFlag.getStatusColor(cubitLandTo) + CubitBukkitPlugin.inst().getRegionManager().pvpFlag.getProtectionName();
                    String msg;
                    if (cubitLandTo.getOwnersUUID().length >= 1 && cubitLandTo.getOwnersUUID()[0].equals(event.getPlayer().getUniqueId())) {
                        msg = CubitBukkitPlugin.inst().getYamlManager().getLanguage().enterOwnRegionGreetings.replace("{regionID}", landName).replace("{pvp}", pvp);
                    } else {
                        String ownerName = CubitBukkitPlugin.inst().getCacheManager().getPlayername(cubitLandTo.getOwnersUUID()[0]);
                        msg = CubitBukkitPlugin.inst().getYamlManager().getLanguage().enterRegionGreetings.replace("{regionID}", landName).replace("{player}", ownerName).replace("{pvp}", pvp);
                    }
                    ActionBarAPI.sendActionbar(event.getPlayer(), msg);
                });
            }
        }
    }

}
