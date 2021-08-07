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

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.function.RegionFunction;
import com.sk89q.worldedit.function.RegionMaskingFilter;
import com.sk89q.worldedit.function.mask.Mask;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.function.visitor.RegionVisitor;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import org.bukkit.Chunk;

public abstract class WorldEditBlockFunction {
    protected final EditSession editSession;
    protected final BlockVector3 min;
    protected final BlockVector3 max;
    protected final CuboidRegion region;
    protected RegionFunction regionFunction;
    protected RegionVisitor regionVisitor;

    public WorldEditBlockFunction(Chunk chunk) {
        this.editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(chunk.getWorld()));
        this.editSession.enableStandardMode();
        this.min = BlockVector3.at(chunk.getX() * 16, chunk.getWorld().getMinHeight(), chunk.getZ() * 16);
        this.max = BlockVector3.at(min.getBlockX() + 15, chunk.getWorld().getMaxHeight(), min.getBlockZ() + 15);
        this.region = new CuboidRegion(BukkitAdapter.adapt(chunk.getWorld()), min, max);
    }

    protected void setRegionFunction(RegionFunction regionFunction) {
        this.regionFunction = regionFunction;
        this.prepare();
    }

    private void prepare() {
        Mask mask = editSession.getMask();
        if (mask != null) {
            this.regionFunction = new RegionMaskingFilter(mask, this.regionFunction);
        }
        this.regionVisitor = new RegionVisitor(this.region, this.regionFunction);
    }

    public boolean startOperation() {
        try {
            Operations.completeLegacy(this.regionVisitor);
        } catch (MaxChangedBlocksException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
