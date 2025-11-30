package sm.lavenderbiome.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.MinecartItem;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import sm.lavenderbiome.LavenderBiome;
import sm.lavenderbiome.block.ModBlocks;
import sm.lavenderbiome.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.item.Items.*;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                List<ItemConvertible> LAVENDRITE_SMELTABLES = List.of(
                        ModItems.RAW_LAVENDRITE,
                        ModBlocks.LAVENDRITE_ORE,
                        ModBlocks.DEEPSLATE_LAVENDRITE_ORE
                );

                //smelting and blasting
                offerSmelting(LAVENDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.LAVENDRITE_INGOT, 0.2f, 200, "lavendrite");
                offerBlasting(LAVENDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.LAVENDRITE_INGOT, 0.2f, 100, "lavendrite");

                //blocks and items
                offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.LAVENDRITE_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDRITE_BLOCK);
                offerReversibleCompactingRecipes(RecipeCategory.MISC, ModItems.RAW_LAVENDRITE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_LAVENDRITE_BLOCK);

                //food & drink
                createShaped(RecipeCategory.FOOD, ModItems.LAVENDER_LATTE)
                        .pattern("###")
                        .pattern("SGS")
                        .pattern(" M ")
                        .input('#', ModBlocks.LAVENDER)
                        .input('S', SUGAR)
                        .input('M', MILK_BUCKET)
                        .input('G', GLASS_BOTTLE)
                        .criterion(hasItem(ModItems.LAVENDRITE_INGOT), conditionsFromItem(ModItems.LAVENDRITE_INGOT))
                        .offerTo(exporter);

                //raw lavendrite and lavendrite ingot from blocks
                createShapeless(RecipeCategory.MISC, ModItems.RAW_LAVENDRITE, 9)
                        .input(ModBlocks.RAW_LAVENDRITE_BLOCK)
                        .criterion(hasItem(ModBlocks.RAW_LAVENDRITE_BLOCK), conditionsFromItem(ModBlocks.RAW_LAVENDRITE_BLOCK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(LavenderBiome.MOD_ID, "raw_lavendrite_from_block")));

                createShapeless(RecipeCategory.MISC, ModItems.LAVENDRITE_INGOT, 9)
                        .input(ModBlocks.LAVENDRITE_BLOCK)
                        .criterion(hasItem(ModBlocks.LAVENDRITE_BLOCK), conditionsFromItem(ModBlocks.LAVENDRITE_BLOCK))
                        .offerTo(exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(LavenderBiome.MOD_ID, "lavendrite_ingot_from_block")));

                //tools & Combat
                createShaped(RecipeCategory.COMBAT, ModItems.LAVENDRITE_SWORD)
                        .pattern(" # ")
                        .pattern(" # ")
                        .pattern(" S ")
                        .input('#', ModItems.LAVENDRITE_INGOT)
                        .input('S', STICK)
                        .criterion(hasItem(ModItems.LAVENDRITE_INGOT), conditionsFromItem(ModItems.LAVENDRITE_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ModItems.LAVENDRITE_PICKAXE)
                        .pattern("###")
                        .pattern(" S ")
                        .pattern(" S ")
                        .input('#', ModItems.LAVENDRITE_INGOT)
                        .input('S', STICK)
                        .criterion(hasItem(ModItems.LAVENDRITE_INGOT), conditionsFromItem(ModItems.LAVENDRITE_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ModItems.LAVENDRITE_AXE)
                        .pattern("## ")
                        .pattern("#S ")
                        .pattern(" S ")
                        .input('#', ModItems.LAVENDRITE_INGOT)
                        .input('S', STICK)
                        .criterion(hasItem(ModItems.LAVENDRITE_INGOT), conditionsFromItem(ModItems.LAVENDRITE_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ModItems.LAVENDRITE_SHOVEL)
                        .pattern(" # ")
                        .pattern(" S ")
                        .pattern(" S ")
                        .input('#', ModItems.LAVENDRITE_INGOT)
                        .input('S', STICK)
                        .criterion(hasItem(ModItems.LAVENDRITE_INGOT), conditionsFromItem(ModItems.LAVENDRITE_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ModItems.LAVENDRITE_HOE)
                        .pattern("## ")
                        .pattern(" S ")
                        .pattern(" S ")
                        .input('#', ModItems.LAVENDRITE_INGOT)
                        .input('S', STICK)
                        .criterion(hasItem(ModItems.LAVENDRITE_INGOT), conditionsFromItem(ModItems.LAVENDRITE_INGOT))
                        .offerTo(exporter);

                //custom tools
                createShaped(RecipeCategory.TOOLS, ModItems.LAVENDRITE_CROSSPICK)
                        .pattern(" # ")
                        .pattern("#S#")
                        .pattern(" # ")
                        .input('#', ModItems.LAVENDRITE_INGOT)
                        .input('S', STICK)
                        .criterion(hasItem(ModItems.LAVENDRITE_INGOT), conditionsFromItem(ModItems.LAVENDRITE_INGOT))
                        .offerTo(exporter);

            }
        };
    }

    @Override
    public String getName() {
        return "Lavender Biome Recipes";
    }
}
