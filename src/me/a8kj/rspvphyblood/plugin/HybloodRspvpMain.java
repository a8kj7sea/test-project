package me.a8kj.rspvphyblood.plugin;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.a8kj.rspvphyblood.exceptions.NotValidConfigurationMetaException;

public class HybloodRspvpMain extends JavaPlugin {

	private @Getter static HybloodRspvpPlugin hybloodRspvpPlugin;

	@Override
	public void onEnable() {
		hybloodRspvpPlugin = new HybloodRspvpPlugin(this, this.getLogger());
		try {
			hybloodRspvpPlugin.onEnable();
		} catch (NotValidConfigurationMetaException e) {
			e.printStackTrace();
		}
	}

}
