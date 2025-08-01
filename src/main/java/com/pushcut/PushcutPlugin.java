package com.pushcut;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.events.NotificationFired;
import okhttp3.*;

import java.io.IOException;

@PluginDescriptor(
		name = "Pushcut",
		description = "Sends RuneLite notifications to Pushcut",
		tags = {"notifications", "pushcut", "webhook", "notify"}
)
@Slf4j
public class PushcutPlugin extends Plugin {

	@Inject
	private PushcutConfig config;

	@Inject
	private OkHttpClient okHttpClient;

	@Override
	protected void startUp() throws Exception {
		log.info("Pushcut started!");
	}

	@Override
	protected void shutDown() throws Exception {
		log.info("Pushcut stopped!");
	}

	@Subscribe
	public void onNotificationFired(NotificationFired event) {
		String webhookSecret = config.webhookSecret();
		String notificationName = config.notificationName();

		if (webhookSecret.isEmpty() || notificationName.isEmpty()) {
			log.debug("Pushcut webhook secret or notification name not configured.");
			return;
		}

		String url = String.format("https://api.pushcut.io/%s/notifications/%s", webhookSecret, notificationName);

		// The title is now directly taken from the config, which defaults to "RuneLite"
		String title = config.customTitle();

		// Determine the message
		String message = config.messageOverride().isEmpty() ? event.getMessage() : config.messageOverride();

		// Construct the JSON body for Pushcut
		String json = String.format("{\"title\": \"%s\", \"text\": \"%s\"}", title, message);

		RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();

		okHttpClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				log.error("Failed to send Pushcut notification: " + e.getMessage());
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (!response.isSuccessful()) {
					log.error("Pushcut notification failed: " + response.message());
				} else {
					log.debug("Pushcut notification sent successfully.");
				}
				response.close();
			}
		});
	}

	@Provides
	PushcutConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(PushcutConfig.class);
	}
}