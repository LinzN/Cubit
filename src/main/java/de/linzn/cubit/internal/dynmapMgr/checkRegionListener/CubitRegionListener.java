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

package de.linzn.cubit.internal.dynmapMgr.checkRegionListener;

import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.cubitEvents.CubitLandBuyEvent;
import de.linzn.cubit.internal.cubitEvents.CubitLandSellEvent;
import de.linzn.cubit.internal.cubitEvents.CubitLandUpdateEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

public class CubitRegionListener implements Listener {
    private CubitBukkitPlugin plugin;

    public CubitRegionListener(CubitBukkitPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCubitLandBuyEvent(CubitLandBuyEvent cubitLandBuyEvent) {
        Map<String, Boolean> regionMap = new HashMap<>();
        regionMap.put(cubitLandBuyEvent.getRegionID(), true);
        this.plugin.getDynmapManager().cubitDynmap.regionPending.put(cubitLandBuyEvent.getWorld(), regionMap);
    }

    @EventHandler
    public void onCubitLandSellEvent(CubitLandSellEvent cubitLandSellEvent) {
        Map<String, Boolean> regionMap = new HashMap<>();
        regionMap.put(cubitLandSellEvent.getRegionID(), false);
        this.plugin.getDynmapManager().cubitDynmap.regionPending.put(cubitLandSellEvent.getWorld(), regionMap);
    }

    @EventHandler
    public void onCubitLandUpdateEvent(CubitLandUpdateEvent cubitLandUpdateEvent) {
        Map<String, Boolean> regionMap = new HashMap<>();
        regionMap.put(cubitLandUpdateEvent.getRegionID(), true);
        this.plugin.getDynmapManager().cubitDynmap.regionPending.put(cubitLandUpdateEvent.getWorld(), regionMap);
    }
}
