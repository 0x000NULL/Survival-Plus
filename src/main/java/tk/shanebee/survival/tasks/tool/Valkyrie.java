package tk.shanebee.survival.tasks.tool;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import tk.shanebee.survival.Survival;
import tk.shanebee.survival.managers.ItemManager;
import tk.shanebee.survival.managers.Items;

public class Valkyrie extends BukkitRunnable {

	private Survival plugin;
	private int id;

	public Valkyrie(Survival plugin) {
		this.plugin = plugin;
		this.id = this.runTaskTimer(plugin, 1, 10).getTaskId();
	}

	@Override
	public void run() {
		for (Player player : plugin.getServer().getOnlinePlayers()) {
			if (ItemManager.compare(player.getInventory().getItemInMainHand(), Items.VALKYRIES_AXE)) {
				Location particleLoc = player.getLocation();
				particleLoc.setY(particleLoc.getY() + 1);
				assert particleLoc.getWorld() != null;
				particleLoc.getWorld().spawnParticle(Particle.CRIT_MAGIC, particleLoc, 10, 0.5, 0.5, 0.5);
			}
		}
	}

}
