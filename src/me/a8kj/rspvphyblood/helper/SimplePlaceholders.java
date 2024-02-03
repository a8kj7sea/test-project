package me.a8kj.rspvphyblood.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.Validate;

import lombok.NonNull;

public class SimplePlaceholders {

	public SimplePlaceholders(@NonNull String text) {
		this.text = text;
	}

	public SimplePlaceholders() {
	}

	public SimplePlaceholders text(@NonNull String text) {
		this.text = text;
		return this;
	}

	private void handleReplace() {

		if (text == null || text.length() < 1) {
			Validate.notNull(text, "Text cannot be null in this state !");
			return;
		}

		if (SimplePlaceholders.placeholdersMap.isEmpty()) {
			throw new IllegalStateException("PlaceholdersMap cannot be empty X_X , you must add elements to it");
		}

		for (Entry<String, String> placeholderEntry : placeholdersMap.entrySet()) {
			String placeholder = placeholderEntry.getKey();
			String value = placeholderEntry.getValue();
			text = text.replace(placeholder, value);
		}
	}

	public void addPlaceholder(String placeholder, String value) {
		SimplePlaceholders.placeholdersMap.putIfAbsent(placeholder, value);
	}

	public void doTwice(String placeholder, String value) {
		if (SimplePlaceholders.placeholdersMap.containsKey(placeholder)) {
			SimplePlaceholders.placeholdersMap.replace(placeholder, value);
		} else {
			SimplePlaceholders.placeholdersMap.put(placeholder, value);
		}
	}

	public void removePlaceholder(String placeholder) {
		SimplePlaceholders.placeholdersMap.remove(placeholder);
	}

	public SimplePlaceholders newPlaceholdersMap(Map<String, String> newPlaceholdersMap) {
		SimplePlaceholders.placeholdersMap = newPlaceholdersMap;
		return this;
	}

	public String getTextWithPlaceholder() {
		handleReplace();
		return text;
	}

	private @NonNull String text;
	private static Map<String, String> placeholdersMap = new HashMap<String, String>();

}
