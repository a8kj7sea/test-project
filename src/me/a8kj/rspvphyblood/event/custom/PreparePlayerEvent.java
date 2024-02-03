package me.a8kj.rspvphyblood.event.custom;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import me.a8kj.rspvphyblood.enums.PreparePlayerEventType;

@Getter
public class PreparePlayerEvent extends Event implements Cancellable {

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

	public PreparePlayerEvent(Player player, PreparePlayerEventType preparePlayerEventType) {
		this.player = player;
		this.preparePlayerEventType = preparePlayerEventType;
	}

	public void callEvent() {
		Bukkit.getServer().getPluginManager().callEvent(this);
	}

	private boolean cancelled;
	private Player player;
	private PreparePlayerEventType preparePlayerEventType;

	private static final HandlerList handlerList = new HandlerList();

}
