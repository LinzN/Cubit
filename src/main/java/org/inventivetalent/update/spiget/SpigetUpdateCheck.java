
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

package org.inventivetalent.update.spiget;

import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import org.inventivetalent.update.spiget.comparator.VersionComparator;

public class SpigetUpdateCheck {
    public boolean isAvailable;
    public String version;
    private CubitBukkitPlugin plugin;

    public SpigetUpdateCheck(CubitBukkitPlugin plugin) {
        this.plugin = plugin;

        this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(this.plugin, () -> {
            CubitBukkitPlugin.inst().getLogger().info("Run spiget update checker");
            checker();
        }, 20 * 30, 20 * 60 * 30);
    }

    private void checker() {

        SpigetUpdate updater = new SpigetUpdate(this.plugin, 31850);

        // This converts a semantic version to an integer and checks if the
        // updated version is greater
        updater.setVersionComparator(VersionComparator.SEM_VER);

        updater.checkForUpdate(new UpdateCallback() {
            @Override
            public void updateAvailable(String newVersion, String downloadUrl, boolean hasDirectDownload) {
                isAvailable = true;
                version = newVersion;
                plugin.getLogger().info("A new update is available. Version: " + newVersion);

            }

            @Override
            public void upToDate() {
                isAvailable = false;
            }
        });
    }

}
