package sm.lavenderbiome.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import sm.lavenderbiome.LavenderBiome;

public class ModTags
{
    public static class Blocks
    {
        public static final TagKey<Block> NEEDS_LAVENDRITE_TOOL = createTag("needs_lavendrite_tool");

        public static final TagKey<Block> INCORRECT_FOR_LAVENDRITE_TOOL = createTag("incorrect_for_lavendrite_tool");
        private static TagKey<Block> createTag(String name){
            return TagKey.of(RegistryKeys.BLOCK,  Identifier.of(LavenderBiome.MOD_ID, name));
        }
    }


    public static class Items
    {
        public static final TagKey<Item> LAVENDRITE_REPAIR = createTag("lavendrite_repair");



        private static TagKey<Item> createTag(String name){
            return TagKey.of(RegistryKeys.ITEM,  Identifier.of(LavenderBiome.MOD_ID, name));
        }
    }

}
