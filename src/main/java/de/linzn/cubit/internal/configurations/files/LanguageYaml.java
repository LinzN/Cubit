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
import org.bukkit.ChatColor;

public class LanguageYaml {

    /* Info MSG */
    public String startBiomeChange;
    public String enterRegionGreetings;
    public String enterOwnRegionGreetings;
    public String blockEditReconnectInfo;
    /* Error MSG */
    public String noConsoleMode;
    public String disabledCommand;
    public String errorInTask;
    public String errorCommand;
    public String errorNoCommand;
    public String errorNoPermission;
    public String errorNoLandPermission;
    public String errorNoLandFound;
    public String errorNoValidLandFound;
    public String noNumberFound;
    public String noEnabledWorld;
    public String reachLimit;
    public String notABiome;
    public String pageNotFound;
    public String noValidWEAdapter;
    /* Success MSG */
    public String buySuccess;
    public String createShopLand;
    public String deleteShopLand;
    public String sellSuccess;
    public String addMemberSuccess;
    public String removeMemberSuccess;
    public String offerAddSuccess;
    public String offerRemoveSuccess;
    public String kickedInfo;
    public String kickInfo;
    public String whoInfo;
    public String isFreeAndBuyable;
    public String changedBiome;
    public String setbuyuptime;
    /* UnSuccess MSG */
    public String buyIsAlreadyLand;
    public String isAlreadyLand;
    public String notToLongOffline;
    public String notEnoughMoney;
    public String takeOwnLand;
    public String notOffered;
    public String noRegionsFound;
    public String wrongArguments;
    public String errorPvpIsEnabled;

    /* Flag switch msg */
    public String pvpOn;
    public String pvpOff;
    public String monsterOn;
    public String monsterOff;
    public String tntOn;
    public String tntOff;
    public String animalsOn;
    public String animalsOff;
    public String lockOn;
    public String lockOff;
    public String potionsOn;
    public String potionsOff;
    public String fireOn;
    public String fireOff;


