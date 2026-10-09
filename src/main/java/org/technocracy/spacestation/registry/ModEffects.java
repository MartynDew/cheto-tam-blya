package org.technocracy.spacestation.registry;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.effect.RadiationStatusEffect;

public class ModEffects {

    public static final StatusEffect RADIATION = register("radiation", new RadiationStatusEffect());

    private static StatusEffect register(String path, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, Identifier.of(SpaceStation.MOD_ID, path), effect);
    }

    public static void register() {}
}
