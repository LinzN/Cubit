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

package de.linzn.cubit.internal.configurations.files;

import de.linzn.cubit.internal.configurations.setup.CustomConfig;

public class FlagProtectionsYaml {

    public boolean worldRegionPacketFire;
    public boolean worldRegionPacketLock;
    public boolean worldRegionPacketMonster;
    public boolean worldRegionPacketMonsterDisableDamage;
    public boolean worldRegionPacketAnimals;
    public boolean worldRegionPacketPotion;
    public boolean worldRegionPacketPVP;
    public boolean worldRegionPacketTNT;
    public boolean shopRegionPacketFire;
    public boolean shopRegionPacketLock;
    public boolean shopRegionPacketMonster;
    public boolean shopRegionPacketMonsterDisableDamage;
    public boolean shopRegionPacketAnimals;
    public boolean shopRegionPacketPotion;
    public boolean shopRegionPacketPVP;
    public boolean shopRegionPacketTNT;
    public boolean serverRegionPacketFire;
    public boolean serverRegionPacketLock;
    public boolean serverRegionPacketMonster;
    public boolean serverRegionPacketMonsterDisableDamage;
    public boolean serverRegionPacketAnimals;
    public boolean serverRegionPacketPotion;
    public boolean serverRegionPacketPVP;
    public boolean serverRegionPacketTNT;
    private CustomConfig configFile;

    public FlagProtectionsYaml(CustomConfig configFile) {
        this.configFile = configFile;
        setup();
        this.configFile.saveAndReload();
    }

    public void setup() {
        worldRegionPacketFire = (boolean) this.getObjectValue("flags.worldregion.default.fire", false);
        worldRegionPacketLock = (boolean) this.getObjectValue("flags.worldregion.default.lock", true);
        worldRegionPacketMonster = (boolean) this.getObjectValue("flags.worldregion.default.monster", false);
        worldRegionPacketAnimals = (boolean) this.getObjectValue("flags.worldregion.default.animals", true);
        worldRegionPacketPotion = (boolean) this.getObjectValue("flags.worldregion.default.potion", false);
        worldRegionPacketPVP = (boolean) this.getObjectValue("flags.worldregion.default.pvp", false);
        worldRegionPacketTNT = (boolean) this.getObjectValue("flags.worldregion.default.tnt", false);

        shopRegionPacketFire = (boolean) this.getObjectValue("flags.shopregion.default.fire", false);
        shopRegionPacketLock = (boolean) this.getObjectValue("flags.shopregion.default.lock", true);
        shopRegionPacketMonster = (boolean) this.getObjectValue("flags.shopregion.default.monster", false);
        shopRegionPacketAnimals = (boolean) this.getObjectValue("flags.shopregion.default.animals", true);
        shopRegionPacketPotion = (boolean) this.getObjectValue("flags.shopregion.default.potion", false);
        shopRegionPacketPVP = (boolean) this.getObjectValue("flags.shopregion.default.pvp", false);
        shopRegionPacketTNT = (boolean) this.getObjectValue("flags.shopregion.default.tnt", false);

        serverRegionPacketFire = (boolean) this.getObjectValue("flags.serverregion.default.fire", false);
        serverRegionPacketLock = (boolean) this.getObjectValue("flags.serverregion.default.lock", true);
        serverRegionPacketMonster = (boolean) this.getObjectValue("flags.serverregion.default.monster", false);
        serverRegionPacketAnimals = (boolean) this.getObjectValue("flags.serverregion.default.animals", true);
        serverRegionPacketPotion = (boolean) this.getObjectValue("flags.serverregion.default.potion", false);
        serverRegionPacketPVP = (boolean) this.getObjectValue("flags.serverregion.default.pvp", false);
        serverRegionPacketTNT = (boolean) this.getObjectValue("flags.serverregion.default.tnt", false);

        worldRegionPacketMonsterDisableDamage = (boolean) this.getObjectValue("flags.monster.worldregion.disableMonsterDamage", true);
        shopRegionPacketMonsterDisableDamage = (boolean) this.getObjectValue("flags.monster.shopregion.disableMonsterDamage", true);
        serverRegionPacketMonsterDisableDamage = (boolean) this.getObjectValue("flags.monster.serverregion.disableMonsterDamage", true);
    }

    public Object getObjectValue(String path, Object defaultValue) {
        if (!this.configFile.contains(path)) {
            this.configFile.set(path, defaultValue);
        }
        return this.configFile.get(path);

    }

    public CustomConfig getFile() {
        return this.configFile;
    }

}
