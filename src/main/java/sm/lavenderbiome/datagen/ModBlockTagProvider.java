package sm.lavenderbiome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import sm.lavenderbiome.block.ModBlocks;
import sm.lavenderbiome.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider  {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.LAVENDRITE_BLOCK)
                .add(ModBlocks.RAW_LAVENDRITE_BLOCK)
                .add(ModBlocks.LAVENDRITE_ORE)
                .add(ModBlocks.DEEPSLATE_LAVENDRITE_ORE);

        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.RAW_LAVENDRITE_BLOCK)
                .add(ModBlocks.DEEPSLATE_LAVENDRITE_ORE)
                ;

        valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.LAVENDRITE_ORE);

        valueLookupBuilder(ModTags.Blocks.NEEDS_LAVENDRITE_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL);


    }
}
