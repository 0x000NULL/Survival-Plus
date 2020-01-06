package tk.shanebee.survival.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import tk.shanebee.survival.Survival;
import tk.shanebee.survival.config.Lang;
import tk.shanebee.survival.item.FireStriker;
import tk.shanebee.survival.util.Utils;

public class FireStrikerGUI implements InventoryHolder, Listener {

    private Inventory inv;
    private Lang lang;
    private FireStriker fireStriker;
    private BukkitRunnable invUpdate;

    public FireStrikerGUI(Survival plugin, FireStriker fireStriker) {
        this.lang = plugin.getLang();
        this.fireStriker = fireStriker;
        this.inv = Bukkit.createInventory(this, InventoryType.FURNACE, Utils.getColoredString(lang.firestriker));
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inv;
    }

    private void initializeItems() {
        // 0 - INPUT
        this.inv.setItem(0, fireStriker.getInput());
        // 1 - FUEL
        this.inv.setItem(1, fireStriker.getItem());
        // 2 - OUTPUT
        this.inv.setItem(2, fireStriker.getOutput());
    }

    public void openInventory(Player player) {
        player.openInventory(inv);
        initializeItems();
        invUpdate = new BukkitRunnable() {
            @Override
            public void run() {
                if (inv.getViewers().contains(player)) {
                    setCookTime(player, fireStriker.getCookTime());
                    setBurnTime(player, fireStriker.getBurnTime());
                    inv.setItem(0, fireStriker.getInput());
                    inv.setItem(2, fireStriker.getOutput());
                } else {
                    this.cancel();
                }
            }
        };
        invUpdate.runTaskTimer(Survival.getInstance(), 0, 1);

    }

    public void setCookTime(Player player, int time) {
        player.getOpenInventory().setProperty(InventoryView.Property.TICKS_FOR_CURRENT_SMELTING, 100);
        player.getOpenInventory().setProperty(InventoryView.Property.COOK_TIME, time);
    }

    public void setBurnTime(Player player, int time) {
        player.getOpenInventory().setProperty(InventoryView.Property.TICKS_FOR_CURRENT_FUEL, 100 * 8);
        player.getOpenInventory().setProperty(InventoryView.Property.BURN_TIME, time);
    }

    @EventHandler
    private void onClick(InventoryClickEvent event) {
        if (inv.getHolder() != this) return;
        if (event.getInventory().getHolder() != inv.getHolder()) return;
        if (event.getInventory() != inv) return;

        ItemStack cursor = event.getCursor();

        int slot = event.getRawSlot();
        // Prevent clicking fuel slot
        if (slot == 1) {
            event.setCancelled(true);
        } else if (slot == 0) {
            // Prevent non-smeltables in input slot
            if (cursor == null || cursor.getType() == Material.AIR) {
                fireStriker.setInput(null);
            } else if (!Utils.smeltCheck(cursor)) {
                event.setCancelled(true);
            } else if (inv.getItem(0) == null) {
                fireStriker.setInput(cursor.clone());
                fireStriker.cook();
            }
        } else if (slot == 2) {
            if (inv.getItem(2) != null) {
                fireStriker.setOutput(null);
            }
        }
    }

}
