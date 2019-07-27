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

package de.linzn.cubit.internal.cubitRegion.flags.worldguard;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import org.bukkit.Bukkit;

public class CustomFlags {
    public static final StateFlag CUBIT_AUTOMATIC_UPDATE = new StateFlag("cubit-automatic-update", true);
    public static final StateFlag CUBIT_GUILD_LAND = new StateFlag("cubit-guild-land", false);
    public static final StateFlag CUBIT_MONSTER_SPAWN = new StateFlag("cubit-monster-spawn", false);
    public static final StateFlag CUBIT_ANIMAL_SPAWN = new StateFlag("cubit-animal-spawn", true);

    public static void registerFlag(Flag flag) {
        try {
            if (!WorldGuardPlugin.inst().isEnabled()) {
                WorldGuard.getInstance().getFlagRegistry().register(flag);
            } else {
                Bukkit.getLogger().warning("Cubit: Flag registry already locket!");
            }
        } catch (IllegalStateException | Error e) {
            e.printStackTrace();
        }
    }
}
