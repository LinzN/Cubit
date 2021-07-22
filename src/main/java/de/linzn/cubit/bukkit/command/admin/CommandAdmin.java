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

package de.linzn.cubit.bukkit.command.admin;

import de.linzn.cubit.bukkit.command.ICommand;
import de.linzn.cubit.bukkit.command.admin.main.*;
import de.linzn.cubit.bukkit.command.land.main.OfferLand;
import de.linzn.cubit.bukkit.command.land.main.SellLand;
import de.linzn.cubit.bukkit.command.universal.*;
import de.linzn.cubit.bukkit.command.universal.blockedit.EditBiomeUniversal;
import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.bukkit.plugin.PermissionNodes;
import de.linzn.cubit.internal.cubitRegion.CubitType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CommandAdmin implements CommandExecutor {

    public ThreadPoolExecutor cmdThread;
    private CubitBukkitPlugin plugin;
    private boolean isLoaded = false;
    private TreeMap<String, ICommand> cmdMap = new TreeMap<>();

    public CommandAdmin(CubitBukkitPlugin plugin) {
        this.plugin = plugin;
        this.cmdThread = new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, String label, final String[] args) {
        cmdThread.submit(() -> {
            if (args.length == 0) {
                getCmdMap().get("help").runCmd(cmd, sender, args);
            } else if (getCmdMap().containsKey(args[0])) {
                String command = args[0];
                if (!getCmdMap().get(command).runCmd(cmd, sender, args)) {
                    sender.sendMessage(
                            plugin.getYamlManager().getLanguage().errorCommand.replace("{command}", command));
                }
            } else {
                sender.sendMessage(
                        plugin.getYamlManager().getLanguage().errorNoCommand.replace("{command}", "/cadmin help"));
            }
        });
        return true;
    }

    public TreeMap<String, ICommand> getCmdMap() {
        return cmdMap;
    }

    public void loadCmd() {
        try {
            PermissionNodes perm = CubitBukkitPlugin.inst().getPermNodes();
            /* Flags AdminCommands */
            this.cmdMap.put("help", new HelpAdmin(this.plugin, perm.helpAdminLand));
            this.cmdMap.put("setpvp",
                    new ChangeFlagUniversal(this.plugin, CubitBukkitPlugin.inst().getRegionManager().pvpFlag,
                            perm.flagAdminLand + "pvp", CubitType.NOTYPE, true));
            this.cmdMap.put("setfire",
                    new ChangeFlagUniversal(this.plugin, CubitBukkitPlugin.inst().getRegionManager().fireFlag,
                            perm.flagAdminLand + "fire", CubitType.NOTYPE, true));
            this.cmdMap.put("setlock",
                    new ChangeFlagUniversal(this.plugin, CubitBukkitPlugin.inst().getRegionManager().lockFlag,
                            perm.flagAdminLand + "lock", CubitType.NOTYPE, true));
            this.cmdMap.put("settnt",
                    new ChangeFlagUniversal(this.plugin, CubitBukkitPlugin.inst().getRegionManager().tntFlag,
                            perm.flagAdminLand + "tnt", CubitType.NOTYPE, true));
            this.cmdMap.put("setmonster",
                    new ChangeFlagUniversal(this.plugin, CubitBukkitPlugin.inst().getRegionManager().monsterFlag,
                            perm.flagAdminLand + "monster", CubitType.NOTYPE, true));
            this.cmdMap.put("setanimals",
                    new ChangeFlagUniversal(this.plugin, CubitBukkitPlugin.inst().getRegionManager().animalFlag,
                            perm.flagAdminLand + "animals", CubitType.NOTYPE, true));
            this.cmdMap.put("remove", new SellLand(this.plugin, perm.sellAdminLand, true));
            this.cmdMap.put("addplayer",
                    new AddMemberUniversal(this.plugin, perm.addMemberAdminLand, CubitType.NOTYPE, true));
            this.cmdMap.put("removeplayer",
                    new RemoveMemberUniversal(this.plugin, perm.removeMemberAdminLand, CubitType.NOTYPE, true));
            this.cmdMap.put("setoffer", new OfferLand(this.plugin, perm.offerAdminLand, true));

            this.cmdMap.put("create", new CreateLandAdmin(this.plugin, perm.createAdminLand));
            this.cmdMap.put("createserver", new CreateServerAdmin(this.plugin, perm.createServerAdminLand));
            this.cmdMap.put("deleteserver", new DeleteServerAdmin(this.plugin, perm.deleteServerAdminLand));
            this.cmdMap.put("createshop", new CreateShopAdmin(this.plugin, perm.createShopAdminLand));
            this.cmdMap.put("deleteshop", new DeleteShopAdmin(this.plugin, perm.deleteShopAdminLand));

            this.cmdMap.put("changebiome",
                    new EditBiomeUniversal(this.plugin, perm.changeBiomeAdminLand, CubitType.NOTYPE, true));
            this.cmdMap.put("listbiomes",
                    new ListBiomesUniversal(this.plugin, perm.listBiomesAdminLand, CubitType.NOTYPE));
            this.cmdMap.put("list", new ListUniversal(this.plugin, perm.listAdminLand, CubitType.NOTYPE, true));
            this.cmdMap.put("setbuyuptime", new SetBuyupTime(this.plugin, perm.setbuyuptime));

            this.isLoaded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isLoaded() {
        return this.isLoaded;
    }

}
