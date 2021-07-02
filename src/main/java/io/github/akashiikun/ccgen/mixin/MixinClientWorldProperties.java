package io.github.akashiikun.ccgen.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.akashiikun.ccgen.ConfigValues;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;

@Mixin(ClientWorld.Properties.class)
public class MixinClientWorldProperties {
	
    @Shadow @Final private boolean flatWorld;

	
    @Inject(method = "getHorizonShadingRatio", at = @At("HEAD"), cancellable = true)
	public void getHorizonShadingRatio(CallbackInfoReturnable<Double> cir) {
    	if (!this.flatWorld) {
    		cir.setReturnValue(MinecraftClient.getInstance().player.getY() > ConfigValues.seaLevel - 32 ? 0.03125D : 0.0D);
    	} else {
    		cir.setReturnValue(1.0D);
    	}
     }
}
