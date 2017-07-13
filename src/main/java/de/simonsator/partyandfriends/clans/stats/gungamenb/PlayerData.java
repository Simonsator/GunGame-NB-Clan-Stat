package de.simonsator.partyandfriends.clans.stats.gungamenb;

/**
 * @author Simonsator
 * @version 1.0.0 30.11.16
 */
public class PlayerData {
	public final int DEATHS;
	public final int KILLS;

	public PlayerData(int kills, int deaths) {
		KILLS = kills;
		DEATHS = deaths;
	}
}
