package me.a8kj.rspvphyblood.configuration;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import me.a8kj.rspvphyblood.entity.Configuration;
import me.a8kj.rspvphyblood.entity.ConfigurtionMeta;
import me.a8kj.rspvphyblood.entity.PlayerProfile;
import me.a8kj.rspvphyblood.enums.ConfigurationSaveType;
import me.a8kj.rspvphyblood.enums.PlayerDataType;

@ConfigurtionMeta(name = "data.yml", saveType = ConfigurationSaveType.AUTO)
public class PlayerDataConfiguration extends Configuration {

	public PlayerDataConfiguration(JavaPlugin javaPlugin, Logger logger) {
		super(javaPlugin, logger);
	}

	public void addDataToPlayer(PlayerDataType playerDataType, int amount, String playerName) {
		setDataToPlayer(playerDataType, getCertainDataToPlayer(playerName, playerDataType) + Math.abs(amount),
				playerName);
	}

	public void removeDataFromplayer(PlayerDataType playerDataType, int amount, String playerName) {
		setDataToPlayer(playerDataType, getCertainDataToPlayer(playerName, playerDataType) - Math.abs(amount),
				playerName);
	}

	public PlayerProfile getPlayerProfile(String playerName) {
		PlayerProfile playerProfile = new PlayerProfile(playerName);
		playerProfile.setCoins(getCertainDataToPlayer(playerName, PlayerDataType.COINS));
		playerProfile.setDeaths(getCertainDataToPlayer(playerName, PlayerDataType.DEATHS));
		playerProfile.setKills(getCertainDataToPlayer(playerName, PlayerDataType.KILLS));
		playerProfile.setStreaks(getCertainDataToPlayer(playerName, PlayerDataType.STREAKS));
		return playerProfile;
	}

	public boolean exist(String playerName) {
		return playersSection.contains(playerName);
	}

	public void newPlayer(String playerName) {
		for (PlayerDataType playerDataType : PlayerDataType.values()) {
			setDataToPlayer(playerDataType, 0, playerName);
		}
	}

	public void setDataToPlayer(PlayerDataType playerDataType, int amount, String playerName) {
		ConfigurationSection playerSection = playerSection(playerName);
		playerSection.set(playerDataType.name().toLowerCase(), amount);
		try {
			this.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCertainDataToPlayer(String playerName, PlayerDataType playerDataType) {
		return playerSection(playerName).getInt(playerDataType.name().toLowerCase());
	}

	private ConfigurationSection playerSection(String playerName) {

		if (playersSection.getConfigurationSection(playerName) != null) {
			return playersSection.getConfigurationSection(playerName);
		} else {
			return playersSection.createSection(playerName);
		}

	}

	private final ConfigurationSection playersSection = getFileConfiguration().getConfigurationSection("players");

}
