package org.technocracy.spacestation.registry.items;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.registry.ModComponents;

public class RadioactiveItems {
    // над балансом потрудятся гейм дизайнеры
    public static final Item URANIUM                = register("uranium", 10);
    public static final Item URANIUM_ORE            = register("uranium_ore", 1);
    public static final Item BANANIUM           = register("bananium", 5);
    public static final Item BANANIUM_ORE           = register("bananium_ore", 1);

    private static Item register(String name, int radiation) {
        return Registry.register(
                Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, name),
                new Item(new Item.Settings()
                .component(ModComponents.RADIATION_GIVER_COMPONENT, radiation))
        );
    }
    private RadioactiveItems() {}

    public static void register() {}
}
