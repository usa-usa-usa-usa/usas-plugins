package com.npcanimationnotifier;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("npcAnimationNotifier")
public interface NPCAnimationNotifierConfig extends Config
{
    @ConfigItem(
        keyName = "npcAnimationDefinition",
        name = "NPC Animations",
        description = "Enter NPC ID, Animation ID, and Animation Name (you name it yourself)<br>Separated by a comma - ex. '1234,9999,The Griddy'<br> Add a new line for each new NPC"
    )
    default String npcAnimationDefinition()
    {
        return "";
    }
}
