/*
The MIT License (MIT)

Copyright (c) 2014 Joseph DeSimpliciis

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.linzn.cubit.internal.landmapMgr;

import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.regionMgr.region.RegionData;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import java.util.Map;
import java.util.UUID;

/**
 * File created by jcdesimp on 3/1/14. Updated by SpatiumPrinceps on 20/06/17
 */
public class ScoreboardMap {

	Player mapViewer;
	int schedulerId;
	Chunk currChunk;
	String currDir;
	private CubitBukkitPlugin plugin;

	public ScoreboardMap(Player p, CubitBukkitPlugin plugin) {
		this.plugin = plugin;
		this.mapViewer = p;
		this.currChunk = p.getLocation().getChunk();
		this.currDir = getPlayerDirection(mapViewer);

		this.schedulerId = new BukkitRunnable() {
			public void run() {
				if (!currDir.equals(getPlayerDirection(mapViewer))
						|| !currChunk.equals(mapViewer.getLocation().getChunk())) {
					displayMap(mapViewer);
					currDir = getPlayerDirection(mapViewer);
				}
			}
		}.runTaskTimerAsynchronously(this.plugin, 0L, 7L).getTaskId();

		new BukkitRunnable() {

			@Override
			public void run() {
				displayMap(mapViewer);
			}
		}.runTaskAsynchronously(plugin);

	}

	public static String getPlayerDirection(Player playerSelf) {
		String dir;
		float y = playerSelf.getLocation().getYaw();
		if (y < 0) {
			y += 360;
		}
		y %= 360;
		int i = (int) ((y + 8) / 22.5);
		if (i == 0) {
			dir = "south";
		} else if (i == 1) {
			dir = "south southwest";
		} else if (i == 2) {
			dir = "southwest";
		} else if (i == 3) {
			dir = "west southwest";
		} else if (i == 4) {
			dir = "west";
		} else if (i == 5) {
			dir = "west northwest";
		} else if (i == 6) {
			dir = "northwest";
		} else if (i == 7) {
			dir = "north northwest";
		} else if (i == 8) {
			dir = "north";
		} else if (i == 9) {
			dir = "north northeast";
		} else if (i == 10) {
			dir = "northeast";
		} else if (i == 11) {
			dir = "east northeast";
		} else if (i == 12) {
			dir = "east";
		} else if (i == 13) {
			dir = "east southeast";
		} else if (i == 14) {
			dir = "southeast";
		} else if (i == 15) {
			dir = "south southeast";
		} else {
			dir = "south";
		}
		return dir;
	}

