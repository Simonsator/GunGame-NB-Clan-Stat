package de.simonsator.partyandfriends.clans.stats.gungamenb;

import de.simonsator.partyandfriends.utilities.Language;
import de.simonsator.partyandfriends.utilities.LanguageConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * @author Simonsator
 * @version 1.0.0 30.11.16
 */
public class GGNBMessages extends LanguageConfiguration {

	public GGNBMessages(Language pLanguage, File pFile) throws IOException {
		super(pLanguage, pFile);
		readFile();
		loadDefaultValues();
		saveFile();
		process(configuration);
	}

	private void loadDefaultValues() {
		set("ClanStats.Name", "GunGame");
		set("ClanStats.Kills", "&7The players in the clan killed &a[KILLS]&7 players.");
		set("ClanStats.Deaths", "&7The players in the clan died &c[DEATHS] &7times.");
	}

	public void reloadConfiguration() throws IOException {
		configuration = (new GGNBMessages(Language.OWN, FILE)).getCreatedConfiguration();
	}
}
