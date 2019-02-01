package de.linzn.cubit.internal.cubitRegion.flags;

import com.sk89q.worldguard.protection.flags.StateFlag;
import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.cubitRegion.ICubitPacket;
import de.linzn.cubit.internal.cubitRegion.flags.worldguard.CustomFlags;
import de.linzn.cubit.internal.cubitRegion.region.CubitLand;
import org.bukkit.ChatColor;

public class CubitPacket implements ICubitPacket {
    @Override
    public CubitLand enablePacket(CubitLand cubitLand) {
        cubitLand.getWGRegion().setFlag(CustomFlags.CUBIT_AUTOMATIC_UPDATE, StateFlag.State.ALLOW);
        cubitLand.getWGRegion().setFlag(CustomFlags.CUBIT_GUILD_LAND, StateFlag.State.DENY);
        return cubitLand;

    }

    @Override
    public CubitLand disablePacket(CubitLand cubitLand) {
        cubitLand.getWGRegion().setFlag(CustomFlags.CUBIT_AUTOMATIC_UPDATE, StateFlag.State.DENY);
        cubitLand.getWGRegion().setFlag(CustomFlags.CUBIT_GUILD_LAND, StateFlag.State.DENY);
        return cubitLand;

    }

    @Override
    public boolean getState(CubitLand cubitLand) {
        if(cubitLand.getWGRegion().getFlag(CustomFlags.CUBIT_AUTOMATIC_UPDATE) == null){
           return true;
        }
        return cubitLand.getWGRegion().getFlag(CustomFlags.CUBIT_AUTOMATIC_UPDATE) == StateFlag.State.ALLOW;
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
    public void refreshPacket(CubitLand cubitLand, boolean save) {
        if (getState(cubitLand)) {
            enablePacket(cubitLand);
        } else {
            disablePacket(cubitLand);
        }
    }

    @Override
    public String getPacketName() {
        return "CUBIT";
    }
}
