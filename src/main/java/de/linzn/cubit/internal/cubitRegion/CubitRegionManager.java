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

package de.linzn.cubit.internal.cubitRegion;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.cubitRegion.flags.*;
import de.linzn.cubit.internal.cubitRegion.region.CubitLand;
import de.linzn.cubit.internal.cubitRegion.region.ManageRegionEntities;
import de.linzn.cubit.internal.cubitRegion.region.ManageRegions;
import de.linzn.cubit.internal.cubitRegion.region.SaveRegions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;

import java.util.*;

public class CubitRegionManager {

    public FireFlag fireFlag;
    public LockFlag lockFlag;
    public MonsterFlag monsterFlag;
    public PvPFlag pvpFlag;
    public TNTFlag tntFlag;
    public PotionFlag potionFlag;
    public AnimalFlag animalFlag;
    public CubitFlag cubitPacket;
    private CubitBukkitPlugin plugin;
    private ManageRegions mReg;
    private ManageRegionEntities mRegE;
    private SaveRegions saveMrg;

    public CubitRegionManager(CubitBukkitPlugin plugin) {
        plugin.getLogger().info("[Setup] CubitRegionManager");
        this.plugin = plugin;
        this.mReg = new ManageRegions();
        this.mRegE = new ManageRegionEntities();
        this.saveMrg = new SaveRegions();
        this.fireFlag = new FireFlag();
        this.lockFlag = new LockFlag();
        this.monsterFlag = new MonsterFlag();
        this.pvpFlag = new PvPFlag();
        this.tntFlag = new TNTFlag();
        this.potionFlag = new PotionFlag();
        this.animalFlag = new AnimalFlag();
        this.cubitPacket = new CubitFlag();
    }

    public boolean isValidRegion(final World world, final int valueX, final int valueZ) {
        RegionManager manager = WorldGuardPlugin.inst().getRegionContainer().get(world);
        String serverName = buildLandName(CubitType.SERVER.toString().toLowerCase(), valueX, valueZ);
        String shopName = buildLandName(CubitType.SHOP.toString().toLowerCase(), valueX, valueZ);
        String worldName = buildLandName(world.getName().toLowerCase(), valueX, valueZ);
        return manager.hasRegion(serverName) || manager.hasRegion(shopName) || manager.hasRegion(worldName);
    }

    public boolean createRegion(final Location loc, final UUID playerUUID, final CubitType type) {

        switch (type) {
            case SERVER:
                return serverRegion(loc);
            case SHOP:
                return shopRegion(loc, playerUUID);
            case WORLD:
                return worldRegion(loc, playerUUID);
            default:
                System.err.println("No valid LandType!");
                return false;
        }
    }

