package sm.lavenderbiome.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.*;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import sm.lavenderbiome.LavenderBiome;
import sm.lavenderbiome.block.ModBlocks;
import sm.lavenderbiome.item.custom.CrosspickItem;

import java.util.function.Function;

public class ModItems {

    // Register a new Item called "lavendrite", which is an instance of the Item class with default settings.
    // // Lavendrite is a mineral item found from Lavendrite Ore in the Lavender Biome.
    // // static final modifier is like a const in C++/C#.

    // minerals
    public static final Item LAVENDRITE_INGOT = registerItem("lavendrite_ingot", Item::new);
    //new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LavenderBiome.MOD_ID, "lavendrite_ingot"))));

    public static final Item RAW_LAVENDRITE = registerItem("raw_lavendrite", Item::new);
    //new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LavenderBiome.MOD_ID, "raw_lavendrite"))));


    //food
    /*public static final Item LAVENDER_LATTE = registerCustomItem("lavender_latte",
            LavenderLatteItem::new,
    (new Item.Settings()
            .rarity(Rarity.RARE)
            .maxCount(16)
            .food(ModFoodComponents.LAVENDER_LATTE, ModConsumableComponents.LAVENDER_LATTE)
            .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LavenderBiome.MOD_ID, "lavender_latte")))));*/ //

    public static final Item LAVENDER_LATTE = registerCustomItem("lavender_latte", setting -> new Item(setting
            .food(ModFoodComponents.LAVENDER_LATTE, ModConsumableComponents.LAVENDER_LATTE)
            .maxCount(16)
            .rarity(Rarity.RARE)
            .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true))
    );

    // Plants
    public static final Item LAVENDER = registerItem("lavender", Item::new);

    //tools
    public static final Item LAVENDRITE_SWORD = registerItem("lavendrite_sword",
            settings -> new Item(settings.sword(ModToolMaterials.LAVENDRITE, 3.0F, -0.1f)));
    public static final Item LAVENDRITE_PICKAXE = registerItem("lavendrite_pickaxe",
            settings -> new Item(settings.pickaxe(ModToolMaterials.LAVENDRITE, 1.0F, -0.1f)));
    public static final Item LAVENDRITE_AXE = registerItem("lavendrite_axe",
            settings -> new AxeItem(ModToolMaterials.LAVENDRITE, 6.0F, -1.0f, settings));
    public static final Item LAVENDRITE_SHOVEL = registerItem("lavendrite_shovel",
            settings -> new ShovelItem(ModToolMaterials.LAVENDRITE, 1.5F, -0.1f, settings));
    public static final Item LAVENDRITE_HOE = registerItem("lavendrite_hoe",
            settings -> new HoeItem(ModToolMaterials.LAVENDRITE, -3.0F, -0.0f, settings));

    //custom tools
    public static final Item LAVENDRITE_CROSSPICK = registerItem("lavendrite_crosspick",
            settings -> new CrosspickItem(ModToolMaterials.LAVENDRITE, 7.0F, -4.0f, settings));

    //seeds
    public static final Item LAVENDER_SEEDS = registerItem("lavender_seeds", createBlockItemWithUniqueName(Block.)

    public static final Item WHEAT_SEEDS = register("wheat_seeds", createBlockItemWithUniqueName(Blocks.WHEAT));
    private static Function<Item.Settings, Item> createBlockItemWithUniqueName(ModBlocks block) {
        return settings -> new BlockItem(block, settings.useItemPrefixedTranslationKey());
    }

    // Helper method to register items.
    // // How it works is that it takes in the name of the item and the item instance,
    // // then it registers the item in the Minecraft item registry using the provided name and instance.
    /*private static Item registerItem(String name, Item.Settings itemSettings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LavenderBiome.MOD_ID, name));
        Item item = new Item(itemSettings.registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }*/ // ^ OLD WAY

    private static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(LavenderBiome.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LavenderBiome.MOD_ID, name)))));
    }

    // New helper method for registering custom item classes.
    private static <T extends Item> T registerCustomItem(String name, Function<Item.Settings, T> factory) {
        return Registry.register(Registries.ITEM, Identifier.of(LavenderBiome.MOD_ID, name),
                factory.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LavenderBiome.MOD_ID, name)))));
    }



    // Method to register all mod items.
    public static void registerModItems() {

        LavenderBiome.LOGGER.info("Registering Mod Items for: " + LavenderBiome.MOD_ID);

        // Add items to the vanilla item groups.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(LAVENDRITE_INGOT);
            entries.add(RAW_LAVENDRITE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(LAVENDER_LATTE);
            entries.add(LAVENDER_SEEDS);
            entries.add(LAVENDER);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(LAVENDRITE_SWORD);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(LAVENDRITE_PICKAXE);
            entries.add(LAVENDRITE_AXE);
            entries.add(LAVENDRITE_SHOVEL);
            entries.add(LAVENDRITE_HOE);
            entries.add(LAVENDRITE_CROSSPICK);
        });
    }
}
