package tk.shanebee.survival.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import tk.shanebee.survival.Survival;
import tk.shanebee.survival.util.Config;

/**
 * Internal use only
 */
public class EventManager {

	private Survival plugin;
	private FileConfiguration settings;
	private int localChat = Survival.LocalChatDist;
	private final Config CONFIG;

	public EventManager(Survival plugin, FileConfiguration settings) {
		this.plugin = plugin;
		this.settings = settings;
		this.CONFIG = plugin.getSurvivalConfig();
	}

	public void registerEvents() {
		PluginManager pm = plugin.getServer().getPluginManager();
		pm.registerEvents(this.plugin, this.plugin);
		pm.registerEvents(new RecipeDiscovery(plugin), this.plugin);

		if (settings.getBoolean("Survival.Enabled")) {
			pm.registerEvents(new BlockBreak(plugin), this.plugin);
			pm.registerEvents(new BlockPlace(plugin), this.plugin);
			pm.registerEvents(new FirestrikerClick(plugin), this.plugin);
			pm.registerEvents(new ShivPoison(), this.plugin);
			pm.registerEvents(new WaterBowl(plugin), this.plugin);
			pm.registerEvents(new Campfire(plugin), this.plugin);
			//pm.registerEvents(new Backpack(), this.plugin); needs to be reworked
		}
		pm.registerEvents(new NoAnvil(plugin), this.plugin);
		if (settings.getBoolean("Mechanics.Bow"))
			pm.registerEvents(new Bow(plugin), this.plugin);
		if (settings.getBoolean("Mechanics.GrapplingHook"))
			pm.registerEvents(new GrapplingHook(plugin), this.plugin);
		if (settings.getBoolean("LegendaryItems.ObsidianMace"))
			pm.registerEvents(new ObsidianMaceWeakness(plugin), this.plugin);
		if (settings.getBoolean("LegendaryItems.ValkyrieAxe"))
			pm.registerEvents(new Valkyrie(plugin), this.plugin);
		if (settings.getBoolean("LegendaryItems.GiantBlade"))
			pm.registerEvents(new GiantBlade(plugin), this.plugin);
		if (settings.getBoolean("LegendaryItems.BlazeSword"))
			pm.registerEvents(new BlazeSword(), this.plugin);
		if (localChat > -1)
			pm.registerEvents(new LocalChat(plugin), this.plugin);
		if (settings.getBoolean("Mechanics.CompassWaypoint"))
			pm.registerEvents(new CompassWaypoint(this.plugin), this.plugin);
		if (settings.getBoolean("Mechanics.MedicalKit"))
			pm.registerEvents(new MedicKit(plugin), this.plugin);

		pm.registerEvents(new WaterBottleCrafting(plugin), this.plugin);
		pm.registerEvents(new SpecialItemInteractCancel(plugin), this.plugin);

		pm.registerEvents(new SetResourcePack(plugin), this.plugin);

		if (settings.getBoolean("Mechanics.RawMeatHunger"))
			pm.registerEvents(new RawMeatHunger(), this.plugin);
		if (settings.getBoolean("Mechanics.Thirst.Enabled")) {
			pm.registerEvents(new Consume(this.plugin), this.plugin);
			if (settings.getBoolean("Mechanics.Thirst.PurifyWater"))
				pm.registerEvents(new CauldronWaterBottle(), this.plugin);
		}
		if (settings.getBoolean("Mechanics.PoisonousPotato"))
			pm.registerEvents(new PoisonousPotato(), this.plugin);
		if (settings.getBoolean("Mechanics.SharedWorkbench"))
			pm.registerEvents(new WorkbenchShare(plugin), this.plugin);
		if (settings.getBoolean("Mechanics.Chairs.Enabled"))
			pm.registerEvents(new Chairs(plugin), this.plugin);
		if (settings.getBoolean("Mechanics.CookieHealthBoost"))
			pm.registerEvents(new CookieHealthBoost(), this.plugin);
		if (settings.getBoolean("Mechanics.BeetrootStrength"))
			pm.registerEvents(new BeetrootStrength(), this.plugin);
		if (settings.getBoolean("Mechanics.Clownfish"))
			pm.registerEvents(new Clownfish(), this.plugin);
		if (settings.getBoolean("Mechanics.LivingSlime"))
			pm.registerEvents(new LivingSlime(plugin), this.plugin);
		if (settings.getBoolean("Mechanics.BedFatigueLevel"))
			pm.registerEvents(new BedFatigue(plugin), this.plugin);
		if (CONFIG.MECHANICS_FOOD_DIVERSITY_ENABLED)
			pm.registerEvents(new FoodDiversityConsume(plugin), this.plugin);
		if (settings.getBoolean("Mechanics.RecurveBow"))
			pm.registerEvents(new RecurvedBow(plugin), this.plugin);
		if (settings.getBoolean("Mechanics.StatusScoreboard"))
			pm.registerEvents(new ScoreboardStats(plugin), this.plugin);
		if (settings.getBoolean("Mechanics.SnowballRevamp"))
			pm.registerEvents(new SnowballThrow(), this.plugin);
		if (settings.getBoolean("Mechanics.SnowGenerationRevamp"))
			pm.registerEvents(new SnowGeneration(plugin), this.plugin);
		pm.registerEvents(new ChickenSpawn(), this.plugin);
		if (settings.getBoolean("WelcomeGuide.Enabled"))
			pm.registerEvents(new Guide(plugin), this.plugin);
		if (settings.getBoolean("Mechanics.BurnoutTorches.Enabled")) // TODO experimental feature, not 100% sure about this
			pm.registerEvents(new BurnoutTorches(this.plugin), this.plugin);
		pm.registerEvents(new InventoryUpdate(), this.plugin);

		if (CONFIG.ENTITY_MECHANICS_PIGMEN_CHEST_ENABLED)
			pm.registerEvents(new ChestPigmen(this.plugin), this.plugin);
	}

}
