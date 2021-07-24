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

package de.linzn.cubit.api.events;

import de.linzn.cubit.internal.cubitRegion.region.CubitLand;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class CubitLandBuyEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private World world;
    private CubitLand cubitLand;

    public CubitLandBuyEvent(World world, CubitLand cubitLand) {
        this.world = world;
        this.cubitLand = cubitLand;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public CubitLand getCubitLand() {
        return this.cubitLand;
    }

    public World getWorld() {
        return this.world;
    }

    public HandlerList getHandlers() {
        return handlers;
    }
}