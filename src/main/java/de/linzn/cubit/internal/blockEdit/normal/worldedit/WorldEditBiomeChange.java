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

package de.linzn.cubit.internal.blockEdit.normal.worldedit;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.function.biome.BiomeReplace;
import org.bukkit.Chunk;
import org.bukkit.block.Biome;

public class WorldEditBiomeChange extends WorldEditBlockFunction {

    public WorldEditBiomeChange(Chunk chunk, Biome biome) {
        super(chunk);
        super.setRegionFunction(new BiomeReplace(this.editSession, BukkitAdapter.adapt(biome)));
    }
}
