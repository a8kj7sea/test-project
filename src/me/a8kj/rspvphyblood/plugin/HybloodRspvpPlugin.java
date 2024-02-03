package me.a8kj.rspvphyblood.plugin;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Data;
import lombok.Getter;
import me.a8kj.rspvphyblood.command.TrashCommand;
import me.a8kj.rspvphyblood.configuration.MessagesConfiguration;
import me.a8kj.rspvphyblood.configuration.PlayerDailyDataConfiguration;
import me.a8kj.rspvphyblood.configuration.PlayerDataConfiguration;
import me.a8kj.rspvphyblood.configuration.SpawnDetailsConfiguration;
import me.a8kj.rspvphyblood.exceptions.NotValidConfigurationMetaException;
import me.a8kj.rspvphyblood.manager.SimpleConfigurationManager;
import me.a8kj.rspvphyblood.plugin.register.PluginRegistry;

@Data
public class HybloodRspvpPlugin {

	private final JavaPlugin javaPlugin;
	private @Getter static Logger logger;

	private final PluginRegistry pluginRegistry;

	public HybloodRspvpPlugin(final JavaPlugin javaPlugin, Logger logger) {
		this.javaPlugin = javaPlugin;
		HybloodRspvpPlugin.logger = logger;
		pluginRegistry = new TrashCommand(javaPlugin, logger);
	}

	private static @Getter SimpleConfigurationManager configurationManager;

	public void onEnable() throws NotValidConfigurationMetaException {
		SimpleConfigurationManager.load(this);
		configurationManager = SimpleConfigurationManager.getInstance();
		configurationManager.addConfiguration("messages", new MessagesConfiguration(javaPlugin, logger));
		configurationManager.addConfiguration("playerdata", new PlayerDataConfiguration(javaPlugin, logger));
		configurationManager.addConfiguration("spawn", new SpawnDetailsConfiguration(javaPlugin, logger));
		configurationManager.addConfiguration("daily", new PlayerDailyDataConfiguration(javaPlugin, logger));
		configurationManager.registerAll();
		pluginRegistry.start();
	}

	public void onDisable() {
		destory();
	}

	private void destory() {
		System.gc();
	}

	// utils

	public static <T> void sendMessage(T source, String message_name) {
		MessagesConfiguration messagesConfiguration = (MessagesConfiguration) getConfigurationManager()
				.getConfiguration("messages");

		if (source instanceof Player) {
			Player player = (Player) source;
			player.sendMessage(colorize(messagesConfiguration.getMessageByName(message_name)));
			return;
		} else if (source instanceof CommandSender) {
			CommandSender sender = (CommandSender) source;
			sender.sendMessage(colorize(messagesConfiguration.getMessageByName(message_name)));
			return;
		}
	}

	public static <T> void sendText(T source, String text) {
		if (source instanceof Player) {
			Player player = (Player) source;
			player.sendMessage(colorize(text));
			return;
		} else if (source instanceof CommandSender) {
			CommandSender sender = (CommandSender) source;
			sender.sendMessage(colorize(text));
			return;
		}
	}

	public static String getMessage(String message_name) {
		MessagesConfiguration messagesConfiguration = (MessagesConfiguration) getConfigurationManager()
				.getConfiguration("messages");
		return colorize(messagesConfiguration.getMessageByName(message_name));
	}

	public static void shutdown(JavaPlugin javaPlugin) {
		Bukkit.getServer().getPluginManager().disablePlugin(javaPlugin);
	}

	public static boolean found(String plugin_name) {
		return Bukkit.getServer().getPluginManager().getPlugin(plugin_name) != null;
	}

	public static String colorize(String textToTranslate) {
		return ChatColor.translateAlternateColorCodes('&', textToTranslate);
	}
}
