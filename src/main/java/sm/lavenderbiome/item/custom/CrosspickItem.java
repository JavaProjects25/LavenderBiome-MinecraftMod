package sm.lavenderbiome.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

public class CrosspickItem extends Item {

    public CrosspickItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(settings.pickaxe(material, attackDamage, attackSpeed));
    }

    static boolean validToMine(int x, int y) {

        if((y == -1 && x == -1) || (y == -1 && x == 1) || (y == 1 && x == -1) || (y == 1 && x == 1))
            return false;

        else return true;
    }
    public static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos initalBlockPos, ServerPlayerEntity player) {
        List<BlockPos> positions = new ArrayList<>();
        HitResult hit = player.raycast(20, 0, false);
        if (hit.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = (BlockHitResult) hit;

            if(blockHit.getSide() == Direction.DOWN || blockHit.getSide() == Direction.UP) {

                for(int x = -range; x <= range; x++) {

                    for(int y = -range; y <= range; y++) {
                        if(validToMine(x, y)) {
                            positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY(), initalBlockPos.getZ() + y));
                        }
                    }
                }

            }

            if(blockHit.getSide() == Direction.NORTH || blockHit.getSide() == Direction.SOUTH) {
                for(int x = -range; x <= range; x++) {
                    for(int y = -range; y <= range; y++) {
                        if(validToMine(x, y)) {
                            positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY() + y, initalBlockPos.getZ()));
                        }
                    }
                }
            }

            if(blockHit.getSide() == Direction.EAST || blockHit.getSide() == Direction.WEST) {
                for(int x = -range; x <= range; x++) {
                    for(int y = -range; y <= range; y++) {
                        if(validToMine(x, y)) {
                            positions.add(new BlockPos(initalBlockPos.getX(), initalBlockPos.getY() + y, initalBlockPos.getZ() + x));
                        }
                    }
                }
            }
        }

        return positions;
    }

}
