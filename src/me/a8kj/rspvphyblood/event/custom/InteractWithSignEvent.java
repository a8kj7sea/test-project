package me.a8kj.rspvphyblood.event.custom;

import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InteractWithSignEvent extends Event implements Cancellable {

	@Override
	public HandlerList getHandlers() {
		return handlerList;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean eventState) {
		cancelled = eventState;
	}

	public void callEvent() {
		Bukkit.getServer().getPluginManager().callEvent(this);
	}

	private final Player player;
	private final Sign sign;
	private boolean cancelled;

	private static final HandlerList handlerList = new HandlerList();
}
