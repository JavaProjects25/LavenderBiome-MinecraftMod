package sm.lavenderbiome.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import sm.lavenderbiome.block.ModBlocks;
import sm.lavenderbiome.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LAVENDRITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_LAVENDRITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LAVENDRITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_LAVENDRITE_ORE);
    }



    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.LAVENDRITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.LAVENDER_LATTE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_LAVENDRITE, Models.GENERATED);
    }
}
