package sm.lavenderbiome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import sm.lavenderbiome.block.ModBlocks;
import sm.lavenderbiome.block.custom.LavenderCropBlock;
import sm.lavenderbiome.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {


    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.LAVENDRITE_BLOCK);
        addDrop(ModBlocks.RAW_LAVENDRITE_BLOCK);

        addDrop(ModBlocks.LAVENDRITE_ORE, multipleOreDrops(ModBlocks.LAVENDRITE_ORE, ModItems.RAW_LAVENDRITE, 2.0f, 5.0f));
        addDrop(ModBlocks.DEEPSLATE_LAVENDRITE_ORE, multipleOreDrops(ModBlocks.DEEPSLATE_LAVENDRITE_ORE, ModItems.RAW_LAVENDRITE, 3.0f, 7.0f));

        BlockStatePropertyLootCondition.Builder builder = BlockStatePropertyLootCondition.builder(ModBlocks.LAVENDER_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(LavenderCropBlock.AGE, LavenderCropBlock.MAX_AGE));
        this.addDrop(ModBlocks.LAVENDER_CROP, this.cropDrops(ModBlocks.LAVENDER_CROP, ModItems.LAVENDER, ModItems.LAVENDER_SEEDS, builder));
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }

}
