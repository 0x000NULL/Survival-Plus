package tk.shanebee.survival.listeners;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tk.shanebee.survival.Survival;
import tk.shanebee.survival.util.Lang;
import tk.shanebee.survival.util.Utils;

class Guide implements Listener {
    
    private Survival plugin;
    private Lang lang;
    private FileConfiguration settings;
    
    Guide(Survival plugin) {
        this.plugin = plugin;
        this.lang = plugin.getLang();
        this.settings = plugin.getSettings();
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent e) {
        if (e.getPlayer().hasPlayedBefore() && settings.getBoolean("WelcomeGuide.NewPlayersOnly")) return;
        int delay = settings.getInt("WelcomeGuide.Delay");
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            Player player = e.getPlayer();
            TextComponent msg = new TextComponent(Utils.getColoredString(lang.survival_guide_msg));
            TextComponent link = new TextComponent(Utils.getColoredString(lang.survival_guide_click_msg));
            link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, lang.survival_guide_link));
            link.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder(Utils.getColoredString(lang.survival_guide_hover_msg)).create()));
            player.spigot().sendMessage(msg, link);
        }, 20 * delay);
    }

}
