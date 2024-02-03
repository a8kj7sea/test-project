package me.a8kj.rspvphyblood.entity;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.lang.Validate;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.a8kj.rspvphyblood.exceptions.NotValidConfigurationMetaException;
import me.a8kj.rspvphyblood.plugin.HybloodRspvpPlugin;

@Getter
public abstract class Configuration extends HybloodRspvpPlugin implements Configurable {

	private File file;
	private FileConfiguration fileConfiguration;

	private final ConfigurtionMeta configurtionMeta;

	public Configuration(JavaPlugin javaPlugin, Logger logger) {
		super(javaPlugin, logger);
		this.configurtionMeta = getClass().getDeclaredAnnotation(ConfigurtionMeta.class);
		Validate.notNull(configurtionMeta, "Container class must have ContainerMeta Annotation");
	}

	@Override
	public void create() throws IOException, NotValidConfigurationMetaException {

		if (configurtionMeta == null || configurtionMeta.saveType() == null || configurtionMeta.name() == null) {
			throw new NotValidConfigurationMetaException();
		}

		this.file = new File(this.getJavaPlugin().getDataFolder(), this.configurtionMeta.name());
		this.file.getParentFile().mkdirs();

		if (!exists())
			if (!this.configurtionMeta.saveType().getType()) {
				this.file.createNewFile();
			} else {
				this.getJavaPlugin().saveResource(this.configurtionMeta.name(), true);
			}

		load();
	}

	public String getString(String path) {
		return this.getFileConfiguration().getString(path);
	}

	public double getDouble(String path) {
		return this.getFileConfiguration().getDouble(path);
	}

	public long getLong(String path) {
		return this.getFileConfiguration().getLong(path);
	}

	public void set(String path, Object object) {
		this.fileConfiguration.set(path, object);
		try {
			this.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean exists() {
		return this.file.exists();
	}

	@Override
	public boolean delete() {
		return this.file.delete();
	}

	@Override
	public void load() {
		this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
	}

	@Override
	public void save() throws IOException {
		this.fileConfiguration.save(this.file);
	}

}
