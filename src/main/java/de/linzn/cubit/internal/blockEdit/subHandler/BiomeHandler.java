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

import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.blockEdit.normal.biome.ChangeBiome;
import org.bukkit.Chunk;
import org.bukkit.block.Biome;

public class BiomeHandler {

    private CubitBukkitPlugin plugin;

    public BiomeHandler(CubitBukkitPlugin plugin) {
        this.plugin = plugin;
    }


    public boolean changeBiomeChunk(Chunk chunk, Biome biome) {
        return this.changeBiomeChunkDefault(chunk, biome);
    }

    private boolean changeBiomeChunkDefault(Chunk chunk, Biome biome) {

        try {
            new ChangeBiome(plugin, chunk, biome).change();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
