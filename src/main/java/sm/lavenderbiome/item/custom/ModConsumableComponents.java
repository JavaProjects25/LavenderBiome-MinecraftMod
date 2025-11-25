package sm.lavenderbiome.item.custom;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.UseAction;
import net.minecraft.sound.SoundEvents;

public class ModConsumableComponents {

    public static final ConsumableComponent LAVENDER_LATTE = drink().consumeEffect(
                    new ApplyEffectsConsumeEffect(
                            new StatusEffectInstance(StatusEffects.HASTE, 200, 0), 1.0f))
            .build();

    public static ConsumableComponent.Builder drink() {
        return ConsumableComponent.builder().consumeSeconds(2F).useAction(UseAction.DRINK).sound(SoundEvents.ENTITY_GENERIC_DRINK).consumeParticles(true);
    }
}



