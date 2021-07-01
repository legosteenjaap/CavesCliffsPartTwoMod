package io.github.akashiikun.ccgen.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.world.WorldView;

@Mixin(AxolotlEntity.class)
public class MixinAxolotlEntity {
	
	/**
	 * @author Legosteenjaap
	 */
	
    @Inject(method = "canSpawn", at = @At(value = "TAIL"), cancellable = true)
	public void canSpawn(WorldView world, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(true);
	}
}
