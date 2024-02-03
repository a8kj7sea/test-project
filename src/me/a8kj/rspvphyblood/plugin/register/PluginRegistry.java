package me.a8kj.rspvphyblood.plugin.register;

import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import lombok.Getter;
import me.a8kj.rspvphyblood.plugin.HybloodRspvpPlugin;

@Getter
public abstract class PluginRegistry extends HybloodRspvpPlugin {

	public PluginRegistry(JavaPlugin javaPlugin, Logger logger) {
		super(javaPlugin, logger);
	}

	// todo : make a command template to handle them easily with register and tab
	// completer
	public void start() {
		if (!commands.isEmpty()) {
			HybloodRspvpPlugin.getLogger().info("Commands register process started..");
			registerCommands();
		}

		if (!listeners.isEmpty()) {
			HybloodRspvpPlugin.getLogger().info("Listeners register process started..");
			registerListeners();
		}
	}

	private void registerCommands() {
		PluginRegistry.commands.forEach((name, executor) -> {
			this.getJavaPlugin().getCommand(name).setExecutor(executor);
			HybloodRspvpPlugin.getLogger().info("[commands] " + name + " registerd successfully !");
		});
		PluginRegistry.commandsWithTabCompleter.forEach((name, completer) -> {
			this.getJavaPlugin().getCommand(name).setTabCompleter(completer);
			HybloodRspvpPlugin.getLogger().info("[commands] " + name + "with tab completer registerd successfully !");
		});
	}

	private void registerListeners() {
		PluginRegistry.listeners.forEach(listener -> {
			getJavaPlugin().getServer().getPluginManager().registerEvents(listener, getJavaPlugin());
			HybloodRspvpPlugin.getLogger()
					.info("[Listeners] " + listener.getClass().getSimpleName() + " registerd successfully !");
		});
	}

	public void addCommand(String command_name, CommandExecutor command) {
		if (commands.containsKey(command_name) || commands.containsValue(command))
			return;
		commands.put(command_name, command);
	}

	public void removeCommand(String command_name, CommandExecutor command) {
		if (!commands.containsKey(command_name) || !commands.containsValue(command))
			return;
		commands.remove(command_name);
	}

	public void addCommandTab(String command_name, TabCompleter command) {
		if (commandsWithTabCompleter.containsKey(command_name) || commandsWithTabCompleter.containsValue(command))
			return;
		commandsWithTabCompleter.put(command_name, command);
	}

	public void removeCommandTab(String command_name, TabCompleter command) {
		if (!commandsWithTabCompleter.containsKey(command_name) || !commandsWithTabCompleter.containsValue(command))
			return;
		commandsWithTabCompleter.remove(command_name);
	}

	public void addListener(Listener listener) {
		if (listeners.contains(listener))
			return;
		listeners.add(listener);
	}

	public void removeListener(Listener listener) {
		if (listeners.contains(listener))
			return;
		listeners.add(listener);
	}

	private static final Set<Listener> listeners = Sets.newHashSet();
	private static final Map<String, CommandExecutor> commands = Maps.newHashMap();
	private static final Map<String, TabCompleter> commandsWithTabCompleter = Maps.newHashMap();

}
