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

package de.linzn.cubit.internal.cubitRegion.flags;


import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.cubitRegion.IFlags;
import de.linzn.cubit.internal.cubitRegion.region.CubitLand;
import org.bukkit.ChatColor;

public class PvPFlag implements IFlags {

    @Override
    public CubitLand enable(CubitLand cubitLand) {
        cubitLand.getWGRegion().setFlag(Flags.PVP, StateFlag.State.ALLOW);
        return cubitLand;

    }

    @Override
    public CubitLand disable(CubitLand cubitLand) {
        cubitLand.getWGRegion().setFlag(Flags.PVP, StateFlag.State.DENY);
        return cubitLand;

    }

    @Override
    public boolean getStatus(CubitLand cubitLand) {
        return cubitLand.getWGRegion().getFlag(Flags.PVP) == StateFlag.State.ALLOW;
    }

    @Override
    public ChatColor getStatusColor(CubitLand cubitLand) {
        if (!getStatus(cubitLand)) {
            return ChatColor.GREEN;
        }
        return ChatColor.RED;
    }

    @Override
    public CubitLand switchStatus(CubitLand cubitLand, boolean value, boolean save) {
        CubitLand newCubitLand;
        if (value) {
            newCubitLand = enable(cubitLand);
        } else {
            newCubitLand = disable(cubitLand);
        }
        if (save) {
            CubitBukkitPlugin.inst().getRegionManager().getRegionSaver().save(cubitLand.getWorld());
        }
        return newCubitLand;
    }

    @Override
    public CubitLand switchStatus(CubitLand cubitLand, boolean save) {
        if (getStatus(cubitLand)) {
            return switchStatus(cubitLand, false, save);
        } else {
            return switchStatus(cubitLand, true, save);
        }
    }

    @Override
    public void refresh(CubitLand cubitLand, boolean save) {
        if (getStatus(cubitLand)) {
            enable(cubitLand);
        } else {
            disable(cubitLand);
        }
    }

    @Override
    public String getStatusString(CubitLand cubitLand) {
        String statusString = CubitBukkitPlugin.inst().getYamlManager().getLanguage().pvpOff;
        if (getStatus(cubitLand)) {
            statusString = CubitBukkitPlugin.inst().getYamlManager().getLanguage().pvpOn;
        }
        return statusString;
    }

    @Override
    public String getProtectionName() {
        return "PVP";
    }
}
