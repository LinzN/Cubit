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


import com.sk89q.worldedit.world.entity.EntityType;
import com.sk89q.worldedit.world.entity.EntityTypes;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.cubitRegion.IFlags;
import de.linzn.cubit.internal.cubitRegion.flags.worldguard.CustomFlags;
import de.linzn.cubit.internal.cubitRegion.region.CubitLand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Animals;
import org.bukkit.entity.WaterMob;

import java.util.HashSet;
import java.util.Locale;

public class AnimalFlag implements IFlags {

    HashSet<EntityType> animalList = new HashSet<>();

    public AnimalFlag() {
        for (org.bukkit.entity.EntityType entityType : org.bukkit.entity.EntityType.values()) {
            if (entityType.isAlive()) {
                if (Animals.class.isAssignableFrom(entityType.getEntityClass()) || WaterMob.class.isAssignableFrom(entityType.getEntityClass())) {
                    EntityType entityType1 = EntityTypes.get(entityType.getName().toLowerCase(Locale.ROOT));
                    if (entityType1 != null) {
                        animalList.add(EntityTypes.get(entityType.getName().toLowerCase(Locale.ROOT)));
                    } else {
                        CubitBukkitPlugin.inst().getLogger().warning("EntityType not found: " + entityType.name());
                    }
                }

            }
        }
    }

    @Override

    public CubitLand enable(CubitLand cubitLand) {
        cubitLand.getWGRegion().setFlag(CustomFlags.CUBIT_ANIMAL_SPAWN, StateFlag.State.ALLOW);

        HashSet<EntityType> list = new HashSet<>();
        if (cubitLand.getWGRegion().getFlag(CustomFlags.CUBIT_MONSTER_SPAWN) == StateFlag.State.DENY) {
            list.addAll(CubitBukkitPlugin.inst().getRegionManager().monsterFlag.monsterList);
        }
        cubitLand.getWGRegion().getFlags().put(Flags.DENY_SPAWN, list);
        return cubitLand;

    }

    @Override
    public CubitLand disable(CubitLand cubitLand) {
        cubitLand.getWGRegion().setFlag(CustomFlags.CUBIT_ANIMAL_SPAWN, StateFlag.State.DENY);
        HashSet<EntityType> list = new HashSet<>(this.animalList);

        if (cubitLand.getWGRegion().getFlag(CustomFlags.CUBIT_MONSTER_SPAWN) == StateFlag.State.DENY) {
            list.addAll(CubitBukkitPlugin.inst().getRegionManager().monsterFlag.monsterList);
        }
        cubitLand.getWGRegion().getFlags().put(Flags.DENY_SPAWN, list);
        return cubitLand;
    }

    @Override
    public boolean getStatus(CubitLand cubitLand) {
        return cubitLand.getWGRegion().getFlag(CustomFlags.CUBIT_ANIMAL_SPAWN) == StateFlag.State.ALLOW;
    }

    @Override
    public ChatColor getStatusColor(CubitLand cubitLand) {
        if (getStatus(cubitLand)) {
            return ChatColor.GREEN;
        }
        return ChatColor.RED;
    }

    @Override
    public String getStatusString(CubitLand cubitLand) {
        String statusString = CubitBukkitPlugin.inst().getYamlManager().getLanguage().animalsOff;
        if (getStatus(cubitLand)) {
            statusString = CubitBukkitPlugin.inst().getYamlManager().getLanguage().animalsOn;
        }
        return statusString;
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
    public String getProtectionName() {
        return "ANIMALS";
    }
}
