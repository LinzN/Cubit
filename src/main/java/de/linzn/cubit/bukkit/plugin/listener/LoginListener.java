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

package de.linzn.cubit.bukkit.plugin.listener;

import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.Date;

public class LoginListener implements Listener {

    @EventHandler
    public void onPlayerJoin(final PlayerLoginEvent event) {
        Bukkit.getScheduler().runTaskAsynchronously(CubitBukkitPlugin.inst(), () -> {
            CubitBukkitPlugin.inst().getDataAccessManager().databaseType.set_update_profile(
                    event.getPlayer().getUniqueId(), event.getPlayer().getName(), new Date().getTime());
            if (event.getPlayer().hasPermission(CubitBukkitPlugin.inst().getPermNodes().checkUpdateAdmin) && CubitBukkitPlugin.inst().getSpigetUpdateCheck() != null) {
                if (CubitBukkitPlugin.inst().getSpigetUpdateCheck().isAvailable) {
                    event.getPlayer()
                            .sendMessage(ChatColor.GREEN
                                    + "A new update for Cubit is avaiable. Check out the new version "
                                    + CubitBukkitPlugin.inst().getSpigetUpdateCheck().version + "!");
                }
            }
        });
    }

    /* Temp ArmorStand with hands. Thanks to Cris - Hundemaunzen*/
    @EventHandler
    public void onArmorStandArms(final PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() instanceof ArmorStand) {
            if (e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.STICK)) {
                if (!((ArmorStand) e.getRightClicked()).hasArms()) {
                    if (e.getPlayer().getInventory().getItemInMainHand().getAmount() > 2) {
                        e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount() - 2);
                    } else if (e.getPlayer().getInventory().getItemInMainHand().getAmount() == 2) {
                        e.getPlayer().getInventory().clear(e.getPlayer().getInventory().getHeldItemSlot());
                    } else {
                        return;
                    }
                    ((ArmorStand) e.getRightClicked()).setArms(true);
                    e.setCancelled(true);
                }
            }
        }
    }
}
