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

package de.linzn.cubit.api;

import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.cubitRegion.region.CubitLand;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

public class CubitAPI {

    /**
     * Method getCubitLand
     * Get cubitland for specific coordinates
     *
     * @param world World for this cubitland
     * @param chunkX X-coordinates for chunk
     * @param chunkZ Z-coordinates for chunk
     * @return CubitLand. Can return null if no one found
     */
    public static CubitLand getCubitLand(World world, int chunkX, int chunkZ) {
        return CubitBukkitPlugin.inst().getRegionManager().praseRegionData(world, chunkX, chunkZ);
    }

    /**
     * Method getCubitLand
     * Get cubitland for specific location
     *
     * @param location Location for this cubitland
     * @return CubitLand. Can return null if no one found
     */
    public static CubitLand getCubitLand(Location location) {
        return getCubitLand(location.getWorld(), location.getChunk().getX(), location.getChunk().getZ());
    }

    /**
     * Method getCubitLand
     * Get cubitland for specific chunk
     *
     * @param chunk Chunk for this cubitland
     * @return CubitLand. Can return null if no one found
     */
    public static CubitLand getCubitLand(Chunk chunk) {
        return getCubitLand(chunk.getWorld(), chunk.getX(), chunk.getZ());
    }
}
