package sm.lavenderbiome.mixin;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sm.lavenderbiome.world.biome.ModBiomes;

@Mixin(SheepEntity.class)
public abstract class LavenderSheepMixin extends AnimalEntity {

    protected LavenderSheepMixin(EntityType<? extends AnimalEntity> entityType, net.minecraft.world.World world) {
        super(entityType, world);
    }

    @Inject(method = "initialize", at = @At("TAIL"))
    private void initLavenderSheep(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, CallbackInfoReturnable<EntityData> cir) {
        // 1. Check: Is the sheep currently inside the Lavender Biome?
        if (world.getBiome(this.getBlockPos()).matchesKey(ModBiomes.LAVENDER_BIOME)) {
            SheepEntity sheep = (SheepEntity) (Object) this;

            // 2. Override the color logic
            float chance = this.random.nextFloat();

            sheep.setCustomName(Text.literal("Jeff"));
            sheep.setCustomNameVisible(true);


            if (chance < 0.60f) {
                sheep.setColor(DyeColor.PURPLE);
            } else if (chance < 0.90f) {
                sheep.setColor(DyeColor.PINK);
            } else {
                sheep.setColor(DyeColor.WHITE);
            }
        }
    }
}