	public static String[][] getMapDir(String dir) {

		String[][] mapDir = new String[][] { { "▓", "█", "█", "∞", "█", "█", "▓" },
				{ "█", "▓", "█", "∞", "█", "▓", "█" }, { "█", "█", "▓", "∞", "▓", "█", "█" },
				{ "▓", "█", "▓", "█", "█", "▓", "█" }, { "▓", "▓", "█", "█", "█", "▓", "▓" },
				{ "▓", "█", "▓", "▓", "▓", "█", "▓" }, { "█", "▓", "▓", "▓", "▓", "▓", "█" } };

		if (dir.equals("west")) {
			mapDir = new String[][] { { "▓", "█", "█", "█", "█", "█", "▓" }, { "█", "▓", "█", "▓", "█", "▓", "█" },
					{ "█", "█", "▓", "█", "▓", "█", "█" }, { "∞", "∞", "∞", "\u2062", "█", "▓", "█" },
					{ "▓", "▓", "█", "█", "█", "▓", "▓" }, { "▓", "█", "▓", "▓", "▓", "█", "▓" },
					{ "█", "▓", "▓", "▓", "▓", "▓", "█" } };
		} else if (dir.equals("west northwest")) {
			mapDir = new String[][] { { "▓", "█", "█", "█", "█", "█", "▓" }, { "∞", "▓", "█", "▓", "█", "▓", "█" },
					{ "█", "∞", "∞", "█", "▓", "█", "█" }, { "▓", "█", "▓", "\u2062", "█", "▓", "█" },
					{ "▓", "▓", "█", "█", "█", "▓", "▓" }, { "▓", "█", "▓", "▓", "▓", "█", "▓" },
					{ "█", "▓", "▓", "▓", "▓", "▓", "█" } };
		} else if (dir.equals("northwest")) {
			mapDir = new String[][] { { "∞", "█", "█", "█", "█", "█", "▓" }, { "█", "∞", "█", "▓", "█", "▓", "█" },
					{ "█", "█", "∞", "█", "▓", "█", "█" }, { "▓", "█", "▓", "\u2062", "█", "▓", "█" },
					{ "▓", "▓", "█", "█", "█", "▓", "▓" }, { "▓", "█", "▓", "▓", "▓", "█", "▓" },
					{ "█", "▓", "▓", "▓", "▓", "▓", "█" } };
		} else if (dir.equals("north northwest")) {
			mapDir = new String[][] { { "▓", "∞", "█", "█", "█", "█", "▓" }, { "█", "▓", "∞", "▓", "█", "▓", "█" },
					{ "█", "█", "∞", "█", "▓", "█", "█" }, { "▓", "█", "▓", "\u2062", "█", "▓", "█" },
					{ "▓", "▓", "█", "█", "█", "▓", "▓" }, { "▓", "█", "▓", "▓", "▓", "█", "▓" },
					{ "█", "▓", "▓", "▓", "▓", "▓", "█" } };
		} else if (dir.equals("north")) {
			mapDir = new String[][] { { "▓", "█", "█", "∞", "█", "█", "▓" }, { "█", "▓", "█", "∞", "█", "▓", "█" },
					{ "█", "█", "▓", "∞", "▓", "█", "█" }, { "▓", "█", "▓", "\u2062", "█", "▓", "█" },
					{ "▓", "▓", "█", "█", "█", "▓", "▓" }, { "▓", "█", "▓", "▓", "▓", "█", "▓" },
					{ "█", "▓", "▓", "▓", "▓", "▓", "█" } };
		} else if (dir.equals("north northeast")) {
			mapDir = new String[][] { { "▓", "█", "█", "█", "█", "∞", "▓" }, { "█", "▓", "█", "▓", "∞", "▓", "█" },
					{ "█", "█", "▓", "█", "∞", "█", "█" }, { "▓", "█", "▓", "\u2062", "█", "▓", "█" },
					{ "▓", "▓", "█", "█", "█", "▓", "▓" }, { "▓", "█", "▓", "▓", "▓", "█", "▓" },
					{ "█", "▓", "▓", "▓", "▓", "▓", "█" } };
		} else if (dir.equals("northeast")) {
			mapDir = new String[][] { { "▓", "█", "█", "█", "█", "█", "∞" }, { "█", "▓", "█", "▓", "█", "∞", "█" },
					{ "█", "█", "▓", "█", "∞", "█", "█" }, { "▓", "█", "▓", "\u2062", "█", "▓", "█" },
					{ "▓", "▓", "█", "█", "█", "▓", "▓" }, { "▓", "█", "▓", "▓", "▓", "█", "▓" },
					{ "█", "▓", "▓", "▓", "▓", "▓", "█" } };
		} else if (dir.equals("east northeast")) {
			mapDir = new String[][] { { "▓", "█", "█", "█", "█", "█", "▓" }, { "█", "▓", "█", "▓", "█", "▓", "∞" },
					{ "█", "█", "▓", "█", "∞", "∞", "█" }, { "▓", "█", "▓", "\u2062", "█", "▓", "█" },
					{ "▓", "▓", "█", "█", "█", "▓", "▓" }, { "▓", "█", "▓", "▓", "▓", "█", "▓" },
					{ "█", "▓", "▓", "▓", "▓", "▓", "█" } };
		} else if (dir.equals("east")) {
			mapDir = new String[][] { { "▓", "█", "█", "█", "█", "█", "▓" }, { "█", "▓", "█", "▓", "█", "▓", "█" },
					{ "█", "█", "▓", "█", "▓", "█", "█" }, { "▓", "█", "▓", "\u2062", "∞", "∞", "∞" },
					{ "▓", "▓", "█", "█", "█", "▓", "▓" }, { "▓", "█", "▓", "▓", "▓", "█", "▓" },
					{ "█", "▓", "▓", "▓", "▓", "▓", "█" } };
		} else if (dir.equals("east southeast")) {
			mapDir = new String[][] { { "▓", "█", "█", "█", "█", "█", "▓" }, { "█", "▓", "█", "▓", "█", "▓", "█" },
					{ "█", "█", "▓", "█", "▓", "█", "█" }, { "▓", "█", "▓", "\u2062", "█", "▓", "█" },
					{ "▓", "▓", "█", "█", "∞", "∞", "▓" }, { "▓", "█", "▓", "▓", "▓", "█", "∞" },
					{ "█", "▓", "▓", "▓", "▓", "▓", "█" } };
		} else if (dir.equals("southeast")) {
			mapDir = new String[][] { { "▓", "█", "█", "█", "█", "█", "▓" }, { "█", "▓", "█", "▓", "█", "▓", "█" },
					{ "█", "█", "▓", "█", "▓", "█", "█" }, { "▓", "█", "▓", "\u2062", "█", "▓", "█" },
					{ "▓", "▓", "█", "█", "∞", "▓", "▓" }, { "▓", "█", "▓", "▓", "▓", "∞", "▓" },
					{ "█", "▓", "▓", "▓", "▓", "▓", "∞" } };
		} else if (dir.equals("south southeast")) {
			mapDir = new String[][] { { "▓", "█", "█", "█", "█", "█", "▓" }, { "█", "▓", "█", "▓", "█", "▓", "█" },
					{ "█", "█", "▓", "█", "▓", "█", "█" }, { "▓", "█", "▓", "\u2062", "█", "▓", "█" },
					{ "▓", "▓", "█", "█", "∞", "▓", "▓" }, { "▓", "█", "▓", "▓", "∞", "█", "▓" },
					{ "█", "▓", "▓", "▓", "▓", "∞", "█" } };
		} else if (dir.equals("south")) {
			mapDir = new String[][] { { "▓", "█", "█", "█", "█", "█", "▓" }, { "█", "▓", "█", "▓", "█", "▓", "█" },
					{ "█", "█", "▓", "█", "▓", "█", "█" }, { "▓", "█", "▓", "\u2062", "█", "▓", "█" },
					{ "▓", "▓", "█", "∞", "█", "▓", "▓" }, { "▓", "█", "▓", "∞", "▓", "█", "▓" },
					{ "█", "▓", "▓", "∞", "▓", "▓", "█" } };
		} else if (dir.equals("south southwest")) {
			mapDir = new String[][] { { "▓", "█", "█", "█", "█", "█", "▓" }, { "█", "▓", "█", "▓", "█", "▓", "█" },
					{ "█", "█", "▓", "█", "▓", "█", "█" }, { "▓", "█", "▓", "\u2062", "█", "▓", "█" },
					{ "▓", "▓", "∞", "█", "█", "▓", "▓" }, { "▓", "█", "∞", "▓", "▓", "█", "▓" },
					{ "█", "∞", "▓", "▓", "▓", "▓", "█" } };
		} else if (dir.equals("southwest")) {
			mapDir = new String[][] { { "▓", "█", "█", "█", "█", "█", "▓" }, { "█", "▓", "█", "▓", "█", "▓", "█" },
					{ "█", "█", "▓", "█", "▓", "█", "█" }, { "▓", "█", "▓", "\u2062", "█", "▓", "█" },
					{ "▓", "▓", "∞", "█", "█", "▓", "▓" }, { "▓", "∞", "▓", "▓", "▓", "█", "▓" },
					{ "∞", "▓", "▓", "▓", "▓", "▓", "█" } };
		} else if (dir.equals("west southwest")) {
			mapDir = new String[][] { { "▓", "█", "█", "█", "█", "█", "▓" }, { "█", "▓", "█", "▓", "█", "▓", "█" },
					{ "█", "█", "▓", "█", "▓", "█", "█" }, { "▓", "█", "▓", "\u2062", "█", "▓", "█" },
					{ "▓", "∞", "∞", "█", "█", "▓", "▓" }, { "∞", "█", "▓", "▓", "▓", "█", "▓" },
					{ "█", "▓", "▓", "▓", "▓", "▓", "█" } };
		}

		return mapDir;
	}

