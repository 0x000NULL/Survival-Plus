package tk.shanebee.survival.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import tk.shanebee.survival.Survival;
import tk.shanebee.survival.events.ShootRecurvedBowEvent;
import tk.shanebee.survival.managers.ItemManager;
import tk.shanebee.survival.managers.Items;

import java.util.Random;

class RecurvedBow implements Listener {

	private Survival plugin;

	RecurvedBow(Survival plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	private void onShoot(EntityShootBowEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			ItemStack mainItem = event.getBow();

			assert mainItem != null;
			if (ItemManager.compare(mainItem, Items.RECURVE_BOW) || ItemManager.compare(mainItem, Items.RECURVE_CROSSBOW)) {
				Random rand = new Random();
				if (event.getForce() >= 1.0F) {
					final Entity arrow = event.getProjectile();
					final Vector velocity = player.getLocation().getDirection().add(new Vector(0, 0.025, 0)).multiply(4);
					Items item;
					if (ItemManager.compare(mainItem, Items.RECURVE_BOW)) {
						item = Items.RECURVE_BOW;
					} else {
						item = Items.RECURVE_CROSSBOW;
					}
					// Call new ShootRecurvedBowEvent
					ShootRecurvedBowEvent shootEvent = new ShootRecurvedBowEvent(player, mainItem, item);
					Bukkit.getPluginManager().callEvent(shootEvent);
					if (shootEvent.isCancelled()) {
						event.setCancelled(true);
						return;
					}

					arrow.setVelocity(velocity);

					player.getWorld().playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 1.0F, rand.nextFloat() * 0.4F + 0.8F);
					player.getWorld().playSound(player.getLocation(), Sound.ENTITY_SHULKER_BULLET_HURT, 0.5F, rand.nextFloat() * 0.4F + 0.8F);
					final Runnable task = new Runnable() {
						int times = 4;

						public void run() {
							if (!arrow.isOnGround()) {
								arrow.setVelocity(velocity);
								if (times-- > 0)
									Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, this, 5);
							}
						}
					};

					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, task, -1);
				} else {
					event.setCancelled(true);
					player.updateInventory();
					player.getWorld().playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 0.5F, rand.nextFloat() * 0.4F + 0.8F);
				}
			}
		}
	}

}
