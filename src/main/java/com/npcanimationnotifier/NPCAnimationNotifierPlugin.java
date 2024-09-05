package com.npcanimationnotifier;

import javax.inject.Inject;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.Notifier;

import java.util.*;
import java.util.stream.StreamSupport;

@Slf4j
@PluginDescriptor(
	name = "NPC Animation Notifier"
)
public class NPCAnimationNotifierPlugin extends Plugin
{
	private List<AnimationData> npcAnimationList;
	private int currentAnimation;
	private int previousAnimation;

	@Inject
	private Client client;

	@Inject
	private Notifier notifier;

	@Inject
	private NPCAnimationNotifierConfig config;


	@Override
	protected void startUp() throws Exception
	{
		log.info("Loading animation map");
		loadNpcAnimationMap();
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Clearing animation map");
		npcAnimationList.clear();
	}

	@Provides
	NPCAnimationNotifierConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(NPCAnimationNotifierConfig.class);
	}
	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (event.getKey().equals("npcAnimationDefinition"))
		{
			log.info("Animation definition changed");
			loadNpcAnimationMap();
		}
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		WorldView worldView = client.getTopLevelWorldView();

		StreamSupport.stream(worldView.npcs().spliterator(), false)
				.filter(npc -> npc != null)
				.forEach(npc -> {
					npcAnimationList.stream()
							.filter(data -> data.getNpcId() == npc.getId())
							.forEach(data -> handleNpc(npc, data));
				});
	}

	private void handleNpc(NPC npc, AnimationData animationData)
	{
		previousAnimation = currentAnimation;
		currentAnimation = npc.getAnimation();

		if (currentAnimation == animationData.getAnimationId() && currentAnimation != previousAnimation)
		{
			String logMessage = String.format("%s[%d] is performing animation %s[%s]!", npc.getName(), npc.getId(), animationData.getAnimationName(), animationData.getAnimationId());
			String notifyMessage = String.format("%s is performing %s!", npc.getName(), animationData.getAnimationName());

			log.info(logMessage);
			notifier.notify(notifyMessage);
		}
	}

	private void loadNpcAnimationMap()
	{
		String configInput = config.npcAnimationDefinition();
		npcAnimationList = parseNpcAnimationPairs(configInput);
	}

	private List<AnimationData> parseNpcAnimationPairs(String configInput)
	{
		List<AnimationData> npcAnimationList = new ArrayList<>();

		String[] rows = configInput.split("\n");

		for (String row : rows)
		{
			String[] parts = row.split(",");
			if (parts.length >= 3)
			{
				try
				{
					int npcId = Integer.parseInt(parts[0].trim());
					int animationId = Integer.parseInt(parts[1].trim());
					String animationName = parts[2].trim();

					npcAnimationList.add(new AnimationData(npcId, animationId, animationName));
				}
				catch (NumberFormatException e)
				{
					log.error("Invalid NPC or Animation ID format: {}", row, e);
				}
			}
		}

		return npcAnimationList;
	}
}
