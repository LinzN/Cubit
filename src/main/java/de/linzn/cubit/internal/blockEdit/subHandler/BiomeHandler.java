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

package de.linzn.cubit.internal.blockEdit.subHandler;

import de.linzn.cubit.internal.blockEdit.normal.worldedit.WorldEditBiomeChange;
import de.linzn.cubit.internal.blockEdit.normal.worldedit.WorldEditBlockFunction;
import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import org.bukkit.Chunk;
import org.bukkit.block.Biome;

public class BiomeHandler {

    private CubitBukkitPlugin plugin;

    public BiomeHandler(CubitBukkitPlugin plugin) {
        this.plugin = plugin;
    }


    public boolean changeBiomeChunk(Chunk chunk, Biome biome) {
        WorldEditBlockFunction worldEditBlockFunction = new WorldEditBiomeChange(chunk, biome);
        return worldEditBlockFunction.startOperation();
    }
}
