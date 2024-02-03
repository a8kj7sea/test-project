package me.a8kj.rspvphyblood.event;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.a8kj.rspvphyblood.event.custom.InteractWithSignEvent;
import me.a8kj.rspvphyblood.plugin.register.PluginRegistry;
import net.md_5.bungee.api.ChatColor;

public class InteractWithSignListener extends PluginRegistry implements Listener {

	public InteractWithSignListener(JavaPlugin javaPlugin, Logger logger) {
		super(javaPlugin, logger);
		this.addListener(this);
	}

	@EventHandler(ignoreCancelled = false)
	public void onInteractWithSignEvent(InteractWithSignEvent event) {
		if (event.getSign() == null || event.getPlayer() == null || event.isCancelled())
			return;

		Player player = event.getPlayer();
		Sign signBlock = event.getSign();

		if (player.getGameMode() == GameMode.CREATIVE) {
			event.setCancelled(true);
			return;
		} else {
			List<String> lines = Arrays.asList(signBlock.getLines());
			if (!lines.contains("[trash]")) {
				return;
			}

			player.openInventory(
					Bukkit.createInventory(null, 54, ChatColor.RED + "Drop your not important items here !"));
			sendMessage(player, "open-trash-menu");

		}

	}

}
