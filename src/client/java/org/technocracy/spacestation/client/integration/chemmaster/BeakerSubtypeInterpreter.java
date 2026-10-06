package org.technocracy.spacestation.client.integration.chemmaster;

import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.technocracy.spacestation.chemistry.ChemData;
import org.technocracy.spacestation.registry.ModComponents;

import java.util.TreeSet;

public class BeakerSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {

    public static final BeakerSubtypeInterpreter INSTANCE =
            new BeakerSubtypeInterpreter();

    private BeakerSubtypeInterpreter() {
    }

    @Override
    public @Nullable Object getSubtypeData(ItemStack stack, UidContext context) {
        ChemData data = stack.get(ModComponents.CHEM_DATA);

        if (data == null || data.chemicals().isEmpty()) {
            return "empty";
        }

        return new TreeSet<>(data.chemicals().keySet());
    }

    @Override
    public @NotNull String getLegacyStringSubtypeInfo(ItemStack stack, UidContext context) {
        ChemData data = stack.get(ModComponents.CHEM_DATA);

        if (data == null || data.chemicals().isEmpty()) {
            return "empty";
        }

        return String.join(",", new TreeSet<>(data.chemicals().keySet()));
    }
}