    /* Header for Plugin */
    public String landHeader;
    public String landBiomeListHeader;
    public String flagStateActive;
    public String flagStateInactive;
    /* Land List Page */
    public String landListHeader;
    public String landListEntry;
    /* Land Info Page */
    public String landInfoA1;
    public String landInfoA2;
    public String landInfoA3;
    public String landInfoE1;
    public String landInfoE2;
    public String landInfoE3;
    public String landInfoE4;
    public String landInfoE5;
    public String landInfoE6;
    /* Page 1 Help for Land Command */
    public String landHelpHeaderP1;
    public String landHelpE1P1;
    public String landHelpE2P1;
    public String landHelpE3P1;
    public String landHelpE4P1;
    public String landHelpE5P1;
    public String landHelpE6P1;
    public String landHelpE7P1;
    /* Page 2 Help for Land Command */
    public String landHelpHeaderP2;
    public String landHelpE1P2;
    public String landHelpE2P2;
    public String landHelpE3P2;
    public String landHelpE4P2;
    public String landHelpE5P2;
    public String landHelpBottomP2;
    /* Page 3 Help for Land Command */
    public String landHelpHeaderP3;
    public String landHelpE1P3;
    public String landHelpE2P3;
    public String landHelpE3P3;
    public String landHelpE4P3;
    public String landHelpBottomP3;
    /* Page 4 Help for Land Command */
    public String landHelpHeaderP4;
    public String landHelpE1P4;
    public String landHelpE2P4;
    public String landHelpE3P4;
    public String landHelpE4P4;
    public String landHelpE5P4;
    public String landHelpE6P4;
    public String landHelpBottomP4;
    /* Page 5 Help for Land Command */
    public String landHelpHeaderP5;
    public String landHelpE1P5;
    public String landHelpE2P5;
    public String landHelpBottomP5;
    /* Page 1 Help for Shop Command */
    public String shopHelpHeaderP1;
    public String shopHelpE1P1;
    public String shopHelpE2P1;
    public String shopHelpE3P1;
    public String shopHelpE4P1;
    public String shopHelpE5P1;
    public String shopHelpE6P1;
    /* Page 2 Help for Shop Command */
    public String shopHelpHeaderP2;
    public String shopHelpE1P2;
    public String shopHelpE2P2;
    public String shopHelpBottomP2;
    /* Page 3 Help for Shop Command */
    public String shopHelpHeaderP3;
    public String shopHelpE1P3;
    public String shopHelpE2P3;
    public String shopHelpE3P3;
    public String shopHelpE4P3;
    public String shopHelpBottomP3;
    /* Page 4 Help for Shop Command */
    public String shopHelpHeaderP4;
    public String shopHelpE1P4;
    public String shopHelpE2P4;
    public String shopHelpE3P4;
    public String shopHelpE4P4;
    public String shopHelpBottomP4;
    /* Page 1 Help for Admin Command */
    public String adminHelpHeaderP1;
    public String adminHelpE1P1;
    public String adminHelpE2P1;
    public String adminHelpE3P1;
    public String adminHelpE4P1;
    public String adminHelpE5P1;
    public String adminHelpE6P1;
    /* Page 2 Help for Admin Command */
    public String adminHelpHeaderP2;
    public String adminHelpE1P2;
    public String adminHelpE2P2;
    public String adminHelpE3P2;
    public String adminHelpE4P2;
    public String adminHelpE5P2;
    /* Page 3 Help for Admin Command */
    public String adminHelpHeaderP3;
    public String adminHelpE1P3;
    public String adminHelpE2P3;
    public String adminHelpE3P3;
    public String adminHelpE4P3;
    public String adminHelpE5P3;
    public String adminHelpE6P3;
    /* Page 4 Help for Admin Command */
    public String adminHelpHeaderP4;
    public String adminHelpE1P4;
    public String adminHelpE2P4;
    /* Page 5 Help for Admin Command */
    public String adminHelpHeaderP5;
    public String adminHelpE1P5;
    public String adminHelpE2P5;
    public String adminHelpE3P5;
    public String adminHelpE4P5;
    public String adminHelpE5P5;
    /* Page 1 Help for Cubit Command */
    public String cubitHelpHeaderP1;
    public String cubitHelpE1P1;
    public String cubitHelpE2P1;

    /* Land confirm Task like biome */
    public String landEditConfirmTask;
    public String landEditConfirmTaskCancel;
    public String landEditConfirmInfoBiome;
    public String landEditConfirmInfoReset;


    private CustomConfig configFile;

    public LanguageYaml(CustomConfig configFile) {
        this.configFile = configFile;
        setup();
        this.configFile.saveAndReload();
    }

