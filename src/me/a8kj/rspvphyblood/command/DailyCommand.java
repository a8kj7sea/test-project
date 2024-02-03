package me.a8kj.rspvphyblood.command;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.a8kj.rspvphyblood.configuration.PlayerDailyDataConfiguration;
import me.a8kj.rspvphyblood.plugin.HybloodRspvpPlugin;
import me.a8kj.rspvphyblood.plugin.register.PluginRegistry;

public class DailyCommand extends PluginRegistry implements CommandExecutor {

	public DailyCommand(JavaPlugin javaPlugin, Logger logger) {
		super(javaPlugin, logger);
		this.addCommand("daily", this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sendMessage(sender, "no-console");
			HybloodRspvpPlugin.getLogger().info("[commands] console falid to use daily command with 'NoConsole' reason");
			return false;
		}

		Player player = (Player) sender;

		PlayerDailyDataConfiguration playerDailyDataConfiguration = (PlayerDailyDataConfiguration) HybloodRspvpPlugin
				.getConfigurationManager().getConfiguration("daily");
		if (playerDailyDataConfiguration.exists(player)) {

			if (playerDailyDataConfiguration.canGetDaily(player)) {
				// give kit
				player.getInventory().addItem(new ItemStack(Material.APPLE));
				playerDailyDataConfiguration.reset(player);
				sendMessage(player, "daily-success");
			} else {
				sendText(player, getMessage("daily-cooldown").replace("%time%",
						playerDailyDataConfiguration.getRemainingTimeAsString(player)));
			}
		} else {
			// give kit
			player.getInventory().addItem(new ItemStack(Material.APPLE));
			playerDailyDataConfiguration.registerNewPlayer(player);
			sendMessage(player, "daily-success");
		}

		return false;
	}
}
