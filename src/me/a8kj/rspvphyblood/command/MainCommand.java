package me.a8kj.rspvphyblood.command;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.a8kj.rspvphyblood.plugin.register.PluginRegistry;

public class MainCommand extends PluginRegistry implements CommandExecutor, TabCompleter {

	public MainCommand(JavaPlugin javaPlugin, Logger logger) {
		super(javaPlugin, logger);
		this.addCommand("rspvp", this);
		this.addCommandTab("rspvp", this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, "no-console");
			this.getLogger().info("[commands] console falid to use rspvp command with 'NoConsole' reason");
			return false;
		}

		Player player = (Player) sender;
		if (!player.hasPermission("hyblood.rspvp.commands.rspvp")) {
			sendMessage(player, "no-permission");
			this.getLogger()
					.info("[commands] " + player.getName() + " falid to use rspvp command with 'NoPermission' reason");
			return false;
		}

		if (args.length != 1) {
			
		}

		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return null;
	}

}
