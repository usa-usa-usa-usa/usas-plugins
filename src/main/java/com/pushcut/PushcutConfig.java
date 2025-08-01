package com.pushcut;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("pushcut")
public interface PushcutConfig extends Config {

	@ConfigSection(
			name = "Pushcut Settings",
			description = "Configuration for Pushcut notifications",
			position = 99
	)
	String pushcutSettings = "pushcutSettings";

	@ConfigItem(
			keyName = "webhookSecret",
			name = "Webhook Secret",
			description = "Your Pushcut webhook secret (obtained from the Pushcut app)",
			section = pushcutSettings
	)
	default String webhookSecret() {
		return "";
	}

	@ConfigItem(
			keyName = "notificationName",
			name = "Notification Name",
			description = "The name of the Pushcut notification to trigger",
			section = pushcutSettings
	)
	default String notificationName() {
		return "";
	}

	@ConfigSection(
			name = "Notification Customization",
			description = "Customize the title and message of Pushcut notifications",
			position = 100
	)
	String notificationCustomization = "notificationCustomization";

	@ConfigItem(
			keyName = "customTitle",
			name = "Custom Notification Title",
			description = "Override the default 'RuneLite' title for Pushcut notifications.",
			section = notificationCustomization
	)
	default String customTitle() {
		return "RuneLite"; // Default the title to "RuneLite"
	}

	@ConfigItem(
			keyName = "messageOverride",
			name = "Message Override",
			description = "Override the RuneLite notification message. Leave empty to use the original notification message.",
			section = notificationCustomization
	)
	default String messageOverride() {
		return "";
	}
}