    private boolean worldRegion(final Location loc, final UUID playerUUID) {
        try {
            int chunkX = loc.getChunk().getX();
            int chunkZ = loc.getChunk().getZ();
            World world = loc.getWorld();
            String regionName = buildLandName(world.getName(), chunkX, chunkZ);
            CubitLand cubitLand = mReg.newRegion(chunkX, chunkZ, world, playerUUID, regionName);

            cubitLand = this.lockFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().worldRegionPacketLock, false);
            cubitLand = this.monsterFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().worldRegionPacketMonster, false);
            cubitLand = this.animalFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().worldRegionPacketAnimals, false);
            cubitLand = this.pvpFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().worldRegionPacketPVP, false);
            cubitLand = this.tntFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().worldRegionPacketTNT, false);
            cubitLand = this.fireFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().worldRegionPacketFire, false);
            cubitLand = this.potionFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().worldRegionPacketPotion, false);
            cubitLand = this.cubitPacket.switchStatus(cubitLand, true, false);
            return saveMrg.save(cubitLand);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean serverRegion(final Location loc) {
        try {
            int chunkX = loc.getChunk().getX();
            int chunkZ = loc.getChunk().getZ();
            World world = loc.getWorld();
            String regionName = buildLandName(CubitType.SERVER.toString(), chunkX, chunkZ);
            CubitLand cubitLand = mReg.newRegion(chunkX, chunkZ, world, null, regionName);

            cubitLand = this.lockFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().serverRegionPacketLock, false);
            cubitLand = this.monsterFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().serverRegionPacketMonster, false);
            cubitLand = this.animalFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().serverRegionPacketAnimals, false);
            cubitLand = this.pvpFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().serverRegionPacketPVP, false);
            cubitLand = this.tntFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().serverRegionPacketTNT, false);
            cubitLand = this.fireFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().serverRegionPacketFire, false);
            cubitLand = this.potionFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().serverRegionPacketPotion, false);
            cubitLand = this.cubitPacket.switchStatus(cubitLand, true, false);
            return saveMrg.save(cubitLand);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private boolean shopRegion(final Location loc, final UUID playerUUID) {
        try {
            int chunkX = loc.getChunk().getX();
            int chunkZ = loc.getChunk().getZ();
            World world = loc.getWorld();
            String regionName = buildLandName(CubitType.SHOP.toString(), chunkX, chunkZ);
            CubitLand cubitLand = mReg.newRegion(chunkX, chunkZ, world, null, regionName);

            cubitLand = this.lockFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().shopRegionPacketLock, false);
            cubitLand = this.monsterFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().shopRegionPacketMonster, false);
            cubitLand = this.animalFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().shopRegionPacketAnimals, false);
            cubitLand = this.pvpFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().shopRegionPacketPVP, false);
            cubitLand = this.tntFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().shopRegionPacketTNT, false);
            cubitLand = this.fireFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().shopRegionPacketFire, false);
            cubitLand = this.potionFlag.switchStatus(cubitLand,
                    this.plugin.getYamlManager().getFlag().shopRegionPacketPotion, false);
            cubitLand = this.cubitPacket.switchStatus(cubitLand, true, false);
            return saveMrg.save(cubitLand);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean restoreDefaultSettings(final CubitLand cubitLand, final World world, final UUID playerUUID) {
        try {

            List<CubitLand> list = new ArrayList<>();
            list.add(cubitLand);
            this.mRegE.clearMember(list, world);
            this.mRegE.clearOwners(list, world);
            if (playerUUID != null) {
                OfflinePlayer player = Bukkit.getOfflinePlayer(playerUUID);
                this.mRegE.setOwner(list, world, player);
            }

            this.lockFlag.switchStatus(cubitLand, this.plugin.getYamlManager().getFlag().worldRegionPacketLock,
                    false);
            this.monsterFlag.switchStatus(cubitLand, this.plugin.getYamlManager().getFlag().worldRegionPacketMonster,
                    false);
            this.animalFlag.switchStatus(cubitLand, this.plugin.getYamlManager().getFlag().worldRegionPacketAnimals,
                    false);
            this.pvpFlag.switchStatus(cubitLand, this.plugin.getYamlManager().getFlag().worldRegionPacketPVP, false);
            this.tntFlag.switchStatus(cubitLand, this.plugin.getYamlManager().getFlag().worldRegionPacketTNT, false);
            this.fireFlag.switchStatus(cubitLand, this.plugin.getYamlManager().getFlag().worldRegionPacketFire,
                    false);
            this.potionFlag.switchStatus(cubitLand, this.plugin.getYamlManager().getFlag().worldRegionPacketPotion,
                    false);
            this.cubitPacket.switchStatus(cubitLand, true, false);

            return saveMrg.save(world);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeLand(final CubitLand cubitLand, final World world) {
        try {
            mReg.removeRegion(cubitLand, world);
            return saveMrg.save(world);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addMember(final CubitLand cubitLand, final World world, final UUID playerUUID) {
        try {
            OfflinePlayer player = Bukkit.getOfflinePlayer(playerUUID);
            List<CubitLand> list = new ArrayList<>();
            list.add(cubitLand);
            mRegE.addMember(list, world, player);
            return saveMrg.save(world);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addMemberAll(final UUID ownerUUID, final World world, final UUID playerUUID, final CubitType type) {
        try {

            List<CubitLand> list = new ArrayList<>();
            OfflinePlayer owner = Bukkit.getOfflinePlayer(ownerUUID);
            OfflinePlayer member = Bukkit.getOfflinePlayer(playerUUID);
            for (ProtectedRegion region : mRegE.getRegionList(owner, world, type)) {
                CubitLand data = new CubitLand(world);
                data.setWGRegion(region, false);
                list.add(data);
            }
            mRegE.addMember(list, world, member);
            return saveMrg.save(world);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeMemberAll(final UUID ownerUUID, final World world, final UUID playerUUID,
                                   final CubitType type) {
        try {

            List<CubitLand> list = new ArrayList<>();
            OfflinePlayer owner = Bukkit.getOfflinePlayer(ownerUUID);
            OfflinePlayer member = Bukkit.getOfflinePlayer(playerUUID);
            for (ProtectedRegion region : mRegE.getRegionList(owner, world, type)) {
                CubitLand data = new CubitLand(world);
                data.setWGRegion(region, false);
                list.add(data);
            }
            mRegE.removeMember(list, world, member);
            return saveMrg.save(world);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeMember(final CubitLand cubitLand, final World world, final UUID playerUUID) {
        try {
            OfflinePlayer player = Bukkit.getOfflinePlayer(playerUUID);
            List<CubitLand> list = new ArrayList<>();
            list.add(cubitLand);
            mRegE.removeMember(list, world, player);
            return saveMrg.save(world);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getStringState(boolean value) {
        String stateString = plugin.getYamlManager().getLanguage().flagStateInactive;
        if (value) {
            stateString = plugin.getYamlManager().getLanguage().flagStateActive;
        }
        return stateString;
    }

    public List<CubitLand> getAllRegionsFromPlayer(final UUID searchUUID, final World world, final CubitType type) {
        List<CubitLand> list = new ArrayList<>();
        try {

            OfflinePlayer owner = Bukkit.getOfflinePlayer(searchUUID);
            for (ProtectedRegion region : mRegE.getRegionList(owner, world, type)) {
                CubitLand data = new CubitLand(world);
                data.setWGRegion(region, false);
                list.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<CubitLand> getAllRegions(final World world, final CubitType type) {
        RegionManager rm = WorldGuardPlugin.inst().getRegionContainer().get(world);
        List<CubitLand> list = new ArrayList<>();
        try {
            for (Map.Entry<String, ProtectedRegion> region : Objects.requireNonNull(rm).getRegions().entrySet()) {
                CubitLand data = new CubitLand(world);
                data.setWGRegion(region.getValue(), false);
                if (data.getCubitType() == type || type == CubitType.NOTYPE) {
                    list.add(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public CubitLand praseRegionData(final World world, final int valueX, final int valueZ) {
        ProtectedRegion region = praseWGRegion(world, valueX, valueZ);
        CubitLand cubitLand = new CubitLand(world);
        if (region != null) {
            cubitLand.setWGRegion(region, true);
        }
        return cubitLand;
    }

    private ProtectedRegion praseWGRegion(final World world, final int valueX, final int valueZ) {
        RegionManager manager = WorldGuardPlugin.inst().getRegionContainer().get(world);
        String serverName = buildLandName(CubitType.SERVER.toString(), valueX, valueZ);
        String shopName = buildLandName(CubitType.SHOP.toString(), valueX, valueZ);
        String worldName = buildLandName(world.getName(), valueX, valueZ);
        if (manager.hasRegion(serverName)) {
            return manager.getRegion(serverName);

        } else if (manager.hasRegion(shopName)) {
            return manager.getRegion(shopName);

        } else if (manager.hasRegion(worldName)) {
            return manager.getRegion(worldName);

        }
        return null;
    }

    public String buildLandName(final String type, final int valueX, final int valueZ) {
        return type.toLowerCase() + "_" + valueX + "_" + valueZ;
    }

    public SaveRegions getRegionSaver() {
        return this.saveMrg;
    }

    public boolean hasReachLimit(UUID playerUUID, World world, CubitType type, int limit) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerUUID);
        List<ProtectedRegion> regions = this.mRegE.getRegionList(player, world, type);
        return regions.size() >= limit;

    }

    public boolean hasLandPermission(final CubitLand cubitLand, final UUID uuid) {
        return cubitLand.getWGRegion().getOwners().getUniqueIds().contains(uuid);

    }

    public boolean isToLongOffline(final UUID uuid, final boolean isMember) {
        long currentTimeStamp = new Date().getTime();
        double buyupTimeInDays = plugin.getDataAccessManager().databaseType.get_buyup_time(uuid);

        if (buyupTimeInDays == -1D) {
            return false;
        } else if (buyupTimeInDays == 0D) {
            if (isMember) {
                buyupTimeInDays = this.plugin.getYamlManager().getSettings().landDeprecatedMember;
            } else {
                buyupTimeInDays = this.plugin.getYamlManager().getSettings().landDeprecatedOther;
            }
        }

        long lastLogin = plugin.getDataAccessManager().databaseType.get_last_login_profile(uuid);
        long landDeprecated = (long) (buyupTimeInDays * 24 * 60 * 60 * 1000);

        return currentTimeStamp - lastLogin >= landDeprecated;
    }

}
