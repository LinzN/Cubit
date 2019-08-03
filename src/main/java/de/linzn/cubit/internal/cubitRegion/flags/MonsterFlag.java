/*
 *  Copyright (C) 2019. MineGaming - All Rights Reserved
 *  You may use, distribute and modify this code under the
 *  terms of the LGPLv3 license, which unfortunately won't be
 *  written for another century.
 *
 *  You should have received a copy of the LGPLv3 license with
 *  this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.cubit.internal.cubitRegion.flags;


import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.entity.EntityType;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.cubitRegion.CubitType;
import de.linzn.cubit.internal.cubitRegion.IFlags;
import de.linzn.cubit.internal.cubitRegion.flags.worldguard.CustomFlags;
import de.linzn.cubit.internal.cubitRegion.region.CubitLand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Monster;

import java.util.HashSet;

public class MonsterFlag implements IFlags {

    HashSet<EntityType> monsterList = new HashSet<>();

    public MonsterFlag() {
        for (org.bukkit.entity.EntityType entityType : org.bukkit.entity.EntityType.values()) {
            if (entityType.isAlive()) {
                if (Monster.class.isAssignableFrom(entityType.getEntityClass())) {
                    EntityType entityType1 = BukkitAdapter.adapt(entityType);
                    if (entityType1 != null) {
                        monsterList.add(BukkitAdapter.adapt(entityType));
                    } else {
                        CubitBukkitPlugin.inst().getLogger().warning("EntityType not found: " + entityType.name());
                    }
                }
            }
        }
    }

    @Override
    public CubitLand enable(CubitLand cubitLand) {
        cubitLand.getWGRegion().setFlag(CustomFlags.CUBIT_MONSTER_SPAWN, StateFlag.State.ALLOW);
        cubitLand.getWGRegion().setFlag(Flags.MOB_DAMAGE, StateFlag.State.ALLOW);

        HashSet<EntityType> list = new HashSet<>();
        if (cubitLand.getWGRegion().getFlag(CustomFlags.CUBIT_ANIMAL_SPAWN) == StateFlag.State.DENY) {
            list.addAll(CubitBukkitPlugin.inst().getRegionManager().animalFlag.animalList);
        }
        cubitLand.getWGRegion().getFlags().put(Flags.DENY_SPAWN, list);
        return cubitLand;

    }

    @Override
    public CubitLand disable(CubitLand cubitLand) {
        boolean disableMonsterDamage;
        if (cubitLand.getCubitType() == CubitType.WORLD) {
            disableMonsterDamage = CubitBukkitPlugin.inst().getYamlManager().getFlag().worldRegionPacketMonsterDisableDamage;
        } else if (cubitLand.getCubitType() == CubitType.SHOP) {
            disableMonsterDamage = CubitBukkitPlugin.inst().getYamlManager().getFlag().shopRegionPacketMonsterDisableDamage;
        } else if (cubitLand.getCubitType() == CubitType.SERVER) {
            disableMonsterDamage = CubitBukkitPlugin.inst().getYamlManager().getFlag().serverRegionPacketMonsterDisableDamage;
        } else {
            disableMonsterDamage = true;
        }

        cubitLand.getWGRegion().setFlag(CustomFlags.CUBIT_MONSTER_SPAWN, StateFlag.State.DENY);
        cubitLand.getWGRegion().setFlag(Flags.MOB_DAMAGE, disableMonsterDamage ? StateFlag.State.DENY : StateFlag.State.ALLOW);

        HashSet<EntityType> list = new HashSet<>(this.monsterList);
        if (cubitLand.getWGRegion().getFlag(CustomFlags.CUBIT_ANIMAL_SPAWN) == StateFlag.State.DENY) {
            list.addAll(CubitBukkitPlugin.inst().getRegionManager().animalFlag.animalList);
        }
        cubitLand.getWGRegion().getFlags().put(Flags.DENY_SPAWN, list);
        return cubitLand;
    }

    @Override
    public boolean getStatus(CubitLand cubitLand) {
        return cubitLand.getWGRegion().getFlag(CustomFlags.CUBIT_MONSTER_SPAWN) == StateFlag.State.ALLOW;
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
        String statusString = CubitBukkitPlugin.inst().getYamlManager().getLanguage().monsterOff;
        if (getStatus(cubitLand)) {
            statusString = CubitBukkitPlugin.inst().getYamlManager().getLanguage().monsterOn;
        }
        return statusString;
    }

    @Override
    public String getProtectionName() {
        return "MONSTER";
    }
}
