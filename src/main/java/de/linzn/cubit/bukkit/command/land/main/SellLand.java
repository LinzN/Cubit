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

package de.linzn.cubit.bukkit.command.land.main;

import de.linzn.cubit.api.events.CubitLandSellEvent;
import de.linzn.cubit.bukkit.command.ICommand;
import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.cubitRegion.CubitType;
import de.linzn.cubit.internal.cubitRegion.region.CubitLand;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SellLand implements ICommand {

    private CubitBukkitPlugin plugin;
    private String permNode;
    private boolean isAdmin;

    public SellLand(CubitBukkitPlugin plugin, String permNode, boolean isAdmin) {
        this.plugin = plugin;
        this.permNode = permNode;

        this.isAdmin = isAdmin;

    }

    @Override
    public boolean runCmd(final Command cmd, final CommandSender sender, String[] args) {
        if (!CubitBukkitPlugin.inst().getYamlManager().getCommandsConfig().land_sell) {
            /* Command is disabled */
            sender.sendMessage(plugin.getYamlManager().getLanguage().disabledCommand);
            return true;
        }

        if (!(sender instanceof Player)) {
            /* This is not possible from the server console */
            sender.sendMessage(plugin.getYamlManager().getLanguage().noConsoleMode);
            return true;
        }

        /* Build and get all variables */
        Player player = (Player) sender;

        /* Permission Check */
        if (!player.hasPermission(this.permNode)) {
            sender.sendMessage(plugin.getYamlManager().getLanguage().errorNoPermission);
            return true;
        }

        final Location loc = player.getLocation();
        final Chunk chunk = loc.getChunk();

        /* Check if this is a valid sellTask */
        if (!plugin.getRegionManager().isValidRegion(loc.getWorld(), chunk.getX(), chunk.getZ())) {
            sender.sendMessage(plugin.getYamlManager().getLanguage().errorNoLandFound);
            return true;
        }

        CubitLand cubitLand = plugin.getRegionManager().praseRegionData(loc.getWorld(), chunk.getX(), chunk.getZ());
        final String regionID = cubitLand.getLandName();

        if (cubitLand.getCubitType() != CubitType.WORLD) {
            sender.sendMessage(plugin.getYamlManager().getLanguage().errorNoValidLandFound.replace("{type}",
                    CubitType.WORLD.toString()));
            return true;
        }

        if (!plugin.getRegionManager().hasLandPermission(cubitLand, player.getUniqueId()) && !this.isAdmin) {
            sender.sendMessage(plugin.getYamlManager().getLanguage().errorNoLandPermission.replace("{regionID}",
                    cubitLand.getLandName()));
            return true;
        }

        if (!plugin.getYamlManager().getSettings().freeCubitLandWorld.contains(loc.getWorld().getName())) {
            double economyValue = plugin.getVaultManager().calculateLandCost(player.getUniqueId(), loc.getWorld(), false);

            if (!plugin.getVaultManager().transferMoney(null, cubitLand.getOwnersUUID()[0], economyValue)) {
                /* If this task failed! This should never happen */
                sender.sendMessage(plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "CREATE-ECONOMY"));
                plugin.getLogger()
                        .warning(plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "CREATE-ECONOMY"));
                return true;
            }
        }

        if (!plugin.getRegionManager().removeLand(cubitLand, loc.getWorld())) {
            /* If this task failed! This should never happen */
            sender.sendMessage(plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "DELETE-REGION"));
            plugin.getLogger()
                    .warning(plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "DELETE-REGION"));
            return true;
        }

        if (!plugin.getBlockManager().getBlockHandler().placeLandBorder(chunk,
                CubitBukkitPlugin.inst().getYamlManager().getSettings().landSellMaterialBorder)) {
            /* If this task failed! This should never happen */
            sender.sendMessage(plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "CREATE-BLOCK"));
            plugin.getLogger()
                    .warning(plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "CREATE-BLOCK"));
            return true;
        }
        if (!plugin.getDataAccessManager().databaseType.set_remove_offer(cubitLand.getLandName(), loc.getWorld())) {
            /* If this task failed! This should never happen */
            sender.sendMessage(
                    plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "SELL-REMOVEOFFER"));
            plugin.getLogger()
                    .warning(plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "SELL-REMOVEOFFER"));
            return true;
        }

        /* Call cubit sell land event */
        CubitLandSellEvent cubitLandSellEvent = new CubitLandSellEvent(loc.getWorld(), regionID);
        Bukkit.getScheduler().runTask(plugin, () -> plugin.getServer().getPluginManager().callEvent(cubitLandSellEvent));

        if (!plugin.getParticleManager().sendSell(player, loc)) {
            /* If this task failed! This should never happen */
            sender.sendMessage(plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "CREATE-PARTICLE"));
            plugin.getLogger()
                    .warning(plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "CREATE-PARTICLE"));
            return true;
        }

        /* Task was successfully. Send BuyMessage */
        sender.sendMessage(plugin.getYamlManager().getLanguage().sellSuccess.replace("{regionID}", regionID));
        return true;
    }

}
