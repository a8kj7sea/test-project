package me.a8kj.rspvphyblood.command;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.Lists;

import lombok.NonNull;

import me.a8kj.rspvphyblood.configuration.MessagesConfiguration;
import me.a8kj.rspvphyblood.manager.SimpleConfigurationManager;
import me.a8kj.rspvphyblood.plugin.register.PluginRegistry;

public class ReloadConfigurationCommand extends PluginRegistry implements CommandExecutor, TabCompleter {

	public ReloadConfigurationCommand(JavaPlugin javaPlugin, Logger logger) {
		super(javaPlugin, logger);
		this.addCommand("rspvpreload", this);
		this.addCommandTab("rspvpreload", this);
	}

	private @NonNull final SimpleConfigurationManager configurationsManager = getConfigurationManager();
	private @NonNull final MessagesConfiguration messagesConfiguration = (MessagesConfiguration) this.configurationsManager
			.getConfiguration("messages");

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, "no-console");
			this.getLogger()
					.info("[commands] console falid to use reload configuration command with 'NoConsole' reason");
			return false;
		}

		Player player = (Player) sender;

		if (!player.hasPermission("hyblood.rspvp.commands.reload")) {
			sendMessage(player, "no-permission");
			this.getLogger().info("[commands] " + player.getName()
					+ " falid to use reload configuration command with 'NoPermission' reason");
			return false;
		}

		if (args.length != 1) {
			sendMessage(player, "reload-command-use");
			return false;
		}

		String type = args[0].toLowerCase();

		if (type == null) {
			sendMessage(player, "reload-command-use");
			return false;
		}

		Set<String> names = this.configurationsManager.getConfigurations().keySet();

		if (!type.equalsIgnoreCase("all") && !names.contains(type)) {
			sendMessage(player, "reload-command-use");
			return false;
		}

		this.messagesConfiguration.getSimplePlaceholders().doTwice("%type%", type);

		if (type.equalsIgnoreCase("all")) {
			this.configurationsManager.reloadAll();
			sendMessage(player, "reload-command-success");
		} else {
			this.configurationsManager.reloadByName(type);
			sendMessage(player, "reload-command-success");
		}

		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return Lists.newArrayList();
		}

		if (args.length != 1) {
			return Lists.newArrayList();
		}

		if (!sender.hasPermission("hyblood.rspvp.commands.reload")) {
			return Lists.newArrayList();
		}
		Set<String> names = this.configurationsManager.getConfigurations().keySet();
		List<String> suggestments = Lists.newArrayList(names.iterator());
		suggestments.add("all");
		return suggestments;

	}

}
