package me.a8kj.rspvphyblood.entity;

import lombok.Data;

@Data
public class PlayerProfile {

	private final String playerName;
	private int kills, deaths, streaks, coins;
	
}
