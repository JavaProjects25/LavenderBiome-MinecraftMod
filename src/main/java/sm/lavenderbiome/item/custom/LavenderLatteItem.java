package sm.lavenderbiome.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

public class LavenderLatteItem extends Item {

    public LavenderLatteItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        // This call handles eating the food and decrementing the stack.
        super.finishUsing(stack, world, user);

        if (user instanceof ServerPlayerEntity serverPlayer) {
            Criteria.CONSUME_ITEM.trigger(serverPlayer, stack);
            serverPlayer.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        // If the user is a player, give them a glass bottle back.
        if (user instanceof PlayerEntity player && !player.getAbilities().creativeMode) {
            // If the latte stack is now empty, return a glass bottle to replace it.
            if (stack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }
            // If the stack is not empty, add a glass bottle to the player's inventory.
            player.getInventory().insertStack(new ItemStack(Items.GLASS_BOTTLE));
        }

        // Return the original stack, which is now either empty or has a reduced count.
        return stack;
    }
}
