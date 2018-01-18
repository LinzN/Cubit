/*
 * Copyright (C) 2018. MineGaming - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 *  You should have received a copy of the LGPLv3 license with
 *  this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.cubit.internal.YamlConfigurationMgr;

import de.linzn.cubit.internal.YamlConfigurationMgr.files.FlagProtectionsYaml;
import de.linzn.cubit.internal.YamlConfigurationMgr.files.LanguageYaml;
import de.linzn.cubit.internal.YamlConfigurationMgr.files.LimitYaml;
import de.linzn.cubit.internal.YamlConfigurationMgr.files.SettingsYaml;
import de.linzn.cubit.internal.YamlConfigurationMgr.setup.YamlFileSetup;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class YamlConfigurationManager {

    private YamlFileSetup fileOperator;
    private Plugin plugin;

    public YamlConfigurationManager(JavaPlugin plugin) {
        plugin.getLogger().info("Loading YamlConfigurationManager");
        this.plugin = plugin;
        this.fileOperator = new YamlFileSetup(this.plugin);
    }

    public void reloadAllConfigs() {
        plugin.getLogger().info("Reloading all cubit configs");
        this.fileOperator = new YamlFileSetup(this.plugin);
    }

    public SettingsYaml getSettings() {
        return this.fileOperator.settings;
    }

    public LanguageYaml getLanguage() {
        return this.fileOperator.language;
    }

    public LimitYaml getLimit() {
        return this.fileOperator.limit;
    }

    public FlagProtectionsYaml getFlag() {
        return this.fileOperator.flag;
    }

}
