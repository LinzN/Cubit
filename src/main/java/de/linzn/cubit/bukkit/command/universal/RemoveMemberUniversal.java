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

package de.linzn.cubit.bukkit.command.universal;

import de.linzn.cubit.bukkit.command.ICommand;
import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.cubitRegion.CubitType;
import de.linzn.cubit.internal.cubitRegion.region.CubitLand;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class RemoveMemberUniversal implements ICommand {

    private CubitBukkitPlugin plugin;
    private String permNode;
    private boolean isAdmin;
    private CubitType type;

    public RemoveMemberUniversal(CubitBukkitPlugin plugin, String permNode, CubitType type, boolean isAdmin) {
        this.plugin = plugin;
        this.isAdmin = isAdmin;
        this.permNode = permNode;
        this.type = type;

    }

    @Override
    public boolean runCmd(final Command cmd, final CommandSender sender, String[] args) {
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

        if (args.length < 2) {
            sender.sendMessage(plugin.getYamlManager().getLanguage().wrongArguments.replace("{usage}",
                    "/" + cmd.getLabel() + " " + args[0].toLowerCase() + " [Player]"));
            return true;
        }

        final Location loc = player.getLocation();
        if (args[1].equalsIgnoreCase("-a") || args[1].equalsIgnoreCase("-all")) {
            if (args.length >= 3 && !this.isAdmin) {

                @SuppressWarnings("deprecation")
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[2]);
                UUID uuid = offlinePlayer.getUniqueId();
                if (!plugin.getRegionManager().removeMemberAll(player.getUniqueId(), loc.getWorld(), uuid, type)) {
                    /* If this task failed! This should never happen */
                    sender.sendMessage(
                            plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "REMOVE-MEMBER"));
                    plugin.getLogger().warning(
                            plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "REMOVE-MEMBER"));
                    return true;
                }

                sender.sendMessage(plugin.getYamlManager().getLanguage().removeMemberSuccess
                        .replace("{regionID}", "ALL").replace("{member}", offlinePlayer.getName()));

                return true;
            } else {
                // someting
                return true;
            }
        }

        final Chunk chunk = loc.getChunk();
        CubitLand cubitLand = plugin.getRegionManager().praseRegionData(loc.getWorld(), chunk.getX(), chunk.getZ());

        /*
         * Check if the player has permissions for this land or hat landadmin
         * permissions
         */

        if (!plugin.getRegionManager().isValidRegion(loc.getWorld(), chunk.getX(), chunk.getZ())) {
            sender.sendMessage(plugin.getYamlManager().getLanguage().errorNoLandFound);
            return true;
        }

        if (cubitLand.getCubitType() != type && type != CubitType.NOTYPE) {
            sender.sendMessage(
                    plugin.getYamlManager().getLanguage().errorNoValidLandFound.replace("{type}", type.toString()));
            return true;
        }

        if (!plugin.getRegionManager().hasLandPermission(cubitLand, player.getUniqueId()) && !this.isAdmin) {
            sender.sendMessage(plugin.getYamlManager().getLanguage().errorNoLandPermission.replace("{regionID}",
                    cubitLand.getLandName()));
            return true;
        }
        @SuppressWarnings("deprecation")
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
        UUID uuid = offlinePlayer.getUniqueId();
        if (!plugin.getRegionManager().removeMember(cubitLand, loc.getWorld(), uuid)) {
            /* If this task failed! This should never happen */
            sender.sendMessage(plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "REMOVE-MEMBER"));
            plugin.getLogger()
                    .warning(plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "REMOVE-MEMBER"));
            return true;
        }

        if (!args[1].equalsIgnoreCase("-a") && !args[1].equalsIgnoreCase("-all")) {
            if (!plugin.getParticleManager().removeMember(player, loc)) {
                /* If this task failed! This should never happen */
                sender.sendMessage(
                        plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "CREATE-PARTICLE"));
                plugin.getLogger().warning(
                        plugin.getYamlManager().getLanguage().errorInTask.replace("{error}", "CREATE-PARTICLE"));
                return true;
            }
        }

        sender.sendMessage(plugin.getYamlManager().getLanguage().removeMemberSuccess
                .replace("{regionID}", cubitLand.getLandName()).replace("{member}", offlinePlayer.getName()));

        return true;
    }

}
