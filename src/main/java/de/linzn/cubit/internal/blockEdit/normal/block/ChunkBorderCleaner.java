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

package de.linzn.cubit.internal.blockEdit.normal.block;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

public class ChunkBorderCleaner implements Runnable {

    private Plugin plugin;
    private Chunk chunk;
    private Material airMaterial;

    private Worker work;
    private int[] directions;

    public ChunkBorderCleaner(Plugin plugin, Chunk chunk, Material airMaterial, int[] directions) {
        this.plugin = plugin;
        this.chunk = chunk;
        this.airMaterial = airMaterial;
        work = new Worker();
        this.directions = directions;
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, this);
    }

    @Override
    public void run() {
        if (work.isTaskComplete()) {
            return;
        }

        for (int count = 0; count < 4 && !work.isTaskComplete(); work.i++, count++) {
            Location corner_1 = null;
            if (directions[0] == 0) {
                corner_1 = chunk.getBlock(work.i, 0, 0).getLocation();
                corner_1.setY(getValidBlockHight(chunk.getWorld(), corner_1.getBlockX(), corner_1.getBlockZ()));
                if (corner_1.getBlock().getType() != Material.TORCH) {
                    corner_1 = null;
                }
            }
            Location corner_2 = null;
            if (directions[1] == 0) {
                corner_2 = chunk.getBlock(15, 0, work.i).getLocation();
                corner_2.setY(getValidBlockHight(chunk.getWorld(), corner_2.getBlockX(), corner_2.getBlockZ()));
                if (corner_2.getBlock().getType() != Material.TORCH) {
                    corner_2 = null;
                }
            }
            Location corner_3 = null;
            if (directions[2] == 0) {
                corner_3 = chunk.getBlock((15 - work.i), 0, 15).getLocation();
                corner_3.setY(getValidBlockHight(chunk.getWorld(), corner_3.getBlockX(), corner_3.getBlockZ()));
                if (corner_3.getBlock().getType() != Material.TORCH) {
                    corner_3 = null;
                }
            }
            Location corner_4 = null;
            if (directions[3] == 0) {
                corner_4 = chunk.getBlock(0, 0, (15 - work.i)).getLocation();
                corner_4.setY(getValidBlockHight(chunk.getWorld(), corner_4.getBlockX(), corner_4.getBlockZ()));
                if (corner_4.getBlock().getType() != Material.TORCH) {
                    corner_4 = null;
                }
            }
            setBlockSync(airMaterial, corner_1, corner_2, corner_3, corner_4);
        }
        if (!work.isTaskComplete()) {
            plugin.getServer().getScheduler().runTaskAsynchronously(plugin, this);
        }
    }

    public int getValidBlockHight(World world, int x, int z) {
        int y;
        for (y = 255; y > 0; y--) {
            if ((world.getBlockAt(x, y, z).getType() != Material.AIR)
                    && (world.getBlockAt(x, y, z).getType() != Material.END_PORTAL_FRAME)
                    && (world.getBlockAt(x, y, z).getType() != Material.BEDROCK)) {
                if ((world.getBlockAt(x, y, z).getType() == Material.TORCH)) {
                    break;
                }
                y--;
                break;
            }
        }
        return y + 1;
    }

    private void setBlockSync(final Material material, final Location corner_1, final Location corner_2,
                              final Location corner_3, final Location corner_4) {
        plugin.getServer().getScheduler().runTask(plugin, () -> {
            if (corner_1 != null)
                chunk.getWorld().getBlockAt(corner_1).setType(material);
            if (corner_2 != null)
                chunk.getWorld().getBlockAt(corner_2).setType(material);
            if (corner_3 != null)
                chunk.getWorld().getBlockAt(corner_3).setType(material);
            if (corner_4 != null)
                chunk.getWorld().getBlockAt(corner_4).setType(material);
        });
    }

    public class Worker {
        public int i = 0;

        public boolean isTaskComplete() {
            return i == 15;
        }
    }
}
