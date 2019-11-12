package tk.shanebee.survival.listeners;

import java.util.Random;

import org.bukkit.scoreboard.Scoreboard;
import tk.shanebee.survival.managers.ItemManager;
import tk.shanebee.survival.managers.Items;
import tk.shanebee.survival.managers.PlayerManager;
import tk.shanebee.survival.util.Lang;
import tk.shanebee.survival.util.Utils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Objective;

import tk.shanebee.survival.Survival;

class MedicKit implements Listener {

	private Survival plugin;
	private Lang lang;
	private Scoreboard board;
	private PlayerManager playerManager;

	private Objective healing;
	private Objective healTimes;
	
	MedicKit(Survival plugin) {
		this.plugin = plugin;
		this.lang = plugin.getLang();
		this.board = plugin.getBoard();
		this.playerManager = plugin.getPlayerManager();
		this.healing = board.getObjective("Healing");
		this.healing = board.getObjective("HealTimes");
	}

	

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onDamaged(EntityDamageByEntityEvent event) {
		if (event.isCancelled()) return;
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			healing.getScore(player.getName()).setScore(0);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onClickEntity(PlayerInteractEntityEvent event) {
		if (event.isCancelled()) return;
		final Player player = event.getPlayer();
		final ItemStack mainItem = player.getInventory().getItemInMainHand();
		if (ItemManager.compare(mainItem, Items.MEDIC_KIT)) {
			if (healing.getScore(player.getName()).getScore() <= 0) {
				if (!player.isSneaking()) {
					if (event.getRightClicked() instanceof Player) {
						final Player healed = (Player) event.getRightClicked();
						if (healing.getScore(healed.getName()).getScore() <= 0) {
							if (player.getLocation().distance(healed.getLocation()) <= 4) {
								healing.getScore(player.getName()).setScore(1);
								healing.getScore(healed.getName()).setScore(1);
								healed.teleport(playerManager.lookAt(healed.getLocation(), player.getLocation()));
								player.sendMessage(Utils.getColoredString(lang.healing) + ChatColor.RESET + healed.getDisplayName() + Utils.getColoredString(lang.keep) + ChatColor.DARK_GREEN + Utils.getColoredString(lang.medical_kit) + Utils.getColoredString(lang.on_hand));
								healed.sendMessage(Utils.getColoredString(lang.being_healed) + ChatColor.RESET + player.getDisplayName() + Utils.getColoredString(lang.stay_still));

								healTimes.getScore(player.getName()).setScore(5);
								final Runnable task = new Runnable() {
									public void run() {
										int times = healTimes.getScore(player.getName()).getScore();
										if (player.getInventory().getItemInMainHand().getType() == Material.CLOCK && player.getLocation().distance(healed.getLocation()) <= 4 && healing.getScore(player.getName()).getScore() > 0 && healing.getScore(healed.getName()).getScore() > 0) {
											if (times-- > 0) {
												player.teleport(playerManager.lookAt(player.getLocation(), healed.getLocation()));

												Random rand = new Random();

												player.removePotionEffect(PotionEffectType.SLOW);
												player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 6, true, false));
												player.removePotionEffect(PotionEffectType.JUMP);
												player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20, 199, true, false));

												healed.getWorld().playSound(healed.getLocation(), Sound.ENTITY_LEASH_KNOT_PLACE, 1.0F, rand.nextFloat() * 0.4F + 0.8F);
												healed.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0));

												Location particleLoc = healed.getLocation();
												particleLoc.setY(particleLoc.getY() + 1);
												Utils.spawnParticle(particleLoc, Particle.VILLAGER_HAPPY, 10, 0.5, 0.5, 0.5);

												Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, this, 20L);
												healTimes.getScore(player.getName()).setScore(times);
											} else {
												healing.getScore(player.getName()).setScore(0);
												healing.getScore(healed.getName()).setScore(0);

												player.sendMessage(ChatColor.DARK_GREEN + Utils.getColoredString(lang.healing_complete));
												healed.sendMessage(ChatColor.DARK_GREEN + Utils.getColoredString(lang.healing_complete));

												player.getInventory().removeItem(ItemManager.get(Items.MEDIC_KIT));
											}
										} else {
											healing.getScore(player.getName()).setScore(0);
											healing.getScore(healed.getName()).setScore(0);

											player.sendMessage(ChatColor.DARK_RED + Utils.getColoredString(lang.healing_interrupted));
											healed.sendMessage(ChatColor.DARK_RED + Utils.getColoredString(lang.healing_interrupted));

											player.getInventory().removeItem(ItemManager.get(Items.MEDIC_KIT));
										}
									}
								};
								Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, task, -1L);
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	private void onSelfClick(PlayerInteractEvent event) {
		if (event.hasItem() && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			final Player player = event.getPlayer();
			ItemStack mainItem = player.getInventory().getItemInMainHand();
			if (ItemManager.compare(mainItem, Items.MEDIC_KIT)) {
				if (healing.getScore(player.getName()).getScore() <= 0) {
					if (player.isSneaking()) {
						healing.getScore(player.getName()).setScore(1);
						player.sendMessage(Utils.getColoredString(lang.healing_self) + Utils.getColoredString(lang.keep) + ChatColor.DARK_GREEN + Utils.getColoredString(lang.medical_kit) + Utils.getColoredString(lang.on_hand));

						healTimes.getScore(player.getName()).setScore(5);
						final Runnable task = new Runnable() {
							public void run() {
								int times = healTimes.getScore(player.getName()).getScore();
								if (ItemManager.compare(player.getInventory().getItemInMainHand(), Items.MEDIC_KIT) && healing.getScore(player.getName()).getScore() > 0) {
									if (times-- > 0) {
										Random rand = new Random();

										player.removePotionEffect(PotionEffectType.SLOW);
										player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 6, true, false));
										player.removePotionEffect(PotionEffectType.JUMP);
										player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20, 199, true, false));

										player.getWorld().playSound(player.getLocation(), Sound.ENTITY_LEASH_KNOT_PLACE, 1.0F, rand.nextFloat() * 0.4F + 0.8F);
										player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0));

										Location particleLoc = player.getLocation();
										particleLoc.setY(particleLoc.getY() + 1);
										Utils.spawnParticle(particleLoc, Particle.VILLAGER_HAPPY, 10, 0.5, 0.5, 0.5);

										Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, this, 20L);
										healTimes.getScore(player.getName()).setScore(times);
									} else {
										healing.getScore(player.getName()).setScore(0);

										player.sendMessage(ChatColor.DARK_GREEN + Utils.getColoredString(lang.healing_complete));

										player.getInventory().removeItem(ItemManager.get(Items.MEDIC_KIT));
									}
								} else {
									healing.getScore(player.getName()).setScore(0);

									player.sendMessage(ChatColor.DARK_RED + Utils.getColoredString(lang.healing_interrupted));

									player.getInventory().removeItem(ItemManager.get(Items.MEDIC_KIT));
								}
							}
						};

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, task, -1L);
					}
				}
			}
		}
	}


}