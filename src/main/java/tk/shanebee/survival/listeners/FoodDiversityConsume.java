package tk.shanebee.survival.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import tk.shanebee.survival.Survival;
import tk.shanebee.survival.data.Nutrient;
import tk.shanebee.survival.data.PlayerData;
import tk.shanebee.survival.managers.PlayerManager;

class FoodDiversityConsume implements Listener {

	private PlayerManager playerManager;

	FoodDiversityConsume(Survival plugin) {
		this.playerManager = plugin.getPlayerManager();
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onConsume(PlayerItemConsumeEvent event) {
		if (event.isCancelled()) return;
		Player player = event.getPlayer();
		switch (event.getItem().getType()) {
			case PUMPKIN_PIE:
				addStats(player, 300, 50, 60);
				break;
			case RABBIT_STEW:
				addStats(player, 200, 225, 240);
				break;
			case BREAD:
				addStats(player, 300, 25, 12);
				break;
			//case CAKE: (PlayerInteractEvent)
			case POTATO:
				addStats(player, 25, 0, 10);
				break;
			case BAKED_POTATO:
				addStats(player, 200, 0, 60);
				break;
			case POISONOUS_POTATO:
				addStats(player, 50, 0, 8);
				break;
			case APPLE:
			case GOLDEN_APPLE:
			case ENCHANTED_GOLDEN_APPLE:
				addStats(player, 50, 0, 70);
				break;
			case SWEET_BERRIES:
				addStats(player, 40, 0, 60);
			case CHORUS_FRUIT:
			case MELON:
				addStats(player, 25, 0, 35);
				break;
			case MUSHROOM_STEW:
			case BEETROOT_SOUP:
				addStats(player, 0, 50, 200);
				break;
			case COOKIE:
				addStats(player, 107, 11, 3);
				break;
			case MILK_BUCKET:
				addStats(player, 0, 250, 0);
				break;
			case BEETROOT:
				addStats(player, 0, 0, 35);
				break;
			case CARROT:
				addStats(player, 0, 0, 105);
				break;
			case GOLDEN_CARROT:
				addStats(player, 0, 0, 25);
				break;
			case COOKED_COD:
			case COOKED_SALMON:
				addStats(player, 0, 225, 0);
				break;
			case COOKED_BEEF:
			case COOKED_CHICKEN:
			case COOKED_MUTTON:
			case COOKED_PORKCHOP:
			case COOKED_RABBIT:
				addStats(player, 0, 200, 0);
				break;
			case SALMON:
			case COD:
			case PUFFERFISH:
			case TROPICAL_FISH:
				addStats(player, 0, 75, 0);
				break;
			case BEEF:
			case CHICKEN:
			case MUTTON:
			case PORKCHOP:
			case RABBIT:
			case SPIDER_EYE:
				addStats(player, 0, 50, 0);
				break;
			case ROTTEN_FLESH:
				addStats(player, 0, 25, 25);
				break;
			case DRIED_KELP:
				addStats(player, 15, 50, 50);
			default:
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	private void onConsumeCake(PlayerInteractEvent event) {
		if (event.isCancelled()) return;
		Player player = event.getPlayer();
		if (event.hasBlock() && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			Block cake = event.getClickedBlock();
			assert cake != null;
			if (cake.getType().equals(Material.CAKE)) {
				if (player.getFoodLevel() < 20 && (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE)) {
					addStats(player, 171, 114, 3);
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onDamage(EntityDamageEvent e) {
		if (e.isCancelled()) return;
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();

			e.setDamage(e.getDamage() * addMultiplier(player));
		}
	}

	@EventHandler
	private void onRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		switch (player.getWorld().getDifficulty()) {
			case PEACEFUL:
			case EASY:
				setStats(player, 960, 240, 360);
				break;
			case NORMAL:
				setStats(player, 480, 120, 180);
				break;
			case HARD:
				setStats(player, 96, 24, 36);
				break;
		}
	}

	private void addStats(Player player, Nutrient nutrient, int point) {
		PlayerData playerData = playerManager.getPlayerData(player);
		playerData.setNutrient(nutrient, playerData.getNutrient(nutrient) + point);

	}

	private void addStats(Player player, int carbs, int proteins, int salts) {
		addStats(player, Nutrient.CARBS, carbs);
		addStats(player, Nutrient.PROTEIN, proteins);
		addStats(player, Nutrient.SALTS, salts);
	}

	private void setStats(Player player, int carbs, int proteins, int salts) {
		PlayerData playerData = playerManager.getPlayerData(player);
		playerData.setNutrient(Nutrient.CARBS, carbs);
		playerData.setNutrient(Nutrient.PROTEIN, proteins);
		playerData.setNutrient(Nutrient.SALTS, salts);
	}

	private double addMultiplier(Player player) {
		PlayerData playerData = playerManager.getPlayerData(player);
		double damageMultiplier = 1;

		if (playerData.getNutrient(Nutrient.PROTEIN) <= 75) {
			switch (player.getWorld().getDifficulty()) {
				case EASY:
					damageMultiplier *= 1.25;
					break;
				case NORMAL:
					damageMultiplier *= 1.5;
					break;
				case HARD:
					damageMultiplier *= 2;
					break;
				default:
			}
		}
		if (playerData.getNutrient(Nutrient.SALTS) <= 100) {
			switch (player.getWorld().getDifficulty()) {
				case EASY:
					damageMultiplier *= 1.25;
					break;
				case NORMAL:
					damageMultiplier *= 1.5;
					break;
				case HARD:
					damageMultiplier *= 2;
					break;
				default:
			}
		}
		return damageMultiplier;
	}

}
