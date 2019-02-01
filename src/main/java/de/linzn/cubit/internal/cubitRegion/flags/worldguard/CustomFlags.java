package de.linzn.cubit.internal.cubitRegion.flags.worldguard;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;

public class CustomFlags {
    public static final StateFlag CUBIT_AUTOMATIC_UPDATE = new StateFlag("cubit-automatic-update", true);
    public static final StateFlag CUBIT_GUILD_LAND = new StateFlag("cubit-guild-land", false);

    public static void registerFlag(Flag flag) {
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        try {
            registry.register(flag);
        } catch (FlagConflictException e) {
            e.printStackTrace();
        }
    }
}
