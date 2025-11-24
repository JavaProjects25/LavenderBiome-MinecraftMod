package sm.lavenderbiome;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import sm.lavenderbiome.block.ModBlocks;
import sm.lavenderbiome.item.ModItems;

public class ModGroups {

    public static final ItemGroup LAVENDER_BIOME_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(LavenderBiome.MOD_ID, "lavender_biome_group"),
            FabricItemGroup.builder().icon(() ->new ItemStack(ModBlocks.LAVENDRITE_BLOCK))
                    .displayName(Text.translatable("itemgroup.lavender_biome.lavender_biome_group"))
                    .entries(((displayContext, entries) -> {
                        entries.add(ModItems.LAVENDRITE_INGOT);
                        entries.add(ModItems.RAW_LAVENDRITE);
                        entries.add(ModBlocks.LAVENDRITE_BLOCK);
                        entries.add(ModBlocks.LAVENDRITE_ORE);
                        entries.add(ModBlocks.RAW_LAVENDRITE_BLOCK);
                    }
                    ))
                    .build());

    public static void registerItemGroups() {
        LavenderBiome.LOGGER.info("Registering Mod Item Groups for: " + LavenderBiome.MOD_ID);
    }
}
