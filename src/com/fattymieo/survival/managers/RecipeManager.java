package com.fattymieo.survival.managers;

import com.fattymieo.survival.Survival;
import com.fattymieo.survival.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecipeManager {


    public FileConfiguration settings;
    private Survival survival;

    public RecipeManager(Survival survival, FileConfiguration settings) {
        this.survival = survival;
        this.settings = settings;

    }

    @SuppressWarnings("deprecation")
    public void loadCustomRecipes() {
        removeRecipes();

        // Todo   HATCHET RECIPE
        ShapedRecipe hatchet1 = new ShapedRecipe(new NamespacedKey(survival, "hatchet1"), Items.get(Items.HATCHET));
        ShapedRecipe hatchet2 = new ShapedRecipe(new NamespacedKey(survival, "hatchet2"), Items.get(Items.HATCHET));

        hatchet1.shape("@@", " 1");

        hatchet1.setIngredient('@', Material.FLINT);
        hatchet1.setIngredient('1', Material.STICK);
        hatchet1.setGroup("HATCHET");

        hatchet2.shape("@@", "1 ");

        hatchet2.setIngredient('@', Material.FLINT);
        hatchet2.setIngredient('1', Material.STICK);
        hatchet2.setGroup("HATCHET");


        // Todo   MATTOCK RECIPE
        ShapedRecipe mattock1 = new ShapedRecipe(new NamespacedKey(survival, "mattock1"), Items.get(Items.MATTOCK));
        ShapedRecipe mattock2 = new ShapedRecipe(new NamespacedKey(survival, "mattock2"), Items.get(Items.MATTOCK));
        ShapedRecipe mattock3 = new ShapedRecipe(new NamespacedKey(survival, "mattock3"), Items.get(Items.MATTOCK));
        ShapedRecipe mattock4 = new ShapedRecipe(new NamespacedKey(survival, "mattock4"), Items.get(Items.MATTOCK));
        ShapedRecipe mattock5 = new ShapedRecipe(new NamespacedKey(survival, "mattock5"), Items.get(Items.MATTOCK));
        ShapedRecipe mattock6 = new ShapedRecipe(new NamespacedKey(survival, "mattock6"), Items.get(Items.MATTOCK));

        mattock1.shape("@-", "1@");
        mattock1.setIngredient('@', Material.FLINT);
        mattock1.setIngredient('-', Material.OAK_PLANKS);
        mattock1.setIngredient('1', Material.STICK);
        mattock1.setGroup("MATTOCK");

        mattock2.shape("@-", "1@");
        mattock2.setIngredient('@', Material.FLINT);
        mattock2.setIngredient('-', Material.BIRCH_PLANKS);
        mattock2.setIngredient('1', Material.STICK);
        mattock2.setGroup("MATTOCK");

        mattock3.shape("@-", "1@");
        mattock3.setIngredient('@', Material.FLINT);
        mattock3.setIngredient('-', Material.SPRUCE_PLANKS);
        mattock3.setIngredient('1', Material.STICK);
        mattock3.setGroup("MATTOCK");

        mattock4.shape("@-", "1@");
        mattock4.setIngredient('@', Material.FLINT);
        mattock4.setIngredient('-', Material.JUNGLE_PLANKS);
        mattock4.setIngredient('1', Material.STICK);
        mattock4.setGroup("MATTOCK");

        mattock5.shape("@-", "1@");
        mattock5.setIngredient('@', Material.FLINT);
        mattock5.setIngredient('-', Material.ACACIA_PLANKS);
        mattock5.setIngredient('1', Material.STICK);
        mattock5.setGroup("MATTOCK");

        mattock6.shape("@-", "1@");
        mattock6.setIngredient('@', Material.FLINT);
        mattock6.setIngredient('-', Material.DARK_OAK_PLANKS);
        mattock6.setIngredient('1', Material.STICK);
        mattock6.setGroup("MATTOCK");


        // Todo   SHIV RECIPE
        ShapedRecipe shiv1 = new ShapedRecipe(new NamespacedKey(survival, "shiv1"), Items.get(Items.SHIV));
        ShapedRecipe shiv2 = new ShapedRecipe(new NamespacedKey(survival, "shiv2"), Items.get(Items.SHIV));
        ShapedRecipe shiv3 = new ShapedRecipe(new NamespacedKey(survival, "shiv3"), Items.get(Items.SHIV));
        ShapedRecipe shiv4 = new ShapedRecipe(new NamespacedKey(survival, "shiv4"), Items.get(Items.SHIV));

        shiv1.shape("*@", "1&");

        shiv1.setIngredient('@', Material.FLINT);
        shiv1.setIngredient('1', Material.STICK);
        shiv1.setIngredient('*', Material.STRING);
        shiv1.setIngredient('&', Material.SPIDER_EYE);
        shiv1.setGroup("SHIV");

        shiv2.shape("@*", "&1");

        shiv2.setIngredient('@', Material.FLINT);
        shiv2.setIngredient('1', Material.STICK);
        shiv2.setIngredient('*', Material.STRING);
        shiv2.setIngredient('&', Material.SPIDER_EYE);
        shiv2.setGroup("SHIV");

        shiv3.shape("&@", "1*");

        shiv3.setIngredient('@', Material.FLINT);
        shiv3.setIngredient('1', Material.STICK);
        shiv3.setIngredient('*', Material.STRING);
        shiv3.setIngredient('&', Material.SPIDER_EYE);
        shiv3.setGroup("SHIV");

        shiv4.shape("@&", "*1");

        shiv4.setIngredient('@', Material.FLINT);
        shiv4.setIngredient('1', Material.STICK);
        shiv4.setIngredient('*', Material.STRING);
        shiv4.setIngredient('&', Material.SPIDER_EYE);
        shiv4.setGroup("SHIV");


        // Todo   HAMMER RECIPE
        ShapedRecipe hammer1 = new ShapedRecipe(new NamespacedKey(survival, "hammer1"), Items.get(Items.HAMMER));
        ShapedRecipe hammer2 = new ShapedRecipe(new NamespacedKey(survival, "hammer2"), Items.get(Items.HAMMER));

        hammer1.shape("@ ", "1@");

        hammer1.setIngredient('@', Material.COBBLESTONE);
        hammer1.setIngredient('1', Material.STICK);
        hammer1.setGroup("HAMMER");

        hammer2.shape(" @", "@1");

        hammer2.setIngredient('@', Material.COBBLESTONE);
        hammer2.setIngredient('1', Material.STICK);
        hammer2.setGroup("HAMMER");


        // Todo   STRING RECIPE
        ShapelessRecipe string = new ShapelessRecipe(new NamespacedKey(survival, "string"), new ItemStack(Material.STRING, 2));
        string.addIngredient(Material.COBWEB);


        // Todo   VALKYRIE's AXE RECIPE
        ShapedRecipe gAxe = new ShapedRecipe(new NamespacedKey(survival, "gaxe"), Items.get(Items.VALKYRIES_AXE));

        gAxe.shape("@@@", "@*@", " 1 ");

        gAxe.setIngredient('@', Material.DIAMOND);
        gAxe.setIngredient('*', Material.NETHER_STAR);
        gAxe.setIngredient('1', Material.STICK);


        // Todo   QUARTZ PICKAXE RECIPE
        ShapedRecipe gPickaxe1 = new ShapedRecipe(new NamespacedKey(survival, "gpickaxe1"), Items.get(Items.QUARTZ_PICKAXE));
        ShapedRecipe gPickaxe2 = new ShapedRecipe(new NamespacedKey(survival, "gpickaxe2"), Items.get(Items.QUARTZ_PICKAXE));
        gPickaxe1.shape("@B-", "B# ", "- 1");

        gPickaxe1.setIngredient('@', Material.QUARTZ_BLOCK);
        gPickaxe1.setIngredient('-', Material.DIAMOND);
        gPickaxe1.setIngredient('B', Material.DIAMOND_BLOCK);
        gPickaxe1.setIngredient('1', Material.STICK);
        gPickaxe1.setIngredient('#', Material.DRAGON_EGG);
        gPickaxe1.setGroup("QUARTZ PICKAXE");

        gPickaxe2.shape("-B@", " #B", "1 -");

        gPickaxe2.setIngredient('@', Material.QUARTZ_BLOCK);
        gPickaxe2.setIngredient('-', Material.DIAMOND);
        gPickaxe2.setIngredient('B', Material.DIAMOND_BLOCK);
        gPickaxe2.setIngredient('1', Material.STICK);
        gPickaxe2.setIngredient('#', Material.DRAGON_EGG);
        gPickaxe2.setGroup("QUARTZ PICKAXE");


        // Todo    OBSIDIAN MACE RECIPE
        ShapedRecipe gSpade1 = new ShapedRecipe(new NamespacedKey(survival, "gspade1"), Items.get(Items.OBSIDIAN_MACE));
        ShapedRecipe gSpade2 = new ShapedRecipe(new NamespacedKey(survival, "gspade2"), Items.get(Items.OBSIDIAN_MACE));

        gSpade1.shape(" @@", " &@", "1  ");

        gSpade1.setIngredient('@', Material.OBSIDIAN);
        gSpade1.setIngredient('&', Material.END_CRYSTAL);
        gSpade1.setIngredient('1', Material.STICK);
        gSpade1.setGroup("OBSIDIAN MACE");

        gSpade2.shape("@@ ", "@& ", "  1");

        gSpade2.setIngredient('@', Material.OBSIDIAN);
        gSpade2.setIngredient('&', Material.END_CRYSTAL);
        gSpade2.setIngredient('1', Material.STICK);
        gSpade2.setGroup("OBSIDIAN MACE");


        // Todo   ENDER GIANT BLADE RECIPE
        ShapedRecipe gHoe1 = new ShapedRecipe(new NamespacedKey(survival, "ghoe1"), Items.get(Items.ENDER_GIANT_BLADE));
        ShapedRecipe gHoe2 = new ShapedRecipe(new NamespacedKey(survival, "ghoe2"), Items.get(Items.ENDER_GIANT_BLADE));

        gHoe1.shape(" @@", "B*@", "1B ");

        gHoe1.setIngredient('*', Material.ENDER_EYE);
        gHoe1.setIngredient('@', Material.DIAMOND);
        gHoe1.setIngredient('B', Material.DIAMOND_BLOCK);
        gHoe1.setIngredient('1', Material.OAK_PLANKS);
        gHoe1.setGroup("ENDER GIANT BLADE");

        gHoe2.shape("@@ ", "@*B", " B1");

        gHoe2.setIngredient('*', Material.ENDER_EYE);
        gHoe2.setIngredient('@', Material.DIAMOND);
        gHoe2.setIngredient('B', Material.DIAMOND_BLOCK);
        gHoe2.setIngredient('1', Material.OAK_PLANKS);
        gHoe2.setGroup("ENDER GIANT BLADE");


        // Todo    BLAZE SWORD RECIPE
        ShapedRecipe gSword = new ShapedRecipe(new NamespacedKey(survival, "gsword"), Items.get(Items.BLAZE_SWORD));
        gSword.shape("*@*", "*@*", "*1*");

        gSword.setIngredient('@', Material.GOLD_INGOT);
        gSword.setIngredient('1', Material.BLAZE_ROD);
        gSword.setIngredient('*', Material.BLAZE_POWDER);


        // Todo    NOTCH APPLE RECIPE
        ShapedRecipe notchApple = new ShapedRecipe(new NamespacedKey(survival, "notchapple"), new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1));
        notchApple.shape("@@@", "@*@", "@@@");

        notchApple.setIngredient('@', Material.GOLD_BLOCK);
        notchApple.setIngredient('*', Material.APPLE);


        // Todo    SADDLE RECIPE
        ShapedRecipe saddle = new ShapedRecipe(new NamespacedKey(survival, "saddle"), new ItemStack(Material.SADDLE, 1));

        saddle.shape("@@@", "*-*", "= =");

        saddle.setIngredient('@', Material.LEATHER);
        saddle.setIngredient('*', Material.LEAD);
        saddle.setIngredient('-', Material.IRON_INGOT);
        saddle.setIngredient('=', Material.IRON_NUGGET);


        // Todo    NAMETAG RECIPE
        ShapedRecipe nametag1 = new ShapedRecipe(new NamespacedKey(survival, "nametag1"), new ItemStack(Material.NAME_TAG, 1));
        ShapedRecipe nametag2 = new ShapedRecipe(new NamespacedKey(survival, "nametag2"), new ItemStack(Material.NAME_TAG, 1));

        nametag1.shape(" -@", " *-", "*  ");

        nametag1.setIngredient('@', Material.STRING);
        nametag1.setIngredient('-', Material.IRON_INGOT);
        nametag1.setIngredient('*', Material.PAPER);
        nametag1.setGroup("NAMETAG");

        nametag2.shape("@- ", "-* ", "  *");

        nametag2.setIngredient('@', Material.STRING);
        nametag2.setIngredient('-', Material.IRON_INGOT);
        nametag2.setIngredient('*', Material.PAPER);
        nametag2.setGroup("NAMETAG");


        // Todo    PACKED ICE RECIPE
        ShapedRecipe packedIce1 = new ShapedRecipe(new NamespacedKey(survival, "packedice1"), new ItemStack(Material.PACKED_ICE, 1));
        ShapelessRecipe packedIce2 = new ShapelessRecipe(new NamespacedKey(survival, "packedice2"), new ItemStack(Material.ICE, 4));

        packedIce1.shape("@@ ", "@@ ");

        packedIce1.setIngredient('@', Material.ICE);

        packedIce2.addIngredient(Material.PACKED_ICE);

        // Todo    ICE RECIPE
        ShapedRecipe ice = new ShapedRecipe(new NamespacedKey(survival, "ice"), new ItemStack(Material.ICE, 1));

        ice.shape("@@@", "@*@", "@@@");

        ice.setIngredient('@', Material.SNOWBALL);
        ice.setIngredient('*', Material.WATER_BUCKET);


        // Todo    IRON HORSE ARMOR RECIPE
        ShapedRecipe ironHorse1 = new ShapedRecipe(new NamespacedKey(survival, "ironhorse1"), new ItemStack(Material.IRON_HORSE_ARMOR, 1));
        ShapedRecipe ironHorse2 = new ShapedRecipe(new NamespacedKey(survival, "ironhorse2"), new ItemStack(Material.IRON_HORSE_ARMOR, 1));

        ironHorse1.shape("  @", "#-#", "= =");

        ironHorse1.setIngredient('#', Material.IRON_BLOCK);
        ironHorse1.setIngredient('@', Material.IRON_INGOT);
        ironHorse1.setIngredient('-', Material.SADDLE);
        ironHorse1.setIngredient('=', Material.IRON_NUGGET);
        ironHorse1.setGroup("IRON HORSE ARMOR");

        ironHorse2.shape("@  ", "#-#", "= =");

        ironHorse2.setIngredient('#', Material.IRON_BLOCK);
        ironHorse2.setIngredient('@', Material.IRON_INGOT);
        ironHorse2.setIngredient('-', Material.SADDLE);
        ironHorse2.setIngredient('=', Material.IRON_NUGGET);
        ironHorse2.setGroup("IRON HORSE ARMOR");


        // Todo    GOLD HORSE ARMOR RECIPE
        ShapedRecipe goldHorse1 = new ShapedRecipe(new NamespacedKey(survival, "goldhorse1"), new ItemStack(Material.GOLDEN_HORSE_ARMOR, 1));
        ShapedRecipe goldHorse2 = new ShapedRecipe(new NamespacedKey(survival, "goldhorse2"), new ItemStack(Material.GOLDEN_HORSE_ARMOR, 1));

        goldHorse1.shape("  @", "#-#", "= =");

        goldHorse1.setIngredient('#', Material.GOLD_BLOCK);
        goldHorse1.setIngredient('@', Material.GOLD_INGOT);
        goldHorse1.setIngredient('-', Material.SADDLE);
        goldHorse1.setIngredient('=', Material.GOLD_NUGGET);
        goldHorse1.setGroup("GOLD HORSE ARMOR");

        goldHorse2.shape("@  ", "#-#", "= =");

        goldHorse2.setIngredient('#', Material.GOLD_BLOCK);
        goldHorse2.setIngredient('@', Material.GOLD_INGOT);
        goldHorse2.setIngredient('-', Material.SADDLE);
        goldHorse2.setIngredient('=', Material.GOLD_NUGGET);
        goldHorse2.setGroup("GOLD HORSE ARMOR");


        // Todo    DIAMOND HORSE ARMOR RECIPE
        ShapedRecipe diamondHorse1 = new ShapedRecipe(new NamespacedKey(survival, "diamondhorse1"), new ItemStack(Material.DIAMOND_HORSE_ARMOR, 1));
        ShapedRecipe diamondHorse2 = new ShapedRecipe(new NamespacedKey(survival, "diamondhorse2"), new ItemStack(Material.DIAMOND_HORSE_ARMOR, 1));

        diamondHorse1.shape("  H", "@-@", "B B");

        diamondHorse1.setIngredient('@', Material.DIAMOND);
        diamondHorse1.setIngredient('-', Material.IRON_HORSE_ARMOR);
        diamondHorse1.setIngredient('H', Material.DIAMOND_HELMET);
        diamondHorse1.setIngredient('B', Material.DIAMOND_BOOTS);
        diamondHorse1.setGroup("DIAMOND HORSE ARMOR");

        diamondHorse2.shape("H  ", "@-@", "B B");

        diamondHorse2.setIngredient('@', Material.DIAMOND);
        diamondHorse2.setIngredient('-', Material.IRON_HORSE_ARMOR);
        diamondHorse2.setIngredient('H', Material.DIAMOND_HELMET);
        diamondHorse2.setIngredient('B', Material.DIAMOND_BOOTS);
        diamondHorse2.setGroup("DIAMOND HORSE ARMOR");


        // Todo    CLAY BRICK RECIPE
        ShapelessRecipe clayBrick = new ShapelessRecipe(new NamespacedKey(survival, "claybrick"), new ItemStack(Material.BRICK, 4));

        clayBrick.addIngredient(Material.BRICK);


        // Todo    QUARTZ BLOCK RECIPE
        ShapelessRecipe quartz = new ShapelessRecipe(new NamespacedKey(survival, "quartz"), new ItemStack(Material.QUARTZ, 4));

        quartz.addIngredient(Material.QUARTZ_BLOCK);


        // Todo    STRING RECIPE
        ShapelessRecipe woolString = new ShapelessRecipe(new NamespacedKey(survival, "woolstring"), new ItemStack(Material.STRING, 4));

        woolString.addIngredient(new Wool(DyeColor.WHITE));


        // Todo    REPAIR RECIPE
        ShapedRecipe repair_gSword = new ShapedRecipe(new NamespacedKey(survival, "repair_gsword"), Items.get(Items.BLAZE_SWORD));
        repair_gSword.shape("123");

        repair_gSword.setIngredient('1', new RecipeChoice.ExactChoice(Utils.getItemStackDura(Items.BLAZE_SWORD, 31)));
        repair_gSword.setIngredient('2', Material.BLAZE_POWDER);
        repair_gSword.setIngredient('3', Material.BLAZE_POWDER);
        repair_gSword.setGroup("blaze sword");



        // Todo    REPAIR RECIPE
        ShapedRecipe repair_gHoe = new ShapedRecipe(new NamespacedKey(survival, "repair_ghoe2"), Items.get(Items.ENDER_GIANT_BLADE));
        repair_gHoe.shape("123");
        repair_gHoe.setIngredient('1', new RecipeChoice.ExactChoice(Utils.getItemStackDura(Items.ENDER_GIANT_BLADE, 31)));
        repair_gHoe.setIngredient('2', Material.ENDER_PEARL);
        repair_gHoe.setIngredient('3', Material.DIAMOND_BLOCK);


        // Todo    REPAIR RECIPE
        ShapedRecipe repair_gPickaxe = new ShapedRecipe(new NamespacedKey(survival, "repair_gpickaxe"), Items.get(Items.QUARTZ_PICKAXE));
        repair_gPickaxe.shape("123");
        repair_gPickaxe.setIngredient('1', new RecipeChoice.ExactChoice(Utils.getItemStackDura(Items.QUARTZ_PICKAXE, 31)));
        repair_gPickaxe.setIngredient('2', Material.QUARTZ_BLOCK);
        repair_gPickaxe.setIngredient('3', Material.DIAMOND_BLOCK);


        // Todo    REPAIR RECIPE
        ShapedRecipe repair_gAxe = new ShapedRecipe(new NamespacedKey(survival, "repair_gaxe"), Items.get(Items.VALKYRIES_AXE));
        repair_gAxe.shape("12 ");
        repair_gAxe.setIngredient('1', new RecipeChoice.ExactChoice(Utils.getItemStackDura(Items.VALKYRIES_AXE, 31)));
        repair_gAxe.setIngredient('2', Material.NETHER_STAR);


        // Todo    REPAIR RECIPE
        ShapedRecipe repair_gSpade = new ShapedRecipe(new NamespacedKey(survival, "repair_gspade"), Items.get(Items.OBSIDIAN_MACE));
        repair_gSpade.shape("12 ");
        repair_gSpade.setIngredient('1', new RecipeChoice.ExactChoice(Utils.getItemStackDura(Items.OBSIDIAN_MACE, 31)));
        repair_gSpade.setIngredient('2', Material.END_CRYSTAL);


        // Todo WORKBENCH RECIPE
        ShapelessRecipe workbench1 = new ShapelessRecipe(new NamespacedKey(survival, "workbench1"), Items.get(Items.WORKBENCH));

        workbench1.addIngredient(Material.OAK_LOG);
        workbench1.addIngredient(Material.LEATHER);
        workbench1.addIngredient(Material.STRING);
        workbench1.addIngredient(Material.WOODEN_SWORD);


        // Todo    FURNACE RECIPE
        ShapedRecipe furnace = new ShapedRecipe(new NamespacedKey(survival, "furnace"), new ItemStack(Material.FURNACE, 1));

        furnace.shape("@@@", "@*@", "@@@");

        furnace.setIngredient('@', Material.BRICK);
        furnace.setIngredient('*', Material.WOODEN_SHOVEL);


        // Todo    CHEST RECIPE
        ShapedRecipe chest = new ShapedRecipe(new NamespacedKey(survival, "chest"), new ItemStack(Material.CHEST, 1));

        chest.shape("@@@", "@#@", "@@@");

        chest.setIngredient('@', Material.OAK_LOG);
        chest.setIngredient('#', Material.IRON_INGOT);


        // Todo    CLAY RECIPE
        ShapelessRecipe clay = new ShapelessRecipe(new NamespacedKey(survival, "clay"), new ItemStack(Material.CLAY, 1));

        clay.addIngredient(Material.DIRT);
        clay.addIngredient(Material.SAND);
        clay.addIngredient(Material.BEETROOT_SOUP);


        // Todo    DIORITE RECIPE
        ShapelessRecipe diorite = new ShapelessRecipe(new NamespacedKey(survival, "diorite"), new ItemStack(Material.DIORITE, 1));

        diorite.addIngredient(Material.BONE_MEAL); // TODO Change in 1.14 -> WHITE_DYE
        diorite.addIngredient(Material.COBBLESTONE);


        // Todo    GRANITE RECIPE
        ShapelessRecipe granite = new ShapelessRecipe(new NamespacedKey(survival, "granite"), new ItemStack(Material.GRANITE, 1));

        granite.addIngredient(Material.NETHERRACK);
        granite.addIngredient(Material.COBBLESTONE);


        // Todo    ANDESITE RECIPE
        ShapelessRecipe andesite = new ShapelessRecipe(new NamespacedKey(survival, "andesite"), new ItemStack(Material.ANDESITE, 1));

        andesite.addIngredient(Material.GRAVEL);
        andesite.addIngredient(Material.COBBLESTONE);


        // Todo    GRAVEL RECIPE
        ShapedRecipe gravel1 = new ShapedRecipe(new NamespacedKey(survival, "gravel1"), new ItemStack(Material.GRAVEL, 2));
        ShapedRecipe gravel2 = new ShapedRecipe(new NamespacedKey(survival, "gravel2"), new ItemStack(Material.GRAVEL, 2));

        gravel1.shape("@B", "B@");

        gravel1.setIngredient('@', Material.SAND);
        gravel1.setIngredient('B', Material.COBBLESTONE);
        gravel1.setGroup("GRAVEL");

        gravel2.shape("B@", "@B");

        gravel2.setIngredient('@', Material.SAND);
        gravel2.setIngredient('B', Material.COBBLESTONE);
        gravel2.setGroup("GRAVEL");


        // Todo    FIRESSTRIKER RECIPE
        ShapelessRecipe firestriker1 = new ShapelessRecipe(new NamespacedKey(survival, "firestriker1"), Items.get(Items.FIRESTRIKER));
        ShapelessRecipe firestriker2 = new ShapelessRecipe(new NamespacedKey(survival, "firestriker2"), Items.get(Items.FIRESTRIKER));

        firestriker1.addIngredient(Material.FLINT);
        firestriker1.addIngredient(Material.COAL);
        firestriker1.setGroup("FIRESTRIKER");

        firestriker2.addIngredient(Material.FLINT);
        firestriker2.addIngredient(Material.CHARCOAL);
        firestriker2.setGroup("FIRESTRIKER");


        // Todo    TORCH RECIPE
        ShapedRecipe torch1 = new ShapedRecipe(new NamespacedKey(survival, "torch1"), new ItemStack(Material.TORCH, 8));
        ShapedRecipe torch2 = new ShapedRecipe(new NamespacedKey(survival, "torch2"), new ItemStack(Material.TORCH, 16));
        ShapedRecipe torch3 = new ShapedRecipe(new NamespacedKey(survival, "torch3"), new ItemStack(Material.TORCH, 16));

        torch1.shape("AAA", "ABA", "AAA");
        torch1.setIngredient('B', new RecipeChoice.ExactChoice(Items.get(Items.FIRESTRIKER)));
        torch1.setIngredient('A', Material.STICK);
        torch1.setGroup("TORCH");

        torch2.shape("ACA", "ABA", "AAA");
        torch2.setIngredient('C', Material.COAL);
        torch2.setIngredient('B', new RecipeChoice.ExactChoice(Items.get(Items.FIRESTRIKER)));
        torch2.setIngredient('A', Material.STICK);
        torch2.setGroup("TORCH");

        torch3.shape("ACA", "ABA", "AAA");
        torch3.setIngredient('C', Material.CHARCOAL);
        torch3.setIngredient('B', new RecipeChoice.ExactChoice(Items.get(Items.FIRESTRIKER)));
        torch3.setIngredient('A', Material.STICK);
        torch3.setGroup("TORCH");


        // Todo    FLINT RECIPE
        ShapelessRecipe flint = new ShapelessRecipe(new NamespacedKey(survival, "flint"), new ItemStack(Material.FLINT, 1));

        flint.addIngredient(Material.GRAVEL);


        // Todo    FERMENTED SPIDER EYE RECIPE
        ShapelessRecipe fermentedSpiderEye1 = new ShapelessRecipe(new NamespacedKey(survival, "fermentedspidereye1"),
                new ItemStack(Material.FERMENTED_SPIDER_EYE, 1));
        ShapelessRecipe fermentedSpiderEye2 = new ShapelessRecipe(new NamespacedKey(survival, "fermentedspidereye2"),
                new ItemStack(Material.FERMENTED_SPIDER_EYE, 1));

        fermentedSpiderEye1.addIngredient(Material.SPIDER_EYE);
        fermentedSpiderEye1.addIngredient(Material.SUGAR);
        fermentedSpiderEye1.addIngredient(Material.RED_MUSHROOM);
        fermentedSpiderEye1.setGroup("FERMENTED SPIDER EYE");

        fermentedSpiderEye2.addIngredient(Material.SPIDER_EYE);
        fermentedSpiderEye2.addIngredient(Material.SUGAR);
        fermentedSpiderEye2.addIngredient(Material.RED_MUSHROOM);
        fermentedSpiderEye2.setGroup("FERMENTED SPIDER EYE");


        // Todo    FERMENTED SKIN RECIPE
        ShapelessRecipe fermentedSkin1 = new ShapelessRecipe(new NamespacedKey(survival, "fermentedskin1"), Items.get(Items.FERMENTED_SKIN));
        ShapelessRecipe fermentedSkin2 = new ShapelessRecipe(new NamespacedKey(survival, "fermentedskin2"), Items.get(Items.FERMENTED_SKIN));

        fermentedSkin1.addIngredient(Material.ROTTEN_FLESH);
        fermentedSkin1.addIngredient(Material.SUGAR);
        fermentedSkin1.addIngredient(Material.BROWN_MUSHROOM);
        fermentedSkin1.setGroup("FERMENTED SKIN");

        fermentedSkin2.addIngredient(Material.ROTTEN_FLESH);
        fermentedSkin2.addIngredient(Material.SUGAR);
        fermentedSkin2.addIngredient(Material.RED_MUSHROOM);
        fermentedSkin2.setGroup("FERMENTED SKIN");


        // Todo    POISONOUS POTATO RECIPE
        ShapelessRecipe poisonousPotato = new ShapelessRecipe(new NamespacedKey(survival, "poisonouspotato"),
                new ItemStack(Material.POISONOUS_POTATO, 1));

        poisonousPotato.addIngredient(Material.POTATO);
        poisonousPotato.addIngredient(Material.BONE_MEAL); // TODO Add WHITE_DYE recipe in 1.14


        // Todo    GLASS BOTTLE RECIPE
        ShapelessRecipe glassBottle = new ShapelessRecipe(new NamespacedKey(survival, "glassbottle"), new ItemStack(Material.GLASS_BOTTLE, 1));

        glassBottle.addIngredient(Material.POTION);


        // Todo    BOWL RECIPE
        ShapelessRecipe bowl = new ShapelessRecipe(new NamespacedKey(survival, "bowl"), new ItemStack(Material.BOWL, 1));

        bowl.addIngredient(Material.BEETROOT_SOUP);


        // Todo    MEDIC KIT RECIPE
        ShapedRecipe medicKit1 = new ShapedRecipe(new NamespacedKey(survival, "medickit1"), Items.get(Items.MEDIC_KIT));
        ShapedRecipe medicKit2 = new ShapedRecipe(new NamespacedKey(survival, "medickit2"), Items.get(Items.MEDIC_KIT));
        ShapedRecipe medicKit3 = new ShapedRecipe(new NamespacedKey(survival, "medickit3"), Items.get(Items.MEDIC_KIT));
        ShapedRecipe medicKit4 = new ShapedRecipe(new NamespacedKey(survival, "medickit4"), Items.get(Items.MEDIC_KIT));
        ShapedRecipe medicKit5 = new ShapedRecipe(new NamespacedKey(survival, "medickit5"), Items.get(Items.MEDIC_KIT));
        ShapedRecipe medicKit6 = new ShapedRecipe(new NamespacedKey(survival, "medickit6"), Items.get(Items.MEDIC_KIT));

        medicKit1.shape(" @ ", "ABC", " @ ");

        medicKit1.setIngredient('@', Material.GOLD_INGOT);
        medicKit1.setIngredient('A', Material.FEATHER);
        medicKit1.setIngredient('B', Material.GLISTERING_MELON_SLICE);
        medicKit1.setIngredient('C', Material.PAPER);
        medicKit1.setGroup("MEDIC KIT");

        medicKit2.shape(" @ ", "ACB", " @ ");

        medicKit2.setIngredient('@', Material.GOLD_INGOT);
        medicKit2.setIngredient('A', Material.FEATHER);
        medicKit2.setIngredient('B', Material.GLISTERING_MELON_SLICE);
        medicKit2.setIngredient('C', Material.PAPER);
        medicKit2.setGroup("MEDIC KIT");

        medicKit3.shape(" @ ", "BAC", " @ ");

        medicKit3.setIngredient('@', Material.GOLD_INGOT);
        medicKit3.setIngredient('A', Material.FEATHER);
        medicKit3.setIngredient('B', Material.GLISTERING_MELON_SLICE);
        medicKit3.setIngredient('C', Material.PAPER);
        medicKit3.setGroup("MEDIC KIT");

        medicKit4.shape(" @ ", "BCA", " @ ");

        medicKit4.setIngredient('@', Material.GOLD_INGOT);
        medicKit4.setIngredient('A', Material.FEATHER);
        medicKit4.setIngredient('B', Material.GLISTERING_MELON_SLICE);
        medicKit4.setIngredient('C', Material.PAPER);
        medicKit4.setGroup("MEDIC KIT");

        medicKit5.shape(" @ ", "CAB", " @ ");

        medicKit5.setIngredient('@', Material.GOLD_INGOT);
        medicKit5.setIngredient('A', Material.FEATHER);
        medicKit5.setIngredient('B', Material.GLISTERING_MELON_SLICE);
        medicKit5.setIngredient('C', Material.PAPER);
        medicKit5.setGroup("MEDIC KIT");

        medicKit6.shape(" @ ", "CBA", " @ ");

        medicKit6.setIngredient('@', Material.GOLD_INGOT);
        medicKit6.setIngredient('A', Material.FEATHER);
        medicKit6.setIngredient('B', Material.GLISTERING_MELON_SLICE);
        medicKit6.setIngredient('C', Material.PAPER);
        medicKit6.setGroup("MEDIC KIT");


        // Todo    FISHING ROD RECIPE
        ShapedRecipe fishingRod1 = new ShapedRecipe(new NamespacedKey(survival, "fishingrod1"), new ItemStack(Material.FISHING_ROD, 1));
        ShapedRecipe fishingRod2 = new ShapedRecipe(new NamespacedKey(survival, "fishingrod2"), new ItemStack(Material.FISHING_ROD, 1));

        fishingRod1.shape("1- ", "1 -", "1@*");

        fishingRod1.setIngredient('1', Material.STICK);
        fishingRod1.setIngredient('@', Material.IRON_INGOT);
        fishingRod1.setIngredient('-', Material.STRING);
        fishingRod1.setIngredient('*', Material.FEATHER);
        fishingRod1.setGroup("FISHING ROD");

        fishingRod2.shape(" -1", "- 1", "*@1");

        fishingRod2.setIngredient('1', Material.STICK);
        fishingRod2.setIngredient('@', Material.IRON_INGOT);
        fishingRod2.setIngredient('-', Material.STRING);
        fishingRod2.setIngredient('*', Material.FEATHER);
        fishingRod2.setGroup("FISHING ROD");


        // Todo    IRON INGOT RECIPE
        ShapedRecipe ironIngot = new ShapedRecipe(new NamespacedKey(survival, "ironingot"), new ItemStack(Material.IRON_INGOT, 1));
        ironIngot.shape("@@", "@@");
        ironIngot.setIngredient('@', Material.IRON_NUGGET);

        // Todo    IRON NUGGET RECIPE
        ShapelessRecipe ironNugget = new ShapelessRecipe(new NamespacedKey(survival, "ironnugget"), new ItemStack(Material.IRON_NUGGET, 4));
        ironNugget.addIngredient(Material.IRON_INGOT);

        // Todo    IRON BLOCK RECIPE
        ShapelessRecipe ironBlock = new ShapelessRecipe(new NamespacedKey(survival, "ironblock"), new ItemStack(Material.IRON_INGOT, 9));
        ironBlock.addIngredient(Material.IRON_BLOCK);

        // Todo    GOLD INGOT RECIPE
        ShapedRecipe goldIngot = new ShapedRecipe(new NamespacedKey(survival, "goldingot"), new ItemStack(Material.GOLD_INGOT, 1));
        goldIngot.shape("@@", "@@");
        goldIngot.setIngredient('@', Material.GOLD_NUGGET);

        // Todo    GOLD NUGGET RECIPE
        ShapelessRecipe goldNugget = new ShapelessRecipe(new NamespacedKey(survival, "goldnugget"), new ItemStack(Material.GOLD_NUGGET, 4));
        goldNugget.addIngredient(Material.GOLD_INGOT);

        // Todo    GOLD BLOCK RECIPE
        ShapelessRecipe goldBlock = new ShapelessRecipe(new NamespacedKey(survival, "goldblock"), new ItemStack(Material.GOLD_INGOT, 9));
        goldBlock.addIngredient(Material.GOLD_BLOCK);

        // Todo    SMELTING RECIPES (what the ferk, why is this even here?)
        org.bukkit.inventory.FurnaceRecipe smelt_ironIngot = new org.bukkit.inventory.FurnaceRecipe(
                new ItemStack(Material.IRON_INGOT, 1), Material.IRON_ORE);
        org.bukkit.inventory.FurnaceRecipe smelt_goldIngot = new org.bukkit.inventory.FurnaceRecipe(
                new ItemStack(Material.GOLD_INGOT, 1), Material.GOLD_ORE);


        // Todo    BREAD RECIPE
        ShapedRecipe bread = new ShapedRecipe(new NamespacedKey(survival, "bread"), new ItemStack(Material.BREAD, 2));

        bread.shape(" E ", "WWW");

        bread.setIngredient('E', Material.EGG);
        bread.setIngredient('W', Material.WHEAT);


        // Todo    COOKIE RECIPE
        ShapedRecipe cookie = new ShapedRecipe(new NamespacedKey(survival, "cookie"), new ItemStack(Material.COOKIE, 8));

        cookie.shape(" E ", "WCW", " S ");

        cookie.setIngredient('E', Material.EGG);
        cookie.setIngredient('W', Material.WHEAT);
        cookie.setIngredient('S', Material.SUGAR);
        cookie.setIngredient('C', Material.COCOA_BEANS);


        // Todo    SLIME BALL RECIPE
        ShapelessRecipe slimeball = new ShapelessRecipe(new NamespacedKey(survival, "slimeball"), new ItemStack(Material.SLIME_BALL, 1));

        slimeball.addIngredient(Material.MILK_BUCKET);
        slimeball.addIngredient(8, Material.VINE);


        // Todo    COBWEB RECIPE
        ShapelessRecipe cobweb = new ShapelessRecipe(new NamespacedKey(survival, "cobweb"), new ItemStack(Material.COBWEB, 1));

        cobweb.addIngredient(Material.SLIME_BALL);
        cobweb.addIngredient(2, Material.STRING);


        // Todo    SAPLING RECIPE
        ShapelessRecipe sapling1 = new ShapelessRecipe(new NamespacedKey(survival, "sapling1"), new ItemStack(Material.STICK, 4));
        ShapelessRecipe sapling2 = new ShapelessRecipe(new NamespacedKey(survival, "sapling2"), new ItemStack(Material.STICK, 4));
        ShapelessRecipe sapling3 = new ShapelessRecipe(new NamespacedKey(survival, "sapling3"), new ItemStack(Material.STICK, 4));
        ShapelessRecipe sapling4 = new ShapelessRecipe(new NamespacedKey(survival, "sapling4"), new ItemStack(Material.STICK, 4));
        ShapelessRecipe sapling5 = new ShapelessRecipe(new NamespacedKey(survival, "sapling5"), new ItemStack(Material.STICK, 4));
        ShapelessRecipe sapling6 = new ShapelessRecipe(new NamespacedKey(survival, "sapling6"), new ItemStack(Material.STICK, 4));

        sapling1.addIngredient(Material.OAK_SAPLING);
        sapling1.setGroup("STICK");
        sapling2.addIngredient(Material.BIRCH_SAPLING);
        sapling2.setGroup("STICK");
        sapling3.addIngredient(Material.SPRUCE_SAPLING);
        sapling3.setGroup("STICK");
        sapling4.addIngredient(Material.JUNGLE_SAPLING);
        sapling4.setGroup("STICK");
        sapling5.addIngredient(Material.ACACIA_SAPLING);
        sapling5.setGroup("STICK");
        sapling6.addIngredient(Material.DARK_OAK_SAPLING);
        sapling6.setGroup("STICK");


        // Todo REINFORCED LEATHER BOOTS RECIPE
        ShapedRecipe leatherBoots = new ShapedRecipe(new NamespacedKey(survival, "leatherboots"), Items.get(Items.REINFORCED_LEATHER_BOOTS));
        leatherBoots.shape("@*@");

        leatherBoots.setIngredient('@', Material.IRON_INGOT);
        leatherBoots.setIngredient('*', Material.LEATHER_BOOTS);


        // Todo REINFORCED LEATHER TUNIC RECIPE
        ShapedRecipe leatherChestplate = new ShapedRecipe(new NamespacedKey(survival, "leatherchestplate"), Items.get(Items.REINFORCED_LEATHER_TUNIC));
        leatherChestplate.shape(" @ ", "@*@", " @ ");

        leatherChestplate.setIngredient('@', Material.IRON_INGOT);
        leatherChestplate.setIngredient('*', Material.LEATHER_CHESTPLATE);


        // Todo REINFORCED LEATHER TROUSERS RECIPE
        ShapedRecipe leatherLeggings = new ShapedRecipe(new NamespacedKey(survival, "leatherleggings"), Items.get(Items.REINFORCED_LEATHER_TROUSERS));
        leatherLeggings.shape(" @ ", "@*@", " @ ");

        leatherLeggings.setIngredient('@', Material.IRON_INGOT);
        leatherLeggings.setIngredient('*', Material.LEATHER_LEGGINGS);


        // Todo REINFORCED LEATHER HELMET RECIPE
        ShapedRecipe leatherHelmet = new ShapedRecipe(new NamespacedKey(survival, "leatherhelmet"), Items.get(Items.REINFORCED_LEATHER_HELMET));
        leatherHelmet.shape("@*@");

        leatherHelmet.setIngredient('@', Material.IRON_INGOT);
        leatherHelmet.setIngredient('*', Material.LEATHER_HELMET);


        // Todo GOLDEN SABATONS RECIPE
        ShapedRecipe goldBoots = new ShapedRecipe(new NamespacedKey(survival, "goldboots"), Items.get(Items.GOLDEN_SABATONS));
        goldBoots.shape("@ @", "@ @");

        goldBoots.setIngredient('@', Material.GOLD_INGOT);


        // Todo GOLDEN GUARD RECIPE
        ShapedRecipe goldChestplate = new ShapedRecipe(new NamespacedKey(survival, "goldchestplate"), Items.get(Items.GOLDEN_GUARD));
        goldChestplate.shape("@ @", "@@@", "@@@");

        goldChestplate.setIngredient('@', Material.GOLD_INGOT);


        // Todo GOLDEN GREAVES RECIPE
        ShapedRecipe goldLeggings = new ShapedRecipe(new NamespacedKey(survival, "goldleggings"), Items.get(Items.GOLDEN_GREAVES));
        goldLeggings.shape("@@@", "@ @", "@ @");

        goldLeggings.setIngredient('@', Material.GOLD_INGOT);


        // Todo GOLDEN CROWN RECIPE
        ShapedRecipe goldHelmet = new ShapedRecipe(new NamespacedKey(survival, "goldhelmet"), Items.get(Items.GOLDEN_CROWN));
        goldHelmet.shape("@*@", "@@@");

        goldHelmet.setIngredient('@', Material.GOLD_INGOT);
        goldHelmet.setIngredient('*', Material.EMERALD);


        // Todo IRON BOOTS RECIPE
        ShapedRecipe ironBoots = new ShapedRecipe(new NamespacedKey(survival, "ironboots"), Items.get(Items.IRON_BOOTS));
        ironBoots.shape("@ @", "@ @");

        ironBoots.setIngredient('@', Material.IRON_INGOT);


        // Todo IRON CHESTPLATE RECIPE
        ShapedRecipe ironChestplate = new ShapedRecipe(new NamespacedKey(survival, "ironchestplate"), Items.get(Items.IRON_CHESTPLATE));
        ironChestplate.shape("@ @", "@@@", "@@@");

        ironChestplate.setIngredient('@', Material.IRON_INGOT);


        // Todo IRON LEGGINGS RECIPE
        ShapedRecipe ironLeggings = new ShapedRecipe(new NamespacedKey(survival, "ironleggings"), Items.get(Items.IRON_LEGGINGS));
        ironLeggings.shape("@@@", "@ @", "@ @");

        ironLeggings.setIngredient('@', Material.IRON_INGOT);


        // Todo IRON HELMET RECIPE
        ShapedRecipe ironHelmet = new ShapedRecipe(new NamespacedKey(survival, "ironhelmet"), Items.get(Items.IRON_HELMET));
        ironHelmet.shape("@@@", "@ @");

        ironHelmet.setIngredient('@', Material.IRON_INGOT);


        // Todo DIAMOND BOOTS RECIPE
        ShapedRecipe diamondBoots = new ShapedRecipe(new NamespacedKey(survival, "diamondboots"), Items.get(Items.DIAMOND_BOOTS));
        diamondBoots.shape("@ @", "@ @");

        diamondBoots.setIngredient('@', Material.DIAMOND);


        // Todo DIAMOND CHESTPLATE RECIPE
        ShapedRecipe diamondChestplate = new ShapedRecipe(new NamespacedKey(survival, "diamondchestplate"), Items.get(Items.DIAMOND_CHESTPLATE));
        diamondChestplate.shape("@ @", "@@@", "@@@");

        diamondChestplate.setIngredient('@', Material.DIAMOND);


        // Todo DIAMOND LEGGINGS RECIPE
        ShapedRecipe diamondLeggings = new ShapedRecipe(new NamespacedKey(survival, "diamondleggings"), Items.get(Items.DIAMOND_LEGGINGS));
        diamondLeggings.shape("@@@", "@ @", "@ @");

        diamondLeggings.setIngredient('@', Material.DIAMOND);


        // Todo DIAMOND HELMET RECIPE
        ShapedRecipe diamondHelmet = new ShapedRecipe(new NamespacedKey(survival, "diamondhelmet"), Items.get(Items.DIAMOND_HELMET));
        diamondHelmet.shape("@@@", "@ @");

        diamondHelmet.setIngredient('@', Material.DIAMOND);


        // Todo RECURVE BOW RECIPE
        ShapedRecipe recurveBow1 = new ShapedRecipe(new NamespacedKey(survival, "recurvebow1"), Items.get(Items.RECURVE_BOW));
        ShapedRecipe recurveBow2 = new ShapedRecipe(new NamespacedKey(survival, "recurvebow2"), Items.get(Items.RECURVE_BOW));

        recurveBow1.shape(" @1", "#^1", " @1");
        recurveBow1.setIngredient('^', Material.BOW);
        recurveBow1.setIngredient('#', Material.PISTON);
        recurveBow1.setIngredient('@', Material.IRON_INGOT);
        recurveBow1.setIngredient('1', Material.STRING);

        recurveBow2.shape("1@ ", "1^#", "1@ ");

        recurveBow2.setIngredient('^', Material.BOW);
        recurveBow2.setIngredient('#', Material.OAK_LOG);
        recurveBow2.setIngredient('@', Material.IRON_INGOT);
        recurveBow2.setIngredient('1', Material.STRING);


        //Add recipes
        if (settings.getBoolean("Survival.Enabled")) {
            survival.getServer().addRecipe(hatchet1);
            survival.getServer().addRecipe(hatchet2);
            survival.getServer().addRecipe(mattock1);
            survival.getServer().addRecipe(mattock2);
            survival.getServer().addRecipe(mattock3);
            survival.getServer().addRecipe(mattock4);
            survival.getServer().addRecipe(mattock5);
            survival.getServer().addRecipe(mattock6);
            survival.getServer().addRecipe(shiv1);
            survival.getServer().addRecipe(shiv2);
            survival.getServer().addRecipe(shiv3);
            survival.getServer().addRecipe(shiv4);
            survival.getServer().addRecipe(hammer1);
            survival.getServer().addRecipe(hammer2);
            survival.getServer().addRecipe(firestriker1);
            survival.getServer().addRecipe(firestriker2);
            survival.getServer().addRecipe(workbench1);
            survival.getServer().addRecipe(furnace);
            survival.getServer().addRecipe(chest);
            survival.getServer().addRecipe(flint);
        }
        if (settings.getBoolean("Survival.Torch")) {
            survival.getServer().addRecipe(torch1);
            survival.getServer().addRecipe(torch2);
            survival.getServer().addRecipe(torch3);
        }
        if (settings.getBoolean("Recipes.WebString"))
            survival.getServer().addRecipe(string);
        if (settings.getBoolean("Recipes.SaplingToSticks")) {
            survival.getServer().addRecipe(sapling1);
            survival.getServer().addRecipe(sapling2);
            survival.getServer().addRecipe(sapling3);
            survival.getServer().addRecipe(sapling4);
            survival.getServer().addRecipe(sapling5);
            survival.getServer().addRecipe(sapling6);
        }

        if (settings.getBoolean("LegendaryItems.ValkyrieAxe")) {
            survival.getServer().addRecipe(gAxe);
            if (settings.getBoolean("LegendaryItems.CanRepair"))
                survival.getServer().addRecipe(repair_gAxe);
        }
        if (settings.getBoolean("LegendaryItems.QuartzPickaxe")) {
            survival.getServer().addRecipe(gPickaxe1);
            survival.getServer().addRecipe(gPickaxe2);
            if (settings.getBoolean("LegendaryItems.CanRepair"))
                survival.getServer().addRecipe(repair_gPickaxe);
        }
        if (settings.getBoolean("LegendaryItems.ObsidianMace")) {
            survival.getServer().addRecipe(gSpade1);
            survival.getServer().addRecipe(gSpade2);
            if (settings.getBoolean("LegendaryItems.CanRepair"))
                survival.getServer().addRecipe(repair_gSpade);
        }
        if (settings.getBoolean("LegendaryItems.GiantBlade")) {
            survival.getServer().addRecipe(gHoe1);
            survival.getServer().addRecipe(gHoe2);
            if (settings.getBoolean("LegendaryItems.CanRepair"))
                survival.getServer().addRecipe(repair_gHoe);
        }
        if (settings.getBoolean("LegendaryItems.BlazeSword")) {
            survival.getServer().addRecipe(gSword);
            if (settings.getBoolean("LegendaryItems.CanRepair"))
                survival.getServer().addRecipe(repair_gSword);
        }
        if (settings.getBoolean("LegendaryItems.NotchApple"))
            survival.getServer().addRecipe(notchApple);
        if (settings.getBoolean("Recipes.Saddle"))
            survival.getServer().addRecipe(saddle);
        if (settings.getBoolean("Recipes.Nametag")) {
            survival.getServer().addRecipe(nametag1);
            survival.getServer().addRecipe(nametag2);
        }
        if (settings.getBoolean("Recipes.PackedIce")) {
            survival.getServer().addRecipe(packedIce1);
            survival.getServer().addRecipe(packedIce2);
        }
        if (settings.getBoolean("Recipes.IronBard")) {
            survival.getServer().addRecipe(ironHorse1);
            survival.getServer().addRecipe(ironHorse2);
        }
        if (settings.getBoolean("Recipes.GoldBard")) {
            survival.getServer().addRecipe(goldHorse1);
            survival.getServer().addRecipe(goldHorse2);
        }
        if (settings.getBoolean("Recipes.DiamondBard")) {
            survival.getServer().addRecipe(diamondHorse1);
            survival.getServer().addRecipe(diamondHorse2);
        }
        if (settings.getBoolean("Recipes.ClayBrick"))
            survival.getServer().addRecipe(clayBrick);
        if (settings.getBoolean("Recipes.QuartzBlock"))
            survival.getServer().addRecipe(quartz);
        if (settings.getBoolean("Recipes.WoolString"))
            survival.getServer().addRecipe(woolString);
        if (settings.getBoolean("Recipes.Ice"))
            survival.getServer().addRecipe(ice);
        if (settings.getBoolean("Recipes.Clay"))
            survival.getServer().addRecipe(clay);
        if (settings.getBoolean("Recipes.Diorite"))
            survival.getServer().addRecipe(diorite);
        if (settings.getBoolean("Recipes.Granite"))
            survival.getServer().addRecipe(granite);
        if (settings.getBoolean("Recipes.Andesite"))
            survival.getServer().addRecipe(andesite);
        if (settings.getBoolean("Recipes.Gravel")) {
            survival.getServer().addRecipe(gravel1);
            survival.getServer().addRecipe(gravel2);
        }
        if (settings.getBoolean("Mechanics.RedMushroomFermentation")) {
            survival.getServer().addRecipe(fermentedSpiderEye1);
            survival.getServer().addRecipe(fermentedSpiderEye2);
        }
        if (settings.getBoolean("Mechanics.FermentedSkin")) {
            survival.getServer().addRecipe(fermentedSkin1);
            if (settings.getBoolean("Mechanics.RedMushroomFermentation"))
                survival.getServer().addRecipe(fermentedSkin2);
        }
        if (settings.getBoolean("Mechanics.PoisonousPotato"))
            survival.getServer().addRecipe(poisonousPotato);
        if (settings.getBoolean("Mechanics.EmptyPotions")) {
            survival.getServer().addRecipe(glassBottle);
            survival.getServer().addRecipe(bowl);
        }
        if (settings.getBoolean("Mechanics.ReinforcedLeatherArmor")) {
            survival.getServer().addRecipe(leatherBoots);
            survival.getServer().addRecipe(leatherChestplate);
            survival.getServer().addRecipe(leatherLeggings);
            survival.getServer().addRecipe(leatherHelmet);
        }
        if (settings.getBoolean("LegendaryItems.GoldArmorBuff")) {
            survival.getServer().addRecipe(goldBoots);
            survival.getServer().addRecipe(goldChestplate);
            survival.getServer().addRecipe(goldLeggings);
            survival.getServer().addRecipe(goldHelmet);
        }

        if (settings.getBoolean("Mechanics.SlowArmor")) {
            survival.getServer().addRecipe(ironBoots);
            survival.getServer().addRecipe(ironChestplate);
            survival.getServer().addRecipe(ironLeggings);
            survival.getServer().addRecipe(ironHelmet);
            survival.getServer().addRecipe(diamondBoots);
            survival.getServer().addRecipe(diamondChestplate);
            survival.getServer().addRecipe(diamondLeggings);
            survival.getServer().addRecipe(diamondHelmet);
        }

        if (settings.getBoolean("Mechanics.MedicalKit")) {
            survival.getServer().addRecipe(medicKit1);
            survival.getServer().addRecipe(medicKit2);
            survival.getServer().addRecipe(medicKit3);
            survival.getServer().addRecipe(medicKit4);
            survival.getServer().addRecipe(medicKit5);
            survival.getServer().addRecipe(medicKit6);
        }

        if (settings.getBoolean("Recipes.FishingRod")) {
            survival.getServer().addRecipe(fishingRod1);
            survival.getServer().addRecipe(fishingRod2);
        }

        if (settings.getBoolean("Mechanics.ReducedIronNugget")) {
            survival.getServer().addRecipe(ironNugget);
            survival.getServer().addRecipe(ironIngot);
            survival.getServer().addRecipe(ironBlock);
            survival.getServer().addRecipe(smelt_ironIngot);
        }

        if (settings.getBoolean("Mechanics.ReducedGoldNugget")) {
            survival.getServer().addRecipe(goldNugget);
            survival.getServer().addRecipe(goldIngot);
            survival.getServer().addRecipe(goldBlock);
            survival.getServer().addRecipe(smelt_goldIngot);
        }

        if (settings.getBoolean("Mechanics.FarmingProducts.Bread"))
            survival.getServer().addRecipe(bread);
        if (settings.getBoolean("Mechanics.FarmingProducts.Cookie"))
            survival.getServer().addRecipe(cookie);
        if (settings.getBoolean("Recipes.Slimeball"))
            survival.getServer().addRecipe(slimeball);
        if (settings.getBoolean("Recipes.Cobweb"))
            survival.getServer().addRecipe(cobweb);
        if (settings.getBoolean("Mechanics.RecurveBow")) {
            survival.getServer().addRecipe(recurveBow1);
            survival.getServer().addRecipe(recurveBow2);
        }
    }

    private void removeRecipes() {
        List<Recipe> backup = new ArrayList<>();

        Iterator<Recipe> a = survival.getServer().recipeIterator();

        while (a.hasNext()) {
            Recipe recipe = a.next();
            backup.add(recipe);
        }

        Iterator<Recipe> it = backup.iterator();

        while (it.hasNext()) {
            Recipe recipe = it.next();
            if (recipe != null) {
                switch (recipe.getResult().getType()) {
                    case WOODEN_HOE:
                    case WOODEN_AXE:
                    case WOODEN_PICKAXE:
                    case WOODEN_SHOVEL:
                    case WOODEN_SWORD:
                    case FURNACE:
                    case CRAFTING_TABLE:
                    case CHEST:
                    case BEETROOT_SOUP:
                        if (settings.getBoolean("Survival.Enabled"))
                            it.remove();
                        break;
                    case TORCH:
                        if (settings.getBoolean("Survival.Enabled") && settings.getBoolean("Survival.Torch"))
                            it.remove();
                        break;

                    case GOLDEN_HOE:
                        if (settings.getBoolean("LegendaryItems.GiantBlade"))
                            it.remove();
                        break;
                    case GOLDEN_AXE:
                        if (settings.getBoolean("LegendaryItems.ValkyrieAxe"))
                            it.remove();
                        break;
                    case GOLDEN_PICKAXE:
                        if (settings.getBoolean("LegendaryItems.QuartzPickaxe"))
                            it.remove();
                        break;
                    case GOLDEN_SHOVEL:
                        if (settings.getBoolean("LegendaryItems.ObsidianMace"))
                            it.remove();
                        break;
                    case GOLDEN_SWORD:
                        if (settings.getBoolean("LegendaryItems.BlazeSword"))
                            it.remove();
                        break;

                    case GOLDEN_BOOTS:
                    case GOLDEN_CHESTPLATE:
                    case GOLDEN_HELMET:
                    case GOLDEN_LEGGINGS:
                        if (settings.getBoolean("LegendaryItems.GoldArmorBuff"))
                            it.remove();
                        break;

                    case IRON_BOOTS:
                    case IRON_CHESTPLATE:
                    case IRON_HELMET:
                    case IRON_LEGGINGS:
                    case DIAMOND_BOOTS:
                    case DIAMOND_CHESTPLATE:
                    case DIAMOND_HELMET:
                    case DIAMOND_LEGGINGS:
                        if (settings.getBoolean("Mechanics.SlowArmor"))
                            it.remove();
                        break;

                    case FISHING_ROD:
                        if (settings.getBoolean("Recipes.FishingRod"))
                            it.remove();
                        break;

                    case IRON_NUGGET:
                    case IRON_INGOT:
                        if (settings.getBoolean("Mechanics.ReducedIronNugget"))
                            it.remove();
                        break;

                    case GOLD_NUGGET:
                    case GOLD_INGOT:
                        if (settings.getBoolean("Mechanics.ReducedGoldNugget"))
                            it.remove();
                        break;

                    case BREAD:
                        if (settings.getBoolean("Mechanics.FarmingProducts.Bread"))
                            it.remove();
                        break;
                    case COOKIE:
                        if (settings.getBoolean("Mechanics.FarmingProducts.Cookie"))
                            it.remove();
                        break;
                    case ANDESITE:
                        if (settings.getBoolean("Recipes.Andesite"))
                            it.remove();
                        break;
                    case DIORITE:
                        if (settings.getBoolean("Recipes.Diorite"))
                            it.remove();
                        break;
                    case GRANITE:
                        if (settings.getBoolean("Recipes.Granite"))
                            it.remove();
                        break;
                    case SNOW:
                    case SNOW_BLOCK:
                        if (settings.getBoolean("Mechanics.SnowballRevamp"))
                            it.remove();
                        break;
                    case CLOCK:
                        if (settings.getBoolean("Mechanics.MedicalKit"))
                            it.remove();
                    default:
                }
            }
        }

        survival.getServer().clearRecipes();

        for (Recipe r : backup) {
            if (r.getResult().getType() != Material.AIR)
                survival.getServer().addRecipe(r);
        }
    }

}
