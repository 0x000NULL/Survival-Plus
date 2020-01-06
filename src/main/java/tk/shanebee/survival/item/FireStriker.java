package tk.shanebee.survival.item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import tk.shanebee.survival.Survival;
import tk.shanebee.survival.gui.FireStrikerGUI;
import tk.shanebee.survival.tasks.tool.FireStrikerCook;

import java.util.UUID;

public class FireStriker {

    private UUID id;
    private int cookTime;
    private int burnTime;
    private FireStrikerGUI gui;
    private ItemStack input, output, fireStriker;
    private int damage;

    // New FireStriker
    public FireStriker(ItemStack fireStriker) {
        this.fireStriker = fireStriker;
        this.id = UUID.randomUUID();
        this.gui = new FireStrikerGUI(Survival.getInstance(), this);
        this.cookTime = 0;
        // 8 items @ 5 seconds each
        this.burnTime = 8 * 5 * 20;
        this.input = new ItemStack(Material.AIR);
        this.output = new ItemStack(Material.AIR);
        save();
    }

    // FireStriker from previous item
    public FireStriker(ItemStack fireStriker, UUID uuid, int cookTime, int burnTime, ItemStack input, ItemStack output) {
        this.fireStriker = fireStriker;
        this.id = uuid;
        this.cookTime = cookTime;
        this.burnTime = burnTime;
        this.input = input;
        this.output = output;
        this.gui = new FireStrikerGUI(Survival.getInstance(), this);
    }

    // GETTERS & SETTERS


    public UUID getId() {
        return id;
    }

    /** Get the cook time of this FireStriker
     * @return Cook time of this FireStriker
     */
    public int getCookTime() {
        return cookTime;
    }

    /** Set the cook time of this FireStriker
     * @param cookTime Cook time to set
     */
    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    /** Get the burn time of this FireStriker
     * @return Burn time of this FireStriker
     */
    public int getBurnTime() {
        return burnTime;
    }

    /** Set the burn time of this FireStriker
     * @param burnTime Burn time to set
     */
    public void setBurnTime(int burnTime) {
        this.burnTime = burnTime;
    }

    /** Get the item in the input slot
     * @return Item in input slot
     */
    public ItemStack getInput() {
        return input;
    }

    /** Set the item in the input slot
     * @param input Item to put in input slot
     */
    public void setInput(ItemStack input) {
        this.input = input;
    }

    /** Get the item in the output slot
     * @return Item in output slot
     */
    public ItemStack getOutput() {
        return output;
    }

    /** Set the item in the output slot
     * @param output Item to put in output slot
     */
    public void setOutput(ItemStack output) {
        this.output = output;
    }

    /** Get the ItemStack associated with this FireStriker
     * @return ItemStack associated with this FireStriker
     */
    public ItemStack getItem() {
        return this.fireStriker;
    }

    public int getDamage() {
        return damage;
    }

    // METHODS

    public void damageItem() {
        ItemMeta meta = fireStriker.getItemMeta();
        assert meta != null;
        damage = damage + 8;
        ((Damageable) meta).setDamage(damage);
        fireStriker.setItemMeta(meta);
    }

    public void updateItem(ItemStack item) {
        this.fireStriker = item;
        ItemMeta meta = fireStriker.getItemMeta();
        assert meta != null;
        ((Damageable) meta).setDamage(damage);
        fireStriker.setItemMeta(meta);
    }

    /** Open the FireStriker GUI to a player
     * @param player Player to open inventory for
     */
    public void openToPlayer(Player player) {
        gui.openInventory(player);
    }

    /**
     * Cook items in this FireStriker
     */
    public void cook() {
        new FireStrikerCook(this);
    }

    @Override
    public String toString() {
        return "FireStriker{" +
                "id=" + id +
                ", cookTime=" + cookTime +
                ", burnTime=" + burnTime +
                ", input=" + input +
                ", output=" + output +
                '}';
    }

    public void save() {
        ItemMeta meta = this.fireStriker.getItemMeta();
        NamespacedKey key = new NamespacedKey(Survival.getInstance(), "firestriker");
        assert meta != null;
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, this.toString());
        this.fireStriker.setItemMeta(meta);
    }

    public static FireStriker deserialize(ItemStack item) {
        NamespacedKey key = new NamespacedKey(Survival.getInstance(), "firestriker");
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        if (meta.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
            String s = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
            assert s != null;
            s = s.replace("FireString{", "").replace("}", "");
            String[] data = s.split(", ");
            UUID uuid = UUID.fromString(data[0].split("=")[1]);
            int cookTime = Integer.parseInt(data[1].split("=")[1]);
            int burnTime = Integer.parseInt(data[2].split("=")[1]);
            String in = data[3].split("=")[1];
            String out = data[4].split("=")[1];
            ItemStack input = !in.equals("null") ? new ItemStack(Material.valueOf(in)) : null;
            ItemStack output = !out.equals("null") ? new ItemStack(Material.valueOf(out)) : null;
            return new FireStriker(item, uuid, cookTime, burnTime, input, output);
        }
        return new FireStriker(item);
    }

}
