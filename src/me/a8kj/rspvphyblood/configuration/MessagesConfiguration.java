package me.a8kj.rspvphyblood.configuration;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.a8kj.rspvphyblood.entity.Configuration;
import me.a8kj.rspvphyblood.entity.ConfigurtionMeta;
import me.a8kj.rspvphyblood.enums.ConfigurationSaveType;
import me.a8kj.rspvphyblood.helper.SimplePlaceholders;

@ConfigurtionMeta(name = "messages.yml", saveType = ConfigurationSaveType.AUTO)
public class MessagesConfiguration extends Configuration {

	public MessagesConfiguration(JavaPlugin javaPlugin, Logger logger) {
		super(javaPlugin, logger);

		simplePlaceholders.addPlaceholder("%prefix%", this.getFileConfiguration().getString("prefix"));
	}

	public String getMessageByName(String label) {

		if (!getFileConfiguration().contains("messages." + label)) {
			return null;
		}

		return simplePlaceholders.text(getFileConfiguration().getString("messages." + label)).getTextWithPlaceholder();
	}

	private @Getter final SimplePlaceholders simplePlaceholders = new SimplePlaceholders();
}
