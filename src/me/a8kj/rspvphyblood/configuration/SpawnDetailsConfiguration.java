package me.a8kj.rspvphyblood.configuration;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.a8kj.rspvphyblood.entity.Configuration;
import me.a8kj.rspvphyblood.entity.ConfigurtionMeta;
import me.a8kj.rspvphyblood.enums.ConfigurationSaveType;

@ConfigurtionMeta(name = "spawns.yml", saveType = ConfigurationSaveType.AUTO)
public class SpawnDetailsConfiguration extends Configuration {

	public SpawnDetailsConfiguration(JavaPlugin javaPlugin, Logger logger) {
		super(javaPlugin, logger);
	}

	public void setSpawn(Location location) {
		FileConfiguration fileConfiguration = this.getFileConfiguration();
		fileConfiguration.set("spawn-location.world", (String) location.getWorld().getName());
		fileConfiguration.set("spawn-location.x", (double) location.getX());
		fileConfiguration.set("spawn-location.y", (double) location.getY());
		fileConfiguration.set("spawn-location.z", (double) location.getZ());
		fileConfiguration.set("spawn-location.pitch", (double) location.getPitch());
		fileConfiguration.set("spawn-location.yaw", (double) location.getYaw());
		try {
			this.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Location spawnLocation() {
		if (this.getFileConfiguration().getString("spawn-location") == null)
			return null;
		double x = this.getDouble("spawn-location.x");
		double y = this.getDouble("spawn-location.y");
		double z = this.getDouble("spawn-location.z");
		double pitch = this.getDouble("spawn-location.pitch");
		double yaw = this.getDouble("spawn-location.yaw");
		World world = Bukkit.getWorld(this.getString("spawn-location.world"));

		Location location = new Location(world, x, y, z);
		location.setPitch((float) pitch);
		location.setYaw((float) yaw);
		return location;

	}

}
