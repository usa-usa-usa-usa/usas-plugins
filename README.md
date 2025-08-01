# Pushcut Notifications for RuneLite

## Overview

# Pushcut Notifications for RuneLite

> ⚠️ **Note:** Pushcut is currently only available on iOS devices. This plugin will not work with Android unless Pushcut adds support in the future.

This RuneLite plugin allows you to forward in-game notifications to your iOS devices using [Pushcut](https://www.pushcut.io). Pushcut goes beyond simple alerts by enabling **actionable iOS notifications** that can trigger Shortcuts — streamlining your ability to respond to RuneLite events while away from your desk.

This is particularly useful for players who use **remote desktop apps** to manage their gameplay from a distance. Whether you're monitoring idle status, tracking logout timers, or just staying aware of chat messages, this plugin enhances your ability to stay connected to your session in a meaningful and timely way.:

## Why Pushcut?

Compared to traditional notification plugins, Pushcut offers:

- ✨ **Shortcut integration**: Launch remote desktop apps, open widgets, or run custom Shortcuts directly from the notification.
- 🔁 **Fast response flow**: Immediately act on a notification without fumbling through app drawers.
- 🔔 **Reliable delivery**: Receive critical game notifications even when you're multitasking or the RuneLite client is minimized on your host machine.

---

## Example Use Case

Let’s say you're using **Chrome Remote Desktop** to check in on your RuneLite session while away from home.

You might configure a chat notification like:

> `"The gemstone crab burrows away"`

When that event is detected, this plugin sends a notification to your iPhone via Pushcut. You can customize the Pushcut notification to include an **action button** that launches your remote desktop app instantly — no need to manually switch apps or search for it.

This improves your ability to quickly respond to in-game events — especially in edge cases where the OSRS mobile client can’t run reliably in the background (e.g., iOS memory management).

---

## Features

- 📲 **Send RuneLite notifications** to your iOS device via Pushcut.
- 🔧 **Configure webhook settings** to match your Pushcut setup.
- ✏️ **Customize notification title and message** for clarity and relevance.
- ⚡️ **Trigger iOS Shortcuts** right from the notification (e.g., open Remote Desktop).

---

## Setup

### 1. Create a Pushcut Notification

- Open the **Pushcut app** on your iOS device.
- Create a new notification and (optionally) assign a **Shortcut trigger** (e.g., "Open Chrome Remote Desktop").
- Copy the **Webhook URL and secret**.

### 2. Configure the Plugin in RuneLite

- **Webhook Secret**: Paste your Pushcut secret.
- **Notification Name**: Enter the name of the Pushcut notification to trigger.
- *(Optional)* Set:
    - **Notification Title**
    - **Message Override**

---

## Troubleshooting

- **Notifications not appearing?**
    - Double-check that your `webhookSecret` and `notificationName` are correctly entered (they are case-sensitive).
---

## Contributing

Have feedback or want to improve the plugin? Contributions and bug reports are welcome! Please submit an issue or PR on the [GitHub repository](#).

---

