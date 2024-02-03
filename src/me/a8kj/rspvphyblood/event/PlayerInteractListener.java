package me.a8kj.rspvphyblood.event;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.a8kj.rspvphyblood.event.custom.InteractWithSignEvent;

public class PlayerInteractListener implements Listener {

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {

		Player player = event.getPlayer();

		Block targetBlock = event.getClickedBlock();

		if (targetBlock == null || targetBlock.getType() == Material.AIR)
			return;

		if ((event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK)

				&& isSign(targetBlock)) {

			Sign sign = (Sign) targetBlock;
			new InteractWithSignEvent(player, sign).callEvent();

		}

	}

	private boolean isSign(Block targetBlock) {
		return targetBlock.getState() instanceof Sign;
	}

}
