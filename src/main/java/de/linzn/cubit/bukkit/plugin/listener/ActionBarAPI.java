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

package de.linzn.cubit.bukkit.plugin.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

class ActionBarAPI {
    static void sendActionbar(Player player, String msg) {
        try {
            Constructor<?> constructor = getNMSClass("PacketPlayOutChat").getConstructor(getNMSClass("IChatBaseComponent"), getNMSClass("ChatMessageType"));

            Object icbc = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + msg + "\"}");
            Object packet = constructor.newInstance(icbc, getNMSClass("ChatMessageType").getEnumConstants()[2]);
            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);

            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Class<?> getNMSClass(String name) {
        try {
            return Class.forName("net.minecraft.server." + getVersion() + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }
}
