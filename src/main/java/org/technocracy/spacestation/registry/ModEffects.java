package org.technocracy.spacestation.registry;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.effect.RadiationStatusEffect;

public class ModEffects {

    // 1. Регистрируем эффекты прямо при объявлении констант
    public static final StatusEffect RADIATION = register("radiation", new RadiationStatusEffect());


    // 2. Универсальный метод регистрации
    private static StatusEffect register(String path, StatusEffect effect) {
        return Registry.register(
                Registries.STATUS_EFFECT,
                Identifier.of(SpaceStation.MOD_ID, path), // Используем MOD_ID вместо хардкода строки
                effect
        );
    }

    public static void register() {}
}
