package me.a8kj.rspvphyblood.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.a8kj.rspvphyblood.enums.PreparePlayerEventType;
import me.a8kj.rspvphyblood.event.custom.PreparePlayerEvent;

public class PreparePlayerListeners implements Listener {

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		
	}

	@EventHandler(ignoreCancelled = false, priority = EventPriority.MONITOR)
	public void onPreparePlayerEvent(PreparePlayerEvent event) {
		Player player = event.getPlayer();
		PreparePlayerEventType preparePlayerEventType = event.getPreparePlayerEventType();

		if (player == null) {
			return;
		}

		switch (preparePlayerEventType) {
		case JOIN:

			break;

		case QUIT:

			break;
		case RESPAWN:

			break;
		case LOGIN:

			break;

		}
	}

}
