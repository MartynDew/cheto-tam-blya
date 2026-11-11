package org.technocracy.spacestation.system;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.technocracy.spacestation.registry.ModAttributes;
import org.technocracy.spacestation.registry.ModComponents;
import org.technocracy.spacestation.registry.ModDamageTypes;

import java.util.concurrent.ThreadLocalRandom;

public final class RadiationManager {
    public double radiationLevel = 0;

    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup lookup) {
        nbt.putDouble("radiationLevel", this.radiationLevel);
    }

    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup lookup) {
        this.radiationLevel = nbt.getDouble("radiationLevel");
    }

    public void tick(ServerPlayerEntity player) {
        PlayerInventory inventory = player.getInventory();
        for (ItemStack stack : inventory.main) {
            if (!stack.isEmpty() && stack.contains(ModComponents.RADIATION_GIVER_COMPONENT)) {
                int count = stack.getCount();
                Integer radiationPower = stack.get(ModComponents.RADIATION_GIVER_COMPONENT);
                if (radiationPower != null) {
                    this.radiationLevel += (double) (radiationPower * count)/100*(100-player.getAttributeValue(ModAttributes.RADIATION_PROTECTION));
                }
            }
        }

        if (this.radiationLevel > 500) {
            int random = ThreadLocalRandom.current().nextInt(1, 4);

            if (random == 1) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0));
                player.sendMessage(Text.literal("Чувствую себя нехорошо.."), true);
            } else if (random == 2) {
                player.damage(ModDamageTypes.of(player.getWorld(), ModDamageTypes.RADIATION_DAMAGE_TYPE), 6.0f);
                player.sendMessage(Text.literal("Моя кожа отслаивается.."), true);
            } else if (random == 3) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 200, 0));
                player.sendMessage(Text.literal("В глазах темнеет.."), true);
            }
        }
    }
}

