package tk.shanebee.survival.listeners.item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tk.shanebee.survival.Survival;
import tk.shanebee.survival.config.Config;
import tk.shanebee.survival.config.Lang;
import tk.shanebee.survival.data.PlayerData;
import tk.shanebee.survival.events.ThirstLevelChangeEvent;
import tk.shanebee.survival.managers.ItemManager;
import tk.shanebee.survival.item.Item;
import tk.shanebee.survival.managers.PlayerManager;
import tk.shanebee.survival.managers.StatusManager;
import tk.shanebee.survival.util.Utils;

import java.util.Random;

public class Consume implements Listener {

	private final Survival plugin;
	private final Config config;
	private final Lang lang;
	private final PlayerManager playerManager;

	public Consume(Survival plugin) {
		this.plugin = plugin;
		this.config = plugin.getSurvivalConfig();
		this.lang = plugin.getLang();
		this.playerManager = plugin.getPlayerManager();
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onConsume(PlayerItemConsumeEvent event) {
		if (event.isCancelled()) return;
		final Player player = event.getPlayer();
		PlayerData playerData = playerManager.getPlayerData(player);
		ItemStack item = event.getItem();
		int change = 0;
		switch (event.getItem().getType()) {
			case POTION:
				if (config.MECHANICS_THIRST_PURIFY_WATER) {
					if (checkWaterBottle(item)) {
						if (ItemManager.compare(item, Item.DIRTY_WATER)) {
							change = config.MECHANICS_THIRST_REP_DIRTY_WATER;
							Random rand = new Random();
							if (rand.nextInt(10) + 1 <= 5) {
								player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
								player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 0));
							}
						} else if (ItemManager.compare(item, Item.CLEAN_WATER)) {
							change = config.MECHANICS_THIRST_REP_CLEAN_WATER;
							Random rand = new Random();
							if (rand.nextInt(10) + 1 <= 2) {
								player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
								player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 0));
							}
						} else if (ItemManager.compare(item, Item.PURIFIED_WATER)) {
							change = config.MECHANICS_THIRST_REP_PURE_WATER;
						} else if (ItemManager.compare(item, Item.COFFEE)) {
							change = config.MECHANICS_THIRST_REP_COFFEE;
						} else if (ItemManager.compare(item, Item.COLD_MILK)) {
							change = config.MECHANICS_THIRST_REP_COLD_MILK;
						} else if (ItemManager.compare(item, Item.HOT_MILK)) {
							change = config.MECHANICS_THIRST_REP_HOT_MILK;
							player.damage(2);
							player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 0));
							Utils.sendColoredMsg(player, lang.hot_milk_drink);
						} else if (ItemManager.compare(item, Item.WATER_BOWL)) {
						    event.setCancelled(true);
						    change = handleWaterBowl(player);
                        } else {
						    change = config.MECHANICS_THIRST_REP_OTHER_WATER;
                        }
					}
				} else {
					change = config.MECHANICS_THIRST_REP_WATER;
				}
				break;
			case BEETROOT_SOUP: //OLD Water Bowl (removed in 3.11.0 - keep for a few versions)
				if (ItemManager.compare(event.getPlayer().getInventory().getItemInMainHand(), Item.WATER_BOWL_OLD)) {
					event.setCancelled(true);
					change = handleWaterBowl(player);
				} else {
					change = config.MECHANICS_THIRST_REP_BEET_SOUP; // Regular beetroot soup (if player somehow gets one)
				}
				break;
			case MILK_BUCKET:
				change = config.MECHANICS_THIRST_REP_MILK_BUCKET;
				break;
			case MELON_SLICE:
				change = config.MECHANICS_THIRST_REP_MELON_SLICE;
				break;
			case MUSHROOM_STEW:
				change = config.MECHANICS_THIRST_REP_MUSH_STEW;
				break;
            case HONEY_BOTTLE:
                change = config.MECHANICS_THIRST_REP_HONEY_BOTTLE;
		}
		ThirstLevelChangeEvent thirstEvent = new ThirstLevelChangeEvent(player, change, playerData.getThirst() + change);
		Bukkit.getPluginManager().callEvent(thirstEvent);
		if (!thirstEvent.isCancelled()) {
			playerData.setThirst(playerData.getThirst() + change);
		}

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
			if (!config.MECHANICS_STATUS_SCOREBOARD) {
				player.sendMessage(plugin.getPlayerManager().ShowHunger(player).get(1) + plugin.getPlayerManager().ShowHunger(player).get(2) + " " + plugin.getPlayerManager().ShowHunger(player).get(0).toUpperCase());
				player.sendMessage(plugin.getPlayerManager().ShowThirst(player).get(1) + plugin.getPlayerManager().ShowThirst(player).get(2) + " " + plugin.getPlayerManager().ShowThirst(player).get(0).toUpperCase());
			}
		}, 1L);
	}

	private int handleWaterBowl(Player player) {
        int change = config.MECHANICS_THIRST_REP_WATER_BOWL;
        player.getInventory().setItemInMainHand(new ItemStack(Material.BOWL));
        if (config.MECHANICS_THIRST_PURIFY_WATER) {
            Random rand = new Random();
            if (rand.nextInt(10) + 1 <= 8) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 0));
            }
        }
        return change;
    }

    @EventHandler //if player catches a water bottle/potion give them dirty water instead
    private void onFish(PlayerFishEvent event) {
	    if (!config.MECHANICS_THIRST_PURIFY_WATER) return;
	    if (event.isCancelled()) return;
	    if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            Entity caught = event.getCaught();
            if (caught instanceof org.bukkit.entity.Item) {
                org.bukkit.entity.Item item = ((org.bukkit.entity.Item) caught);
                ItemStack stack = item.getItemStack();
                if (stack.getType() == Material.POTION && checkWaterBottle(stack)) {
                    item.setItemStack(Item.CLEAN_WATER.getItem());
                }
            }
        }
    }

	@EventHandler
	private void onRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		PlayerData playerData = playerManager.getPlayerData(player);

		int thirst = config.MECHANICS_THIRST_RESPAWN_AMOUNT;
		playerData.setThirst(thirst);
		playerManager.getPlayerData(player).setThirst(thirst);
		int hunger = config.MECHANICS_HUNGER_RESPAWN_AMOUNT;
		Bukkit.getScheduler().runTaskLater(plugin, () -> StatusManager.setHunger(player, hunger), 1);

	}

	private boolean checkWaterBottle(ItemStack bottle) {
		ItemMeta meta = bottle.getItemMeta();
		assert meta != null;
		switch (((PotionMeta) meta).getBasePotionData().getType()) {
			case WATER:
			case MUNDANE:
			case THICK:
			case AWKWARD:
				return true;
			default:
				return false;
		}
	}

}
