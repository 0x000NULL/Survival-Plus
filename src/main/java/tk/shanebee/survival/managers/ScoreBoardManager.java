package tk.shanebee.survival.managers;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import tk.shanebee.survival.Survival;
import tk.shanebee.survival.config.Config;
import tk.shanebee.survival.tasks.Healthboard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreBoardManager {

    private Survival plugin;
    private Config config;
    private Map<UUID, Healthboard> playerBoards = new HashMap<>();

    public ScoreBoardManager(Survival plugin) {
        this.plugin = plugin;
        this.config = plugin.getSurvivalConfig();
    }

    /** Sets up a scoreboard for a player
     * <p>
     *     This is generally used internally
     * </p>
     * @param player Player to setup a scoreboard for
     */
    public void setupScoreboard(Player player) {
        playerBoards.put(player.getUniqueId(), new Healthboard(plugin, player));
    }

    public void resetStatusScoreboard(boolean enabled) {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            if (enabled)
                setupScoreboard(player);
            else
                player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
        }
    }

    public void unloadScoreboard(Player player) {
        if (playerBoards.containsKey(player.getUniqueId())) {
            playerBoards.get(player.getUniqueId()).cancel();
            playerBoards.remove(player.getUniqueId());
        }
    }

}
