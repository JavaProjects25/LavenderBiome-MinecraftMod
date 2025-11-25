package sm.lavenderbiome.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import sm.lavenderbiome.LavenderBiome;
import sm.lavenderbiome.item.custom.LavenderLatteItem;
import sm.lavenderbiome.item.custom.ModConsumableComponents;
import sm.lavenderbiome.item.custom.ModFoodComponents;

public class ModItems {

    // Register a new Item called "lavendrite", which is an instance of the Item class with default settings.
    // // Lavendrite is a mineral item found from Lavendrite Ore in the Lavender Biome.
    // // static final modifier is like a const in C++/C#.
    public static final Item LAVENDRITE_INGOT = registerItem("lavendrite_ingot",
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LavenderBiome.MOD_ID, "lavendrite_ingot"))));

    public static final Item RAW_LAVENDRITE = registerItem("raw_lavendrite",
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LavenderBiome.MOD_ID, "raw_lavendrite"))));

    public static final Item LAVENDER_LATTE = registerItem("lavender_latte",
            new LavenderLatteItem
                    .Settings().rarity(Rarity.RARE)
                    .maxCount(16)
                    .food(ModFoodComponents.LAVENDER_LATTE, ModConsumableComponents.LAVENDER_LATTE)
                    .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
                    .recipeRemainder(Items.GLASS_BOTTLE)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LavenderBiome.MOD_ID, "lavender_latte"))));
            //(new Item.Settings().rarity(Rarity.RARE).food(ModFoodComponents.LAVENDER_LATTE, ModConsumableComponents.LAVENDER_LATTE).component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)));

    // Helper method to register items.
    // // A helper method is used to reduce code duplication when registering multiple items.
    // // How it works is that it takes in the name of the item and the item instance,
    // // then it registers the item in the Minecraft item registry using the provided name and instance.
    private static Item registerItem(String name, Item.Settings itemSettings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LavenderBiome.MOD_ID, name));
        Item item = new Item(itemSettings.registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }

    // Method to register all mod items.
    public static void registerModItems() {

        LavenderBiome.LOGGER.info("Registering Mod Items for: " + LavenderBiome.MOD_ID);

        // Add the LAVENDRITE item to the INGREDIENTS item group.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(LAVENDRITE_INGOT);
            entries.add(RAW_LAVENDRITE);
            entries.add(LAVENDER_LATTE);
        });
    }
}
