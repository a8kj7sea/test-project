package me.a8kj.rspvphyblood.entity;

import java.io.IOException;

import me.a8kj.rspvphyblood.exceptions.NotValidConfigurationMetaException;

public interface Configurable {
	public void create() throws IOException, NotValidConfigurationMetaException;

	public boolean delete();

	public boolean exists();

	public void load();

	public void save() throws IOException;
}
