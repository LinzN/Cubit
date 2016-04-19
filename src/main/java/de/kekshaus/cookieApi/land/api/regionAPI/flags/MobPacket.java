package de.kekshaus.cookieApi.land.api.regionAPI.flags;

import java.util.HashSet;

import org.bukkit.entity.EntityType;

import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;

import de.kekshaus.cookieApi.land.api.regionAPI.region.RegionData;

public class MobPacket {

	public MobPacket() {

	}

	@SuppressWarnings("serial")
	private RegionData activateData(RegionData regionData) {
		regionData.praseWGRegion().setFlag(DefaultFlag.MOB_DAMAGE, StateFlag.State.DENY);
		regionData.praseWGRegion().getFlags().put(DefaultFlag.DENY_SPAWN, new HashSet<EntityType>() {
			{
				add(EntityType.CREEPER);
				add(EntityType.ZOMBIE);
				add(EntityType.SKELETON);
				add(EntityType.SILVERFISH);
				add(EntityType.ENDER_DRAGON);
				add(EntityType.WITHER);
				add(EntityType.WITHER_SKULL);
				add(EntityType.GIANT);
				add(EntityType.PIG_ZOMBIE);
				add(EntityType.CAVE_SPIDER);
				add(EntityType.SPIDER);
				add(EntityType.WITCH);
				add(EntityType.ENDERMITE);
				add(EntityType.GUARDIAN);

			}
		});
		return regionData;

	}

	@SuppressWarnings("serial")
	private RegionData deactivateData(RegionData regionData) {
		regionData.praseWGRegion().setFlag(DefaultFlag.MOB_DAMAGE, StateFlag.State.ALLOW);
		regionData.praseWGRegion().getFlags().put(DefaultFlag.DENY_SPAWN, new HashSet<EntityType>() {
			{

			}
		});
		return regionData;

	}

	public boolean getState(RegionData regionData) {
		if (regionData.praseWGRegion().getFlag(DefaultFlag.MOB_DAMAGE) == StateFlag.State.DENY) {
			return true;
		}
		return false;
	}

	public RegionData switchState(RegionData regionData, boolean value) {
		if (value) {
			return activateData(regionData);
		} else {
			return deactivateData(regionData);
		}
	}

	public RegionData switchState(RegionData regionData) {
		if (getState(regionData)) {
			return switchState(regionData, false);
		} else {
			return switchState(regionData, true);
		}
	}
}