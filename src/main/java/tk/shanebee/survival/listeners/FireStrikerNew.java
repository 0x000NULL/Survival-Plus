package tk.shanebee.survival.listeners;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import tk.shanebee.survival.Survival;
import tk.shanebee.survival.item.FireStriker;
import tk.shanebee.survival.managers.ItemManager;
import tk.shanebee.survival.managers.Items;
import tk.shanebee.survival.managers.PlayerManager;

import java.util.Random;

class FireStrikerNew implements Listener {

    private PlayerManager playerManager;

    FireStrikerNew(Survival plugin) {
        this.playerManager = plugin.getPlayerManager();
    }

    @EventHandler
    private void onClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) return;
        Player player = event.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();
        if (!ItemManager.compare(hand, Items.FIRESTRIKER)) return;

        if (player.isSneaking()) {
            event.setCancelled(true);
            FireStriker fireStriker = playerManager.getFireStriker(hand);
            fireStriker.updateItem(hand);
            if (fireStriker.getDamage() >= 60) {
                World world = player.getWorld();
                Location loc = player.getLocation();
                if (fireStriker.getInput() != null) {
                    if (player.getInventory().addItem(fireStriker.getInput()).size() > 0) {
                        world.dropItem(loc, fireStriker.getInput());
                    }
                    fireStriker.setInput(null);
                }
                if (fireStriker.getOutput() != null) {
                    if (player.getInventory().addItem(fireStriker.getOutput()).size() > 0) {
                        world.dropItem(loc, fireStriker.getOutput());
                    }
                    fireStriker.setOutput(null);
                }
                playerManager.removeFireStriker(fireStriker.getId());
                player.getInventory().setItemInMainHand(null);
                Random rand = new Random();
                player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, rand.nextFloat() * 0.4F + 0.8F);
            } else {
                fireStriker.openToPlayer(player);
            }
        } else {
            //TODO light fires
        }
    }

    @EventHandler
    private void onBreak(PlayerItemBreakEvent event) {
        if (ItemManager.compare(event.getBrokenItem(), Items.FIRESTRIKER)) {
            playerManager.removeFireStriker(playerManager.getFireStriker(event.getBrokenItem()).getId());
        }
    }

}
