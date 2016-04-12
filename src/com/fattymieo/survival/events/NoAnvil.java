package com.fattymieo.survival.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import com.fattymieo.survival.Survival;

public class NoAnvil implements Listener
{
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e)
	{
		Inventory inv = e.getInventory();
			 
		if(inv instanceof AnvilInventory)
		{
			AnvilInventory anvil = (AnvilInventory)inv;
			InventoryView view = e.getView();
			int rawSlot = e.getRawSlot();
			 
			// compare raw slot to the inventory view to make sure we are in the upper inventory
			if(rawSlot == view.convertSlot(rawSlot))
			{
				// 2 = result slot
				if(rawSlot == 2)
				{
					// item in the left slot
					ItemStack item = anvil.getContents()[0];
					 
					if
					(
						item.getType() == Material.GOLD_AXE
						|| item.getType() == Material.GOLD_PICKAXE
						|| item.getType() == Material.GOLD_SPADE
						|| item.getType() == Material.GOLD_HOE
						|| item.getType() == Material.GOLD_SWORD
						|| item.getType() == Material.WOOD_AXE
						|| item.getType() == Material.WOOD_PICKAXE
						|| item.getType() == Material.WOOD_SPADE
						|| item.getType() == Material.WOOD_HOE
						|| item.getType() == Material.WOOD_SWORD
					)
					{
						e.setCancelled(true);
						e.getWhoClicked().closeInventory();
						e.getWhoClicked().sendMessage(ChatColor.RED + Survival.Words.get("You cannot rename or repair ") + item.getItemMeta().getDisplayName() + ChatColor.RED + Survival.Words.get("period"));
					}
				}
			}
		}
	}
}
