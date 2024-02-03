package me.a8kj.rspvphyblood.command;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.a8kj.rspvphyblood.plugin.register.PluginRegistry;
import net.md_5.bungee.api.ChatColor;

public class TrashCommand extends PluginRegistry implements CommandExecutor {

	public TrashCommand(JavaPlugin javaPlugin, Logger logger) {
		super(javaPlugin, logger);
		this.addCommand("trash", this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, "no-console");
			this.getLogger().info("[commands] console falid to use trash command with 'NoConsole' reason");
			return false;
		}

		Player player = (Player) sender;
		player.openInventory(Bukkit.createInventory(null, 54, ChatColor.RED + "Drop your not important items here !"));
		sendMessage(player, "open-trash-menu");
		return true;

	}

}
