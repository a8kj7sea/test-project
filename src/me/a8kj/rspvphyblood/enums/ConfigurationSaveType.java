package me.a8kj.rspvphyblood.enums;

import lombok.Getter;

@Getter
public enum ConfigurationSaveType {

	AUTO(true), MANUAL(false);

	private Boolean type;

	private ConfigurationSaveType(boolean type) {
		this.type = type;
	}
}
