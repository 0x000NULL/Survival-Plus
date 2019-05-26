package tk.shanebee.survival.events;

import tk.shanebee.survival.managers.Items;
import tk.shanebee.survival.util.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ObsidianMaceWeakness implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onAttack(EntityDamageByEntityEvent event) {
		if (event.isCancelled()) return;
		if (event.getDamager() instanceof Player && event.getEntity() instanceof LivingEntity && event.getCause() == DamageCause.ENTITY_ATTACK) {
			Player player = (Player) event.getDamager();
			ItemStack mainItem = player.getInventory().getItemInMainHand();
			LivingEntity enemy = (LivingEntity) event.getEntity();

			if (Items.compare(mainItem, Items.OBSIDIAN_MACE)) {
				applyObsidianMaceEffects(player, enemy);
			}
		}
	}

	/** Apply obsidian mace effects to player and enemy
	 * @param player Player to apply Regeneration to
	 * @param enemy Enemy to apply weakness and slowness to
	 */
	@SuppressWarnings("WeakerAccess")
	public static void applyObsidianMaceEffects(Player player, LivingEntity enemy) {
		enemy.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 0, false));
		enemy.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0, false));
		player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 48, 2, true));
		Location particleLoc = player.getLocation();
		particleLoc.setY(particleLoc.getY() + 2);
		Utils.spawnParticle(particleLoc, Particle.HEART, 2, 0.5, 0.5, 0.5);
	}

}