	public Player getMapViewer() {
		return mapViewer;
	}

	public void removeMap() {
		mapViewer.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		Bukkit.getServer().getScheduler().cancelTask(schedulerId);

	}

	private Scoreboard displayMap(final Player p) {
		final String[] mapData = buildMap(p);

		this.plugin.getServer().getScheduler().runTask(this.plugin, new Runnable() {

			@Override
			public void run() {
				ScoreboardManager manager = Bukkit.getScoreboardManager();
				final Scoreboard board = manager.getNewScoreboard();
				Team team = board.registerNewTeam("teamname");
				team.addPlayer(p);

				final String header = "Cubit Lands";

				final Objective objective = board.registerNewObjective("Land Map", "dummy");
				objective.setDisplaySlot(DisplaySlot.SIDEBAR);

				/**
				 * Locale string is retrieved from file for use in map header.
				 * Scoreboards do not cooperate with headers longer than 14
				 * characters, therefore it will be truncated if too long.
				 */

				if (header.length() <= 14) {
					objective.setDisplayName(ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "==" + ChatColor.RESET
							+ "" + ChatColor.GOLD + " " + header + " " + ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH
							+ "==");
				} else {
					objective.setDisplayName(ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "==" + ChatColor.RESET
							+ "" + ChatColor.GOLD + " " + header.substring(0, 14) + " " + ChatColor.YELLOW + ""
							+ ChatColor.STRIKETHROUGH + "==");
				}

				for (int i = 0; i < mapData.length; i++) {
					if (mapData[i].length() < 21) {
						for (int f = 0; f < (21 - mapData[i].length()); f++) {
							mapData[i] += ChatColor.RESET;
						}
					}

					OfflinePlayer ofp = new FakeOfflinePlayer(mapData[i].substring(5, 17));

					Score score = objective.getScore(ofp.getName());

					score.setScore(mapData.length - i);

					Team t = board.registerNewTeam(i + "");
					t.setPrefix(mapData[i].substring(0, 5));
					t.setSuffix(mapData[i].substring(17));
					t.addPlayer(ofp);
					t.setDisplayName(mapData[i]);
				}

				p.setScoreboard(board);
			}
		});

		return null;
	}

