package sm.lavenderbiome.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import sm.lavenderbiome.block.ModBlocks;
import sm.lavenderbiome.block.custom.LavenderCropBlock;
import sm.lavenderbiome.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        //general blocks
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LAVENDRITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_LAVENDRITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LAVENDRITE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_LAVENDRITE_ORE);

        blockStateModelGenerator.registerTintableCross(ModBlocks.LAVENDER, BlockStateModelGenerator.CrossType.NOT_TINTED);

        // Food and plants
        blockStateModelGenerator.registerCrop(ModBlocks.LAVENDER_CROP, LavenderCropBlock.AGE, 0,1,2,3);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //general items
        itemModelGenerator.register(ModItems.LAVENDRITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.LAVENDER_LATTE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_LAVENDRITE, Models.GENERATED);

        //tools and combat
        itemModelGenerator.register(ModItems.LAVENDRITE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LAVENDRITE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LAVENDRITE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LAVENDRITE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.LAVENDRITE_HOE, Models.HANDHELD);
        //custom tools
        itemModelGenerator.register(ModItems.LAVENDRITE_CROSSPICK, Models.HANDHELD);

        //food and plants
        //itemModelGenerator.register(ModBlocks.LAVENDER.asItem(), Models.GENERATED);
        //itemModelGenerator.register(ModItems.LAVENDER, Models.GENERATED);
        //itemModelGenerator.register(ModItems.LAVENDER_SEEDS, Models.GENERATED); // THIS WAS CAUSING ISSUES SAYING Duplicate model definition for lavenderbiome:item/lavender_seeds SO I COMMENTED IT OUT

    }
}
