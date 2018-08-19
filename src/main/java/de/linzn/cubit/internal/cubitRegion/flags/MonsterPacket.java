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

package de.linzn.cubit.internal.cubitRegion.flags;


import com.sk89q.worldedit.world.entity.EntityType;
import com.sk89q.worldedit.world.entity.EntityTypes;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.cubitRegion.ICubitPacket;
import de.linzn.cubit.internal.cubitRegion.region.CubitLand;
import org.bukkit.ChatColor;

import java.util.HashSet;

public class MonsterPacket implements ICubitPacket {

    @Override

    public CubitLand enablePacket(CubitLand cubitLand) {
        cubitLand.getWGRegion().setFlag(Flags.MOB_DAMAGE, StateFlag.State.DENY);
        cubitLand.getWGRegion().getFlags().put(Flags.DENY_SPAWN, new HashSet<EntityType>() {
            {
                add(EntityTypes.CREEPER);
                add(EntityTypes.ZOMBIE);
                add(EntityTypes.SKELETON);
                add(EntityTypes.SILVERFISH);
                add(EntityTypes.ENDER_DRAGON);
                add(EntityTypes.WITHER);
                add(EntityTypes.WITHER_SKULL);
                add(EntityTypes.GIANT);
                add(EntityTypes.ZOMBIE_PIGMAN);
                add(EntityTypes.CAVE_SPIDER);
                add(EntityTypes.SPIDER);
                add(EntityTypes.WITCH);
                add(EntityTypes.ENDERMITE);
                add(EntityTypes.GUARDIAN);
                add(EntityTypes.ZOMBIE_VILLAGER);
                add(EntityTypes.HUSK);
                add(EntityTypes.POLAR_BEAR);
                add(EntityTypes.EVOKER);
                add(EntityTypes.MAGMA_CUBE);
                add(EntityTypes.STRAY);
                add(EntityTypes.VEX);
                add(EntityTypes.VINDICATOR);
            }
        });
        return cubitLand;

    }

    @Override
    public CubitLand disablePacket(CubitLand cubitLand) {
        cubitLand.getWGRegion().setFlag(Flags.MOB_DAMAGE, StateFlag.State.ALLOW);
        cubitLand.getWGRegion().getFlags().put(Flags.DENY_SPAWN, new HashSet<EntityType>());
        return cubitLand;
    }

    @Override
    public boolean getState(CubitLand cubitLand) {
        return cubitLand.getWGRegion().getFlag(Flags.MOB_DAMAGE) == StateFlag.State.DENY;
    }

    @Override
    public ChatColor getStateColor(CubitLand cubitLand) {
        if (getState(cubitLand)) {
            return ChatColor.GREEN;
        }
        return ChatColor.RED;
    }

    @Override
    public CubitLand switchState(CubitLand cubitLand, boolean value, boolean save) {
        CubitLand newCubitLand;
        if (value) {
            newCubitLand = enablePacket(cubitLand);
        } else {
            newCubitLand = disablePacket(cubitLand);
        }
        if (save) {
            CubitBukkitPlugin.inst().getRegionManager().getRegionSaver().save(cubitLand.getWorld());
        }
        return newCubitLand;
    }

    @Override
    public CubitLand switchState(CubitLand cubitLand, boolean save) {
        if (getState(cubitLand)) {
            return switchState(cubitLand, false, save);
        } else {
            return switchState(cubitLand, true, save);
        }
    }

    @Override
    public String getPacketName() {
        return "MONSTER";
    }
}
