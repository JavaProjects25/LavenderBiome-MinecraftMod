package sm.lavenderbiome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import org.jetbrains.annotations.Nullable;
import sm.lavenderbiome.block.ModBlocks;
import sm.lavenderbiome.item.ModItems;
import sm.lavenderbiome.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(ModTags.Items.LAVENDRITE_REPAIR)
                .add(ModItems.LAVENDRITE_INGOT);

        //tools
        valueLookupBuilder(ItemTags.SWORDS)
                .add(ModItems.LAVENDRITE_SWORD);
        valueLookupBuilder(ItemTags.PICKAXES)
                .add(ModItems.LAVENDRITE_PICKAXE)
                .add(ModItems.LAVENDRITE_CROSSPICK); //custom pickaxe
        valueLookupBuilder(ItemTags.AXES)
                .add(ModItems.LAVENDRITE_AXE);
        valueLookupBuilder(ItemTags.SHOVELS)
                .add(ModItems.LAVENDRITE_SHOVEL);
        valueLookupBuilder(ItemTags.HOES)
                .add(ModItems.LAVENDRITE_HOE);

        valueLookupBuilder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.LAVENDERWOOD_LOG.asItem())
                .add(ModBlocks.STRIPPED_LAVENDERWOOD_LOG.asItem())
                .add(ModBlocks.LAVENDERWOOD_WOOD.asItem())
                .add(ModBlocks.STRIPPED_LAVENDERWOOD_WOOD.asItem());

        valueLookupBuilder(ItemTags.PLANKS)
                .add(ModBlocks.LAVENDERWOOD_PLANKS.asItem());


    }
}
