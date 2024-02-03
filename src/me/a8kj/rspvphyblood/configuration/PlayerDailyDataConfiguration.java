package me.a8kj.rspvphyblood.configuration;

import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.a8kj.rspvphyblood.entity.Configuration;
import me.a8kj.rspvphyblood.entity.ConfigurtionMeta;
import me.a8kj.rspvphyblood.enums.ConfigurationSaveType;

@ConfigurtionMeta(name = "daily_data.yml", saveType = ConfigurationSaveType.AUTO)
public class PlayerDailyDataConfiguration extends Configuration {

	public PlayerDailyDataConfiguration(JavaPlugin javaPlugin, Logger logger) {
		super(javaPlugin, logger);
	}

	public void registerNewPlayer(Player player) {
		if (exists(player))
			return;
		this.set("players." + player.getName() + ".time", Long.valueOf(System.currentTimeMillis() / 1000L));
	}

	public boolean exists(Player player) {
		return this.getString("players." + player.getName() + ".time") != null;
	}

	public long getPlayerTime(Player player) {
		return this.getLong("players." + player.getName() + ".time");
	}

	public boolean canGetDaily(Player player) {
		long playerTime = getPlayerTime(player);
		long timeSinceLastKit = System.currentTimeMillis() / 1000L - playerTime;
		long remainingTime = 43200 - timeSinceLastKit;
		return !(remainingTime <= 0L);
	}

	public void reset(Player player) {
		registerNewPlayer(player);
	}

	public String getRemainingTimeAsString(Player player) {
		StringBuilder stringBuilder = new StringBuilder();
		long playerTime = getPlayerTime(player);
		long timeSinceLastKit = System.currentTimeMillis() / 1000L - playerTime;
		long remainingTime = 43200 - timeSinceLastKit;
		if (!(remainingTime <= 0L)) {
			int hours = (int) (remainingTime / 3600L);
			int minutes = (int) (remainingTime % 3600L / 60L);
			int seconds = (int) (remainingTime % 60L);
			if (hours > 0) {
				stringBuilder.append(String.format("%02d:%02d:%02d", Integer.valueOf(hours), Integer.valueOf(minutes),
						Integer.valueOf(seconds)));
			} else {
				stringBuilder.append(String.format("%02d:%02d", Integer.valueOf(minutes), Integer.valueOf(seconds)));
			}
		} else {
			stringBuilder = null;
			return null;
		}

		return stringBuilder.toString();
	}
	
}
