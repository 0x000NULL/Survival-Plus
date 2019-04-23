package com.fattymieo.survival.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.fattymieo.survival.Survival;

public class LocalChat implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent event) {
		if (event.isCancelled()) return;
		Player player = event.getPlayer();
		String msg = event.getMessage();

		if (Survival.settings.getBoolean("LegendaryItems.GoldArmorBuff")) {
			if (player.getInventory().getHelmet() != null) {
				if (player.getInventory().getHelmet().getType() == Material.GOLDEN_HELMET) {
					event.setCancelled(false);
					event.setFormat(ChatColor.GOLD + "<%1$s> " + ChatColor.YELLOW + "%2$s");
					return;
				}
			}
		}

		int channel = Survival.board.getObjective("Chat").getScore(player.getName()).getScore();
		if (channel > 0) {
			event.setFormat(ChatColor.GREEN + "<%1$s> " + ChatColor.RESET + "%2$s");
			return;
		}

		event.setCancelled(true);

		Bukkit.getConsoleSender().sendMessage("<" + player.getDisplayName() + "> " + msg);
		for (Player other : Bukkit.getServer().getOnlinePlayers()) {
			if (other.getLocation().getWorld() == player.getLocation().getWorld()) {
				double maxDist = 64;
				if (other.getLocation().distance(player.getLocation()) <= maxDist) {
					other.sendMessage(ChatColor.RESET + "<" + player.getDisplayName() + "> " + msg);
				}
			}
		}
	}

}