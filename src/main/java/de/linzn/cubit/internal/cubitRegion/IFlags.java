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

package de.linzn.cubit.internal.cubitRegion;

import de.linzn.cubit.internal.cubitRegion.region.CubitLand;
import org.bukkit.ChatColor;

public interface IFlags {
    CubitLand enable(CubitLand cubitLand);

    CubitLand disable(CubitLand cubitLand);

    boolean getStatus(CubitLand cubitLand);

    ChatColor getStatusColor(CubitLand cubitLand);

    CubitLand switchStatus(CubitLand cubitLand, boolean value, boolean save);

    CubitLand switchStatus(CubitLand cubitLand, boolean save);

    void refresh(CubitLand cubitLand, boolean save);

    String getStatusString(CubitLand cubitLand);

    String getProtectionName();
}
