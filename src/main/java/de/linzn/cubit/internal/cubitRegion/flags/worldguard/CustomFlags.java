package de.linzn.cubit.internal.cubitRegion.flags.worldguard;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import org.bukkit.Bukkit;

public class CustomFlags {
    public static final StateFlag CUBIT_AUTOMATIC_UPDATE = new StateFlag("cubit-automatic-update", true);
    public static final StateFlag CUBIT_GUILD_LAND = new StateFlag("cubit-guild-land", false);

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
