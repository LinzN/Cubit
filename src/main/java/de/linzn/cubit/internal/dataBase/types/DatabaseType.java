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

package de.linzn.cubit.internal.dataBase.types;

import de.linzn.cubit.internal.dataBase.OfferData;
import org.bukkit.World;

import java.sql.Connection;
import java.util.UUID;

public interface DatabaseType {

    boolean setupDatabase();

    Connection createConnection();

    boolean releaseConnection(Connection con);

    // Input

    boolean set_create_offer(OfferData data);

    boolean set_remove_offer(String regionID, World world);

    boolean set_update_profile(UUID uuid, String player, long time);

    boolean set_buyup_time(UUID uuid, int time);

    // Output

    long get_last_login_profile(UUID uuid);

    String get_profile_name(UUID uuid);

    long get_last_login_profile(String p);

    OfferData get_offer(String regionID, World world);

    boolean get_is_offer(String regionID, World world);

    int get_buyup_time(UUID uuid);

    // Other
    String get_formate_date(long date);

}
