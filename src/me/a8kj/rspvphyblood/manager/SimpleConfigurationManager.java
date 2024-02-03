package me.a8kj.rspvphyblood.manager;

import lombok.Getter;
import lombok.NonNull;
import me.a8kj.rspvphyblood.entity.Configuration;
import me.a8kj.rspvphyblood.entity.ConfigurtionMeta;
import me.a8kj.rspvphyblood.exceptions.NotValidConfigurationMetaException;
import me.a8kj.rspvphyblood.plugin.HybloodRspvpPlugin;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

public final class SimpleConfigurationManager {

	// .. todo : add can load or register interface to manage it

	private SimpleConfigurationManager(@NonNull HybloodRspvpPlugin hybloodRspvpPlugin) {
		this.hybloodRspvpPlugin = hybloodRspvpPlugin;
	}

	public static void load(HybloodRspvpPlugin hybloodRspvpPlugin) {
		if (hybloodRspvpPlugin == null)
			return;
		instance = new SimpleConfigurationManager(hybloodRspvpPlugin);
	}

	public static @NonNull SimpleConfigurationManager getInstance() {
		if (instance == null)
			throw new IllegalStateException("SimpleConfigurationManager instance is not initialized correctly,"
					+ " please try calling the method SimpleConfigurationManager#load");

		return instance;
	}

	public @Nullable Configuration getConfiguration(@NonNull String configuration_name) {
		return configurations.get(configuration_name);
	}

	public void addConfiguration(String configuration_name, Configuration configuraion)
			throws NotValidConfigurationMetaException {
		ConfigurtionMeta configurtionMeta = configuraion.getConfigurtionMeta();

		if (configurtionMeta == null || configurtionMeta.saveType() == null || configurtionMeta.name() == null) {
			throw new NotValidConfigurationMetaException();
		}

		if (configurations.containsKey(configuration_name.toLowerCase()) || configurations.containsValue(configuraion))
			return;
		configurations.computeIfAbsent(configuration_name.toLowerCase(), f -> configuraion);
	}

	public void removeConfiguration(String configuration_name, Configuration configuraion) {
		configurations.remove(configuration_name.toLowerCase());
	}

	public void reload(Configuration configuraion) {
		configuraion.load();
	}

	public void reloadByName(String name) {
		this.getConfiguration(name).load();
	}

	public void reloadAll() {
		this.configurations.forEach((config_name, config) -> {
			config.load();
			HybloodRspvpPlugin.getLogger().info("[configurations] " + config_name + " reloaded successfully !");
		});
	}

	public void saveAll() {
		this.configurations.forEach((config_name, config) -> {
			try {
				config.save();
				HybloodRspvpPlugin.getLogger().info("[configurations] " + config_name + " saved successfully !");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public void save(Configuration configuration) {
		try {
			configuration.save();
			HybloodRspvpPlugin.getLogger()
					.info("[configurations] " + configuration.getConfigurtionMeta().name() + " saved successfully !");
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public void registerAll() {
		this.configurations.forEach((name, configuration) -> {
			try {
				configuration.create();
				HybloodRspvpPlugin.getLogger().info("[configurations] " + name + " registered successfully !");
			} catch (IOException e) {
				HybloodRspvpPlugin.getLogger().log(Level.SEVERE, e.getMessage());
			} catch (NotValidConfigurationMetaException e) {
				HybloodRspvpPlugin.getLogger().log(Level.SEVERE, e.getMessage());
			}
		});
	}

	private final @NonNull HybloodRspvpPlugin hybloodRspvpPlugin;
	private final @NonNull @Getter Map<String, Configuration> configurations = new ConcurrentHashMap<>();
	private static @Nullable SimpleConfigurationManager instance;

}
