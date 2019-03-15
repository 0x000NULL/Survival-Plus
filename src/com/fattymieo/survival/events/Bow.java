package com.fattymieo.survival.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

import com.fattymieo.survival.Survival;

public class Bow implements Listener {

	@EventHandler
	public void onShootWithoutArrows(EntityShootBowEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (player.getInventory().getItemInMainHand().getType() == event.getBow().getType()) {
				if (player.getInventory().getItemInOffHand().getType() == Material.ARROW
						|| player.getInventory().getItemInOffHand().getType() == Material.SPECTRAL_ARROW
						|| player.getInventory().getItemInOffHand().getType() == Material.TIPPED_ARROW) {
					event.setCancelled(false);
				} else {
					event.setCancelled(true);
					player.sendMessage(ChatColor.RED + Survival.Words.get("Must load Arrows on off hand"));
					player.updateInventory();
				}
			} else {
				event.setCancelled(true);
				player.sendMessage(ChatColor.RED + Survival.Words.get("Must use a Bow with main hand"));
				player.updateInventory();
			}
		}
	}

}