    private void setup() {

        /* Info MSG */
        startBiomeChange = this.getLanguageString("startBiomeChange", "&2Starting biome change for region {regionID}!");
        blockEditReconnectInfo  = this.getLanguageString("blockEditReconnectInfo", "&2The see the result of this operation it could be that you need to perform a reconnect!");
        enterRegionGreetings = this.getLanguageString("enterRegionGreetings", "&e{player}`s &2region &e{regionID}&2 {pvp}");
        enterOwnRegionGreetings = this.getLanguageString("enterOwnRegionGreetings", "&eYour &2region &e{regionID}&2 {pvp}");
        /* Error MSG */
        noConsoleMode = this.getLanguageString("noConsoleMode", "&4Sorry this is not available in console mode!");
        disabledCommand = this.getLanguageString("disabledCommand", "&4Sorry this command is disabled!");
        noEnabledWorld = this.getLanguageString("noEnabledWorld",
                "&4Sorry but this command is not available in this world!");
        errorInTask = this.getLanguageString("errorInTask", "&4An internal error occurred during: [&e{error}&4]");
        errorCommand = this.getLanguageString("errorCommand",
                "&4An error occurred during execution command [&e{command}&4]!");
        errorNoCommand = this.getLanguageString("errorNoCommand", "&cUnknown command! Use &e{command}&c for help!");
        errorNoPermission = this.getLanguageString("errorNoPermission", "&4You don have access to that command! :(");
        errorNoLandPermission = this.getLanguageString("errorNoLandPermission",
                "&cYou don have access for region {regionID}! :(");
        errorNoLandFound = this.getLanguageString("errorNoLandFound", "&cNo region found here! Sorry :(");
        errorNoValidLandFound = this.getLanguageString("errorNoLandFound",
                "&cNo valid {type} region found here! Sorry :(");
        noNumberFound = this.getLanguageString("noNumberFound", "&cThis is not a valid number");
        reachLimit = this.getLanguageString("reachLimit", "&cSorry you reached the limit for this regionType!");
        notABiome = this.getLanguageString("notABiome", "&cThis is not a valid biomename: {biome}!");
        pageNotFound = this.getLanguageString("pageNotfound", "&cThis is not a valid page");
        noValidWEAdapter = this.getLanguageString("noValidWEAdapter",
                "&cWorldEdit has no valid bukkit adapter for this server. Please update WorldEdit!");
        /* Success MSG */
        buySuccess = this.getLanguageString("buySuccess", "&2You bought the region &e{regionID}&2!");
        isFreeAndBuyable = this.getLanguageString("isFreeAndBuyable",
                "&2This region &e{regionID} &2is buyable for &6{price}&2!");
        sellSuccess = this.getLanguageString("sellSuccess", "&2You sold the region &e{regionID}&2 to the server!");
        addMemberSuccess = this.getLanguageString("addMemberSuccess",
                "&2You added {member} to the region &e{regionID}&2!");
        removeMemberSuccess = this.getLanguageString("removeMemberSuccess",
                "&2You removed {member} from the region &e{regionID}&2!");
        offerAddSuccess = this.getLanguageString("offerAddSuccess",
                "&2You offer the region &e{regionID}&2 for &e{value}&2!");
        offerRemoveSuccess = this.getLanguageString("offerRemoveSuccess",
                "&2You offer the region &e{regionID}&2 no more!");
        kickedInfo = this.getLanguageString("kickedInfo",
                "&6You kicked from the region {regionID} by the region owner!");
        kickInfo = this.getLanguageString("kickInfo", "&2All non-member kicked from the region &e{regionID}&2!");
        whoInfo = this.getLanguageString("whoInfo", "&2The following players are on the region &e{regionID}&2\n &a {players}");
        changedBiome = this.getLanguageString("changedBiome",
                "&2You changed the biome from region &e{regionID} &2to &e{biome}&2!");

        setbuyuptime = this.getLanguageString("setBuyupTime", "&2You set the buyuptime for &e{player} &2to &e{time} &2days!");

        /* UnSuccess MSG */
        buyIsAlreadyLand = this.getLanguageString("buyIsAlreadyLand",
                "&cSorry. This region is already &e{regionID}&c bought by someone!");

        createShopLand = this.getLanguageString("createShop", "&a You created region {regionID}!");
        deleteShopLand = this.getLanguageString("deleteShop", "&a You deleted region {regionID}!");

        isAlreadyLand = this.getLanguageString("isAlreadyLand", "&cHere is already the region &e{regionID}&c!");
        notEnoughMoney = this.getLanguageString("notEnoughMoney", "&cYou can not afford it. Price: &e{cost}&c!");
        takeOwnLand = this.getLanguageString("takeOwnLand", "&cYou can not buy your own region!");
        notOffered = this.getLanguageString("notOffered", "&cThe region {regionID} is not offered!");
        wrongArguments = this.getLanguageString("wrongArguments", "&cWrong arguments: Use {usage}!");
        errorPvpIsEnabled = this.getLanguageString("errorPvpIsEnabled", "&cPvP is on this region enabled. This command is not possible!");
        noRegionsFound = this.getLanguageString("noRegionsFound", "&cNo regions found. Sry!");
        notToLongOffline = this.getLanguageString("notToLongOffline", "&cThis player is not long enough offline!");
        /* Header for Plugin */
        landHeader = this.getLanguageString("theme.general.header",
                "&6<<<<<<<<<<<<<<<<<<<<<&2&l|Region Info|&6>>>>>>>>>>>>>>>>>>>>>");

        flagStateActive = this.getLanguageString("theme.general.flagStateActive", "Enabled");
        flagStateInactive = this.getLanguageString("theme.general.flagStateInactive", "Disabled");

        /* Land Info Page */
        landInfoA1 = this.getLanguageString("theme.landinfo.empty",
                "&2This region [&e{regionID}&2] is buyable. \n&2Buy it with &e/land buy&2. Price &e{cost}&2!");
        landInfoA2 = this.getLanguageString("theme.landinfo.offered",
                "&2This region is offered for &e{value}&2. Type &e/land takeoffer &2if you want to buy it!");
        landInfoA3 = this.getLanguageString("theme.landinfo.buyupable",
                "&2The owner of this region is to long offline. Type &e/land buyup &2if you want to buy it!");
        landInfoE1 = this.getLanguageString("theme.landinfo.regionID", "&2Region: &9{regionID}");
        landInfoE2 = this.getLanguageString("theme.landinfo.landOwner", "&2Owner: &9{owner}");
        landInfoE3 = this.getLanguageString("theme.landinfo.landMember", "&2Members: &5{members}");
        landInfoE4 = this.getLanguageString("theme.landinfo.landArea",
                "&2Area-corner: From [&e{min}&2] to [&e{max}&2]");
        landInfoE5 = this.getLanguageString("theme.landinfo.lastLogin", "&2Last login: &e{time}");
        landInfoE6 = this.getLanguageString("theme.landinfo.flagPackets",
                "&2Flags: {lock}, {monster}, {animals}, {fire}, {pvp}, {tnt}, {potion}");

        /* Page 1 Help for Land Command */
        landHelpHeaderP1 = this.getLanguageString("theme.helpPage1.header",
                "&6<<<<<<<<<<<<<<<<<&2&l|Region Help|&6>>>>>>>>>>>>>>>>>");
        landHelpE1P1 = this.getLanguageString("theme.helpPage1.help1", "&2 Region informations: &e/land info");
        landHelpE2P1 = this.getLanguageString("theme.helpPage1.help2", "&2 List all your regions: &e/land list (Page)");
        landHelpE3P1 = this.getLanguageString("theme.helpPage1.help3", "&6&lMore commands on the following pages:");
        landHelpE4P1 = this.getLanguageString("theme.helpPage1.help4", "&2 Page 2: Buy - Sell &a/land help 2");
        landHelpE5P1 = this.getLanguageString("theme.helpPage1.help5", "&2 Page 3: Manage Members &a/land help 3");
        landHelpE6P1 = this.getLanguageString("theme.helpPage1.help6", "&2 Page 4: Region flags &a/land help 4");
        landHelpE7P1 = this.getLanguageString("theme.helpPage1.help7", "&2 Page 5: Land edit &a/land help 5");

        /* Page 2 Help for Land Command */
        landHelpHeaderP2 = this.getLanguageString("theme.helpPage2.header", "&6&lBuy - Sell: [Page 2]");
        landHelpE1P2 = this.getLanguageString("theme.helpPage2.help1", "&2 Buy a region: &e/land buy");
        landHelpE2P2 = this.getLanguageString("theme.helpPage2.help2", "&2 Sell a region: &e/land sell");
        landHelpE3P2 = this.getLanguageString("theme.helpPage2.help3", "&2 Take a offered region: &e/land takeoffer");
        landHelpE4P2 = this.getLanguageString("theme.helpPage2.help4", "&2 Offer a region: &e/land offer [Price]");
        landHelpE5P2 = this.getLanguageString("theme.helpPage2.help5", "&2 Buyup outdated regions: &e/land buyup");
        landHelpBottomP2 = this.getLanguageString("theme.helpPage2.bottom", "&a&lMore on page 3 with &6/land help 3");

        /* Page 3 Help for Land Command */
        landHelpHeaderP3 = this.getLanguageString("theme.helpPage3.header", "&6&lManage Members: [Page 3]");
        landHelpE1P3 = this.getLanguageString("theme.helpPage3.help1",
                "&2 Set player build-rights : &e/land add (-all) [player]");
        landHelpE2P3 = this.getLanguageString("theme.helpPage3.help2",
                "&2 Unset player build-rights: &e/land remove (-all) [player]");
        landHelpE3P3 = this.getLanguageString("theme.helpPage3.help3",
                "&2 Kick non-members from a region (teleporting): &e/land kick");
        landHelpE4P3 = this.getLanguageString("theme.helpPage3.help4",
                "&2 Show all players standing on the current region: &e/land who");
        landHelpBottomP3 = this.getLanguageString("theme.helpPage3.bottom", "&a&lMore on page 4 with &6/land help 4");

        /* Page 4 Help for Land Command */
        landHelpHeaderP4 = this.getLanguageString("theme.helpPage4.header", "&6&lRegion Flags: [Page 4]");
        landHelpE1P4 = this.getLanguageString("theme.helpPage4.help1",
                "&2 Fire: &e/land fire (&aON&e/&cOFF&e)");
        landHelpE2P4 = this.getLanguageString("theme.helpPage4.help2",
                "&2 Access: &e/land lock (&aON&e/&cOFF&e)");
        landHelpE3P4 = this.getLanguageString("theme.helpPage4.help3",
                "&2 PvP: &e/land pvp (&aON&e/&cOFF&e)");
        landHelpE4P4 = this.getLanguageString("theme.helpPage4.help4",
                "&2 TnT: &e/land tnt (&aON&e/&cOFF&e)");
        landHelpE5P4 = this.getLanguageString("theme.helpPage4.help5",
                "&2 Monster: &e/land monster (&aON&e/&cOFF&e)");
        landHelpE6P4 = this.getLanguageString("theme.helpPage4.help6",
                "&2 Animals: &e/land animals (&aON&e/&cOFF&e)");
        landHelpBottomP4 = this.getLanguageString("theme.helpPage4.bottom", "&a&lMore on page 5 with &6/land help 5");

        /* Page 5 Help for Land Command */
        landHelpHeaderP5 = this.getLanguageString("theme.helpPage5.header", "&6&lLand edit: [Page 5]");
        landHelpE1P5 = this.getLanguageString("theme.helpPage5.help1", "&2 Change biome: &e/land changebiome [Biome]");
        landHelpE2P5 = this.getLanguageString("theme.helpPage5.help2", "&2 List biomes: &e/land listbiomes");
        landHelpBottomP5 = this.getLanguageString("theme.helpPage5.bottom", "&a&lBack to page 4 &6/land help 4");

        landBiomeListHeader = this.getLanguageString("theme.biomeList.header", "&6&lAll available biomes:");

        /* Page 1 Help for Shop Command */
        shopHelpHeaderP1 = this.getLanguageString("theme.shopHelpPage1.header",
                "&6<<<<<<<<<<<<<<<<<&2&l|Shop Help|&6>>>>>>>>>>>>>>>>>");
        shopHelpE1P1 = this.getLanguageString("theme.shopHelpPage1.help1", "&2 Shop informations: &e/shop info");
        shopHelpE2P1 = this.getLanguageString("theme.shopHelpPage1.help2",
                "&2 List all your shops: &e/shop list [Page]");
        shopHelpE3P1 = this.getLanguageString("theme.shopHelpPage1.help3", "&6&lMore commands on the following pages:");
        shopHelpE4P1 = this.getLanguageString("theme.shopHelpPage1.help4", "&2 Page 2: Buy - Sell &a/shop help 2");
        shopHelpE5P1 = this.getLanguageString("theme.shopHelpPage1.help5", "&2 Page 3: Manage Members &a/shop help 3");
        shopHelpE6P1 = this.getLanguageString("theme.shopHelpPage1.help6", "&2 Page 4: Manage Flags &a/shop help 4");

        /* Page 2 Help for Shop Command */
        shopHelpHeaderP2 = this.getLanguageString("theme.shopHelpPage2.header", "&6&lBuy - Sell: [Page 2]");
        shopHelpE1P2 = this.getLanguageString("theme.shopHelpPage2.help1", "&2 Buy a shop: &e/shop buy");
        shopHelpE2P2 = this.getLanguageString("theme.shopHelpPage2.help2", "&2 Sell a shop: &e/shop sell");
        shopHelpBottomP2 = this.getLanguageString("theme.shopHelpPage2.bottom",
                "&a&lMore on page 3 with &6/shop help 3");

        /* Page 3 Help for Land Command */
        shopHelpHeaderP3 = this.getLanguageString("theme.shopHelpPage3.header", "&6&lManage Members: [Page 3]");
        shopHelpE1P3 = this.getLanguageString("theme.shopHelpPage3.help1",
                "&2 Set player build-rights : &e/shop add (-all) [player]");
        shopHelpE2P3 = this.getLanguageString("theme.shopHelpPage3.help2",
                "&2 Unset player build-rights: &e/shop remove (-all) [player]");
        shopHelpE3P3 = this.getLanguageString("theme.shopHelpPage3.help3",
                "&2 Kick non-members from a shop (teleporting): &4/shop kick");
        shopHelpE4P3 = this.getLanguageString("theme.shopHelpPage3.help4",
                "&2 Show all players standing on the current shop: &4/shop who");
        shopHelpBottomP3 = this.getLanguageString("theme.shopHelpPage3.bottom",
                "&a&lMore on page 4 with &6/shop help 4");

        /* Page 3 Help for Land Command */
        shopHelpHeaderP4 = this.getLanguageString("theme.shopHelpPage4.header", "&6&lManage Flags: [Page 4]");
        shopHelpE1P4 = this.getLanguageString("theme.shopHelpPage4.help1",
                "&2 Fire: &e/shop fire (&aON&e/&cOFF&e)");
        shopHelpE2P4 = this.getLanguageString("theme.shopHelpPage4.help2",
                "&2 Access: &e/shop lock (&aON&e/&cOFF&e)");
        shopHelpE3P4 = this.getLanguageString("theme.shopHelpPage4.help3",
                "&2 Monster: &e/shop monster (&aON&e/&cOFF&e)");
        shopHelpE4P4 = this.getLanguageString("theme.shopHelpPage4.help4",
                "&2 Animals: &e/shop animals (&aON&e/&cOFF&e)");
        shopHelpBottomP4 = this.getLanguageString("theme.shopHelpPage4.bottom", "&a&lBack to page 3 &6/shop help 3");


        /* Land List Command */
        landListHeader = this.getLanguageString("theme.landList.header",
                "&6&lRegion Count: &r&2{count} &6&l- Entries &r&2{entryMin} &6&lfrom &r&2{entryMax}");
        landListEntry = this.getLanguageString("theme.landList.entry",
                "&a{counter}. &6{regionID} &a(&efrom &a- &eto&a) [&e{minPoints} &a- &e{maxPoints}&a]");

        /* Page 1 Help for Admin Command */
        adminHelpHeaderP1 = this.getLanguageString("theme.adminHelpPage1.header",
                "&6<<<<<<<<<<<<<<<<<&2&l|Admin Help|&6>>>>>>>>>>>>>>>>>");
        adminHelpE1P1 = this.getLanguageString("theme.adminHelpPage1.help1",
                "&2 List player regions: &e/cadmin list [Player] (page)");
        adminHelpE2P1 = this.getLanguageString("theme.adminHelpPage1.help2",
                "&6&lMore commands on the following pages:");
        adminHelpE3P1 = this.getLanguageString("theme.adminHelpPage1.help3",
                "&2 Page 2: Manage region &a/cadmin help 2");
        adminHelpE4P1 = this.getLanguageString("theme.adminHelpPage1.help4",
                "&2 Page 3: Manage flags &a/cadmin help 3");
        adminHelpE5P1 = this.getLanguageString("theme.adminHelpPage1.help5", "&2 Page 4: Land edit &a/cadmin help 4");
        adminHelpE6P1 = this.getLanguageString("theme.adminHelpPage1.help6",
                "&2 Page 5: Admin regions &a/cadmin help 5");

        /* Page 2 Help for Admin Command */
        adminHelpHeaderP2 = this.getLanguageString("theme.adminHelpPage2.header", "&6&lManage region: [Page 2]");
        adminHelpE1P2 = this.getLanguageString("theme.adminHelpPage2.help1", "&2 Remove region: &e/cadmin remove");
        adminHelpE2P2 = this.getLanguageString("theme.adminHelpPage2.help2",
                "&2 Offer region: &e/cadmin setoffer (offervalue)");
        adminHelpE3P2 = this.getLanguageString("theme.adminHelpPage2.help3",
                "&2 Add player: &e/cadmin addplayer (player)");
        adminHelpE4P2 = this.getLanguageString("theme.adminHelpPage2.help4",
                "&2 Remove player: &e/cadmin removeplayer (player)");
        adminHelpE5P2 = this.getLanguageString("theme.adminHelpPage2.help5",
                "&2 Set Buyuptime: &e/cadmin setbuyuptime (player) (time)");

        /* Page 3 Help for Admin Command */
        adminHelpHeaderP3 = this.getLanguageString("theme.adminHelpPage3.header", "&6&lManage flags: [Page 3]");
        adminHelpE1P3 = this.getLanguageString("theme.adminHelpPage3.help1",
                "&2 Fire: &e/cadmin setfire (&aON&e/&cOFF&e)");
        adminHelpE2P3 = this.getLanguageString("theme.adminHelpPage3.help2",
                "&2 Access: &e/cadmin setlock (&aON&e/&cOFF&e)");
        adminHelpE3P3 = this.getLanguageString("theme.adminHelpPage3.help3",
                "&2 PvP: &e/cadmin setpvp (&aON&e/&cOFF&e)");
        adminHelpE4P3 = this.getLanguageString("theme.adminHelpPage3.help4",
                "&2 TnT: &e/cadmin settnt (&aON&e/&cOFF&e)");
        adminHelpE5P3 = this.getLanguageString("theme.adminHelpPage3.help5",
                "&2 Monster: &e/cadmin setmonster (&aON&e/&cOFF&e)");
        adminHelpE6P3 = this.getLanguageString("theme.adminHelpPage3.help6",
                "&2 Animals: &e/cadmin setanimals (&aON&e/&cOFF&e)");

        /* Page 4 Help for Admin Command */
        adminHelpHeaderP4 = this.getLanguageString("theme.adminHelpPage4.header", "&6&lLand edit: [Page 4]");
        adminHelpE1P4 = this.getLanguageString("theme.adminHelpPage4.help1",
                "&2 Change biome: &e/cadmin changebiome (biome)");
        adminHelpE2P4 = this.getLanguageString("theme.adminHelpPage4.help2",
                "&2 List available biomes: &e/cadmin listbiomes");

        /* Page 5 Help for Admin Command */
        adminHelpHeaderP5 = this.getLanguageString("theme.adminHelpPage5.header", "&6&lAdmin regions: [Page 5]");
        adminHelpE1P5 = this.getLanguageString("theme.adminHelpPage5.help1",
                "&2 Create Serverregion: &e/cadmin createserver");
        adminHelpE2P5 = this.getLanguageString("theme.adminHelpPage5.help2",
                "&2 Delete Serverregion: &e/cadmin deleteserver");
        adminHelpE3P5 = this.getLanguageString("theme.adminHelpPage5.help3", "&2 Create Shop: &e/cadmin createshop");
        adminHelpE4P5 = this.getLanguageString("theme.adminHelpPage5.help4", "&2 Delete Shop: &e/cadmin deleteshop");
        adminHelpE5P5 = this.getLanguageString("theme.adminHelpPage5.help5", "&2 Create default region: &e/cadmin create [Player]");

        cubitHelpHeaderP1 = this.getLanguageString("theme.cubitHelpPage1.header",
                "&6<<<<<<<<<<<<<<<<<<<<&2&l|Cubit|&6>>>>>>>>>>>>>>>>>>>>");
        cubitHelpE1P1 = this.getLanguageString("theme.cubitHelpPage1.help1", "&2 Cubit version: &e/cubit version");
        cubitHelpE2P1 = this.getLanguageString("theme.cubitHelpPage1.help2", "&2 Reload cubit: &e/cubit reload");

        landEditConfirmTask = this.getLanguageString("landEditConfirmTask.info", "&eConfirm with &b/{command} {subcommand} OK \n&eYou have 20 seconds.");
        landEditConfirmTaskCancel = this.getLanguageString("landEditConfirmTask.cancel", "&cTask canceled!");
        landEditConfirmInfoBiome = this.getLanguageString("landEditConfirmTask.biome", "&eAre you sure to switch biome?");
        landEditConfirmInfoReset = this.getLanguageString("landEditConfirmTask.reset", "&eAre you sure to delete all blocks?");

        pvpOn = this.getLanguageString("flag.pvp.on", "&6PVP is now enabled on region {regionID}!");
        pvpOff = this.getLanguageString("flag.pvp.off", "&2PVP is now disabled on region {regionID}!");
        monsterOn = this.getLanguageString("flag.monster.on", "&6Monster spawning is now enabled on region {regionID}!");
        monsterOff = this.getLanguageString("flag.monster.off", "&2Monster spawning is now disabled on region {regionID}!");
        tntOn = this.getLanguageString("flag.tnt.on", "&2TNT is now enabled on region {regionID}!");
        tntOff = this.getLanguageString("flag.tnt.off", "&2TNT is now disabled on region {regionID}!");
        animalsOn = this.getLanguageString("flag.animals.on", "&2Animal spawning is now enabled on region {regionID}!");
        animalsOff = this.getLanguageString("flag.animals.off", "&6Animal spawning is now disabled on region {regionID}!");
        lockOn = this.getLanguageString("flag.lock.on", "&2Grief-protection is now enabled on region {regionID}!");
        lockOff = this.getLanguageString("flag.lock.off", "&6Grief-protection is now disabled on region {regionID}!");
        potionsOn = this.getLanguageString("flag.potions.on", "&6Potion splash is now enabled on region {regionID}!");
        potionsOff = this.getLanguageString("flag.potions.off", "&2Potion splash is now disabled on region {regionID}!");
        fireOn = this.getLanguageString("flag.fire.on", "&6Fire is now enabled on region {regionID}!");
        fireOff = this.getLanguageString("flag.fire.off", "&2Fire is now disabled on region {regionID}!");
    }

    public String getLanguageString(String path, String defaultValue) {
        if (!this.configFile.contains(path)) {
            this.configFile.set(path, defaultValue.replace("§", "&"));
        }
        return ChatColor.translateAlternateColorCodes('&', this.configFile.getString(path));

    }

    public CustomConfig getFile() {
        return this.configFile;
    }
}
