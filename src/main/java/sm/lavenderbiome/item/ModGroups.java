package sm.lavenderbiome.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import sm.lavenderbiome.LavenderBiome;
import sm.lavenderbiome.block.ModBlocks;

public class ModGroups {

    public static final ItemGroup LAVENDER_BIOME_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(LavenderBiome.MOD_ID, "lavender_biome_group"),
            FabricItemGroup.builder().icon(() ->new ItemStack(ModBlocks.LAVENDRITE_BLOCK))
                    .displayName(Text.translatable("itemgroup.lavender_biome.lavender_biome_group"))
                    .entries(((displayContext, entries) -> {
                        // -- INGREDIENTS --
                        entries.add(ModItems.LAVENDRITE_INGOT);
                        entries.add(ModItems.RAW_LAVENDRITE);
                        entries.add(ModItems.LAVENDER_SEEDS);

                        // -- BLOCKS --
                            // Mineral Blocks
                        entries.add(ModBlocks.LAVENDRITE_BLOCK);
                        entries.add(ModBlocks.LAVENDRITE_ORE);
                        entries.add(ModBlocks.DEEPSLATE_LAVENDRITE_ORE);
                        entries.add(ModBlocks.RAW_LAVENDRITE_BLOCK);
                            // Wood Blocks
                        entries.add(ModBlocks.LAVENDERWOOD_LOG);
                        entries.add(ModBlocks.STRIPPED_LAVENDERWOOD_LOG);
                        entries.add(ModBlocks.LAVENDERWOOD_WOOD);
                        entries.add(ModBlocks.STRIPPED_LAVENDERWOOD_WOOD);
                        entries.add(ModBlocks.LAVENDERWOOD_PLANKS);

                        // -- NATURAL --
                        entries.add(ModBlocks.LAVENDER);
                        entries.add(ModBlocks.LAVENDERWOOD_LEAVES);

                        // -- FOOD AND DRINK --
                        entries.add(ModItems.LAVENDER_LATTE);

                        // -- TOOLS --
                        entries.add(ModItems.LAVENDRITE_SWORD);
                        entries.add(ModItems.LAVENDRITE_PICKAXE);
                        entries.add(ModItems.LAVENDRITE_AXE);
                        entries.add(ModItems.LAVENDRITE_SHOVEL);
                        entries.add(ModItems.LAVENDRITE_HOE);

                        // -- CUSTOM TOOLS --
                        entries.add(ModItems.LAVENDRITE_CROSSPICK);

                    })).build());

    public static void registerItemGroups() {
        LavenderBiome.LOGGER.info("Registering Mod Item Groups for: " + LavenderBiome.MOD_ID);
    }
}
