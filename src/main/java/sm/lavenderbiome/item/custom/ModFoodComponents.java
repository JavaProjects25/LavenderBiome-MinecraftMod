package sm.lavenderbiome.item.custom;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.UseAction;
import net.minecraft.sound.SoundEvents;

public class ModFoodComponents {

        public static final FoodComponent LAVENDER_LATTE = (new FoodComponent.Builder()).nutrition(4).saturationModifier(0.25f).alwaysEdible().build();




}