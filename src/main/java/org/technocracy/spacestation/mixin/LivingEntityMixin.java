package org.technocracy.spacestation.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.technocracy.spacestation.registry.ModAttributes;
import org.technocracy.spacestation.system.LivingEntityExt;
import org.technocracy.spacestation.system.RadiationManager;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements LivingEntityExt {

    @Unique
    private final RadiationManager ss15$radiationManager = new RadiationManager();

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    public RadiationManager ss15$getRadiationManager() {
        return this.ss15$radiationManager;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void ss15$writeData(NbtCompound nbt, CallbackInfo ci) {
        this.ss15$radiationManager.writeNbt(nbt, this.getRegistryManager());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void ss15$readData(NbtCompound nbt, CallbackInfo ci) {
        this.ss15$radiationManager.readNbt(nbt, this.getRegistryManager());
    }

    @Inject(method = "createLivingAttributes", at = @At("RETURN"))
    private static void addCustomAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue().add(ModAttributes.RADIATION_PROTECTION);
    }
}
