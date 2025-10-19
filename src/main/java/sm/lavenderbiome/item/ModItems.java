package sm.lavenderbiome.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import sm.lavenderbiome.LavenderBiome;

public class ModItems {

    // Register a new Item called "lavendrite", which is an instance of the Item class with default settings.
    // // Lavendrite is a mineral item found from Lavendrite Ore in the Lavender Biome.
    // // static final modifier is like a const in C++/C#.
    public static final Item LAVENDRITE =
            registerItem("lavendrite", new Item(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(LavenderBiome.MOD_ID, "lavendrite")))));

    // Helper method to register items.
    // // A helper method is used to reduce code duplication when registering multiple items.
    // // How it works is that it takes in the name of the item and the item instance,
    // // then it registers the item in the Minecraft item registry using the provided name and instance.
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(LavenderBiome.MOD_ID, name), item);
    }

    // Method to register all mod items.
    public static void registerModItems() {

        LavenderBiome.LOGGER.info("Registering Mod Items for: " + LavenderBiome.MOD_ID);

        // Add the LAVENDRITE item to the INGREDIENTS item group.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(LAVENDRITE);
        });
    }
}
