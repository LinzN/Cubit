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

package de.linzn.cubit.internal.blockEdit;

import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.blockEdit.subHandler.BiomeHandler;
import de.linzn.cubit.internal.blockEdit.subHandler.BlockHandler;

public class BlockEditManager {

    private CubitBukkitPlugin plugin;
    private BiomeHandler biomeHandler;
    private BlockHandler blockHandler;

    public BlockEditManager(CubitBukkitPlugin plugin) {
        plugin.getLogger().info("[Setup] BlockEditManager");
        this.plugin = plugin;
        this.biomeHandler = new BiomeHandler(this.plugin);
        this.blockHandler = new BlockHandler(this.plugin);
    }

    public BiomeHandler getBiomeHandler() {
        return this.biomeHandler;
    }

    public BlockHandler getBlockHandler() {
        return this.blockHandler;
    }


}
