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

package de.linzn.cubit.internal.particle.border;

import org.bukkit.Chunk;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ParticleSender {

    private Player player;
    private ArrayList<Location> edgeBlocks;
    private Effect primaryEffect;
    private Effect secondaryEffect;

    public ParticleSender(Player player, Location location, Effect primaryEffect, Effect secondaryEffect) {
        this.player = player;
        this.edgeBlocks = getChunkEdgeLocation(location);
        this.primaryEffect = primaryEffect;
        this.secondaryEffect = secondaryEffect;
        if (isSpigot()) {
            startParticleLoop();
        }
    }

    private ArrayList<Location> getChunkEdgeLocation(Location loc) {
        Chunk chunk = loc.getChunk();
        ArrayList<Location> edgeBlocks = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            for (int ii = -1; ii <= 10; ii++) {
                edgeBlocks.add(chunk.getBlock(i, (int) (loc.getY()) + ii, 15).getLocation());
                edgeBlocks.add(chunk.getBlock(i, (int) (loc.getY()) + ii, 0).getLocation());
                edgeBlocks.add(chunk.getBlock(0, (int) (loc.getY()) + ii, i).getLocation());
                edgeBlocks.add(chunk.getBlock(15, (int) (loc.getY()) + ii, i).getLocation());
            }
        }
        return edgeBlocks;
    }

    private void sendSpigotParticle() {
        for (Location edgeBlock : this.edgeBlocks) {
            edgeBlock.setZ(edgeBlock.getBlockZ() + .5);
            edgeBlock.setX(edgeBlock.getBlockX() + .5);
            if (this.primaryEffect != null) {
                this.player.spigot().playEffect(edgeBlock, this.primaryEffect, 0, 0, 0f, 0f, 0f, 0.001f, 1, 32);
            }
            if (this.secondaryEffect != null) {
                this.player.spigot().playEffect(edgeBlock, this.secondaryEffect, 0, 0, 0f, 0f, 0f, 0.001f, 1, 32);
            }
        }
    }


    private void startParticleLoop() {
        int loopValue = 0;
        while (loopValue <= 5) {
            sendSpigotParticle();
            loopValue++;
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean isSpigot() {
        try {
            Class.forName("net.md_5.bungee.api.ChatColor");
            return true;
        } catch (final ClassNotFoundException e) {
            return false;
        }
    }

}
