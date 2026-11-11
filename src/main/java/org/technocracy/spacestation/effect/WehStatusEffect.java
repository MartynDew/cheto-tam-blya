package org.technocracy.spacestation.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.technocracy.spacestation.registry.ModDamageTypes;
import org.technocracy.spacestation.registry.ModSounds;
import java.util.concurrent.ThreadLocalRandom;

public class WehStatusEffect extends StatusEffect {
    public WehStatusEffect() {
        super(
                StatusEffectCategory.NEUTRAL,
                0x98D982); // color in RGB
    }

    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.playSound(ModSounds.PLUSHIE_WEH, 1, ThreadLocalRandom.current().nextFloat(-0.5f, 1.5f));
        return true;
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        if (duration % (100/(amplifier+1)) == 0) {
            return true;
        }
        return false;
    }
}
