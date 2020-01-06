package tk.shanebee.survival.tasks.tool;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import tk.shanebee.survival.Survival;
import tk.shanebee.survival.item.FireStriker;

public class FireStrikerCook extends BukkitRunnable {

    private FireStriker fireStriker;

    public FireStrikerCook(FireStriker fireStriker) {
        this.fireStriker = fireStriker;
        this.runTaskTimer(Survival.getInstance(), 0, 1);
    }

    @Override
    public void run() {
        if (!canSmelt()) {
            fireStriker.setCookTime(0);
            this.cancel();
            return;
        }
        if (fireStriker.getBurnTime() > 0) {
            fireStriker.setCookTime(fireStriker.getCookTime() + 1);
            fireStriker.setBurnTime(fireStriker.getBurnTime() - 1);
            if (fireStriker.getCookTime() >= 100) {
                processCook();
            }
        } else {
            fireStriker.setCookTime(0);
            this.cancel();
        }
    }

    private boolean canSmelt() {
        if (fireStriker.getInput() == null) return false;
        if (fireStriker.getOutput() == null || fireStriker.getOutput().getType() == Material.AIR) return true;
        return fireStriker.getOutput().getType() == getCooked(fireStriker.getInput());
    }

    private void processCook() {
        fireStriker.setCookTime(0);
        ItemStack item = fireStriker.getInput().clone();
        Material resultType = getCooked(item);
        if (resultType == null) return;

        if (fireStriker.getOutput() == null || fireStriker.getOutput().getType() == Material.AIR) {
            fireStriker.setOutput(new ItemStack(resultType));
        } else {
            fireStriker.getOutput().setAmount(fireStriker.getOutput().getAmount() + 1);
        }
        fireStriker.getInput().setAmount(fireStriker.getInput().getAmount() - 1);
        fireStriker.damageItem();
    }

    private Material getCooked(ItemStack item) {
        if (item == null)
            return null;
        else if (item.getType() == Material.PORKCHOP)
            return Material.COOKED_PORKCHOP;
        else if (item.getType() == Material.BEEF)
            return Material.COOKED_BEEF;
        else if (item.getType() == Material.CHICKEN)
            return Material.COOKED_CHICKEN;
        else if (item.getType() == Material.SALMON)
            return Material.COOKED_SALMON;
        else if (item.getType() == Material.COD)
            return Material.COOKED_COD;
        else if (item.getType() == Material.POTATO)
            return Material.BAKED_POTATO;
        else if (item.getType() == Material.MUTTON)
            return Material.COOKED_MUTTON;
        else if (item.getType() == Material.RABBIT)
            return Material.COOKED_RABBIT;
        else if (item.getType() == Material.SAND)
            return Material.GLASS;
        else if (item.getType() == Material.CLAY_BALL)
            return Material.BRICK;
        else if (Tag.LOGS.isTagged(item.getType()))
            return Material.CHARCOAL;
        else
            return null;
    }

}
