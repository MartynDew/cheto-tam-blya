package org.technocracy.spacestation.registry;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;

public class ModAttributes {
    public static final RegistryEntry<EntityAttribute> RADIATION_PROTECTION = register("generic.radiation_protection", (new ClampedEntityAttribute("attribute.name.generic.radiation_protection", 0.0, 0.0, 100.0)).setTracked(true));

    public ModAttributes() {
        super();
    }

    public static void register() {}

    private static RegistryEntry<EntityAttribute> register(String id, EntityAttribute attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE, Identifier.of(SpaceStation.MOD_ID, id), attribute);
    }
}
