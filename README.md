# NPC Animation Notifier Plugin
The **NPC Animation Notifier Plugin** allows users to configure notifications for specific NPC animations in RuneLite. By specifying NPC IDs, animation IDs, and custom names, the plugin will alert you whenever the selected NPCs perform the defined animations.


## Configuring the Plugin

You need to configure the plugin by specifying the NPC ID, animation ID, and a custom name for each animation you want to track.

Within the plugin settings, enter NPC and animation details in the following format (each on a new line):
   - **NPC_ID**: The ID of the NPC you want to monitor.
   - **Animation_ID**: The ID of the specific animation performed by the NPC.
   - **Custom_Animation_Name**: A custom name for the animation (required). This name will be shown in notifications instead of the animation ID.

**Example**

    <NPC_ID>,<Animation_ID>,<Custom_Animation_Name>

    NOTE: All three fields (NPC ID, Animation ID, and Custom Animation Name) are required for each entry. 
    If any field is missing, the plugin will not function properly for that entry.

    Example:

    8615,9111,Hydra Switch 
    8619,9112,Hydra Switch 
    8620,9113,Hydra Switch


## Finding NPC and Animation IDs

To find NPC IDs and Animation IDs, I recommend using the **Identificator Plugin** from the Plugin Hub:

- [Identificator Plugin on RuneLite](https://runelite.net/plugin-hub/show/identificator)

This plugin helps you identify NPCs and animations by hovering over the NPCs in-game and displaying their IDs.

## Handling Input Errors

If you malform the input in the configuration, the plugin will **ignore** the invalid entries and continue to process correctly formatted ones. Here's how errors are handled:

- **Missing fields**: If any of the NPC ID, Animation ID, or Custom Animation Name is missing from an entry, that line will be ignored.
- **Invalid numbers**: If an NPC ID or Animation ID is not a valid integer, that entry will also be ignored.
- **Empty or incomplete lines**: These will be skipped by the plugin without affecting other valid entries.

Make sure that each entry follows the correct format to avoid missing notifications.

## Frequently Asked Questions (FAQ)

### Q: What happens if I leave out the NPC ID, Animation ID, or Custom Animation Name?
A: The plugin will ignore any entries that do not contain all three fields. You must enter all three values for each line.

### Q: How do I find the NPC and Animation IDs?
A: You can use the **Identificator Plugin** to easily find NPC and Animation IDs by hovering over NPCs in-game.

### Q: Can I track multiple animations for a single NPC?
A: Yes! You can add multiple lines with the same NPC ID and different Animation IDs if you want to track several animations for the same NPC.