	public void updateMap() {
		currChunk = mapViewer.getLocation().getChunk();
		currDir = "";
	}

	private String[] buildMap(Player p) {

		final int radius = 3;

		String[][] mapBoard = getMapDir(getPlayerDirection(p));

		String[] mapRows = new String[mapBoard.length + 3];

		if (!currChunk.equals(mapViewer.getLocation().getChunk())) {
			updateMap();
		}
		for (int z = 0; z < mapBoard.length; z++) {
			String row = "";
			for (int x = 0; x < mapBoard[z].length; x++) {

				int xx = x - radius;
				int zz = z - radius;

				String currSpot = mapBoard[z][x];

				if (plugin.getRegionManager().isValidRegion(currChunk.getWorld(), currChunk.getX() + xx,
						currChunk.getZ() + zz)) {
					RegionData data = plugin.getRegionManager().praseRegionData(currChunk.getWorld(),
							currChunk.getX() + xx, currChunk.getZ() + zz);
					if (data.praseWGRegion() != null) {
						boolean unchanged = true;
						for (UUID id : data.getOwnersUUID())
							if (id.equals(p.getUniqueId())) {
								currSpot = ChatColor.GREEN + currSpot;
								unchanged = false;
							}
						for (UUID mem : data.getMembersUUID())
							if (mem.equals(p.getUniqueId())) {
								currSpot = ChatColor.YELLOW + currSpot;
								unchanged = false;
							}
						if (unchanged)
							currSpot = ChatColor.RED + currSpot;

					}
				} else {
					if (currSpot.equals("∞") || currSpot.equals("\u2062")) {
						currSpot = ChatColor.RESET + currSpot;
					} else {
						currSpot = ChatColor.GRAY + currSpot;
					}
				}
				row += currSpot;
			}
			mapRows[z] = row;

		}

		/**
		 * Locale strings are retrieved from file for use in map legend.
		 * Scoreboards do not cooperate with strings longer than 28 characters,
		 * therefore they will be truncated if too long.
		 */

		final String yours = "Yours";
		final String member = "Member";
		final String others = "Others";

		if (yours.length() <= 25) {
			mapRows[mapRows.length - 3] = ChatColor.GREEN + "█- " + yours;
		} else {
			mapRows[mapRows.length - 3] = ChatColor.GREEN + "█- " + yours.substring(0, 25);
		}

		if (member.length() <= 25) {
			mapRows[mapRows.length - 2] = ChatColor.YELLOW + "█- " + member;
		} else {
			mapRows[mapRows.length - 2] = ChatColor.YELLOW + "█- " + member.substring(0, 25);
		}

		if (others.length() <= 25) {
			mapRows[mapRows.length - 1] = ChatColor.RED + "█- " + others;
		} else {
			mapRows[mapRows.length - 1] = ChatColor.RED + "█- " + others.substring(0, 25);
		}

		return mapRows;
	}

}
