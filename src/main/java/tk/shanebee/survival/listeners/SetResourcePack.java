package tk.shanebee.survival.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;
import tk.shanebee.survival.Survival;
import tk.shanebee.survival.managers.PlayerManager;
import tk.shanebee.survival.util.Config;
import tk.shanebee.survival.util.Lang;
import tk.shanebee.survival.util.Utils;

class SetResourcePack implements Listener {

	private Survival plugin;
	private Config config;
	private Lang lang;
	private PlayerManager playerManager;
	private String prefix;

	SetResourcePack(Survival plugin) {
		this.plugin = plugin;
		this.config = plugin.getSurvivalConfig();
		this.lang = plugin.getLang();
		this.playerManager = plugin.getPlayerManager();
		this.prefix = lang.prefix;
	}



	@EventHandler
	private void onPlayerJoin(PlayerJoinEvent event) {
		if (config.RESOURCE_PACK_ENABLED)
			playerManager.applyResourcePack(event.getPlayer(), 20);
	}

	/* Not sure why this was added, leaving here for now just in case its actually needed
	@EventHandler
	public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
		if (resourcePack)
			applyResourcePack(event.getPlayer());
	}
	 */

	@EventHandler
	private void onPlayerLeave(PlayerQuitEvent event) {
		plugin.getUsingPlayers().remove(event.getPlayer());
	}

	@EventHandler
	private void resourcePackEvent(PlayerResourcePackStatusEvent e) {
		Player player = e.getPlayer();
		if (config.RESOURCE_PACK_NOTIFY)
			if (e.getStatus() == Status.DECLINED) {
				player.sendMessage(" ");
				player.sendMessage(prefix + ChatColor.RED + Utils.getColoredString(lang.resource_pack_declined));
				player.sendMessage("   " + ChatColor.GOLD + Utils.getColoredString(lang.resource_pack_apply));
				player.sendMessage("   " + ChatColor.GOLD + Utils.getColoredString(lang.resource_pack_required));
			} else if (e.getStatus() == Status.ACCEPTED) {
				player.sendMessage(prefix + ChatColor.GREEN + Utils.getColoredString(lang.resource_pack_accepted));
			}
	}

}