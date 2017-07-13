package de.simonsator.partyandfriends.clans.stats.gungamenb;

import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.clan.api.Clan;
import de.simonsator.partyandfriends.clan.api.ClanStat;
import de.simonsator.partyandfriends.clan.commands.ClanCommands;
import de.simonsator.partyandfriends.clan.commands.subcommands.Stats;
import de.simonsator.partyandfriends.utilities.Language;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Simonsator
 * @version 1.0.0 20.01.17
 */
public class GGNBMain extends Plugin implements ClanStat {
	private Configuration config;
	private GGNBConnection connection;
	private Configuration messagesConfig;

	public void onEnable() {
		try {
			config = (new GGNBConfig(new File(getDataFolder(), "config.yml"))).getCreatedConfiguration();
			messagesConfig = (new GGNBMessages(Language.OWN, new File(getDataFolder(), "messages.yml"))).getCreatedConfiguration();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connection = new GGNBConnection(config.getString("database.db"), "jdbc:mysql://" + config.getString("database.host") + ":" + config.getInt("database.port"), config.getString("database.user"), config.getString("database.password"));
		((Stats) ClanCommands.getInstance().getSubCommand(Stats.class)).registerClanStats(this, this);
	}

	public void stats(OnlinePAFPlayer pSender, Clan pClan) {
		List<PAFPlayer> players = pClan.getAllPlayers();
		List<PlayerData> playerData = new ArrayList<PlayerData>();
		for (PAFPlayer player : players) {
			PlayerData data = connection.getPlayerData(player.getUniqueId());
			if (data != null)
				playerData.add(data);
		}
		int deaths = 0;
		int kills = 0;
		for (PlayerData data : playerData) {
			deaths += data.DEATHS;
			kills += data.KILLS;
		}
		pSender.sendMessage(messagesConfig.getString("ClanStats.Kills").replace("[KILLS]", kills + ""));
		pSender.sendMessage(messagesConfig.getString("ClanStats.Deaths").replace("[DEATHS]", deaths + ""));
	}

	public String getName() {
		return messagesConfig.getString("ClanStats.Name");
	}
}
