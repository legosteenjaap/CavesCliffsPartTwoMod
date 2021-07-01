package io.github.akashiikun.ccgen.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.akashiikun.ccgen.ConfigValues;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;

@Mixin(ChunkGeneratorSettings.class)
public class ChunkGeneratorSettingsMixin {

	@ModifyConstant(constant = @Constant(intValue = 0), method = "createSurfaceSettings")
	private static int minYOverworld(int original) {
		return ConfigValues.minYOverworld;
	}

	@ModifyConstant(constant = @Constant(intValue = 256), method = "createSurfaceSettings")
	private static int heightOverworld(int original) {
		return ConfigValues.heightOverworld;
	}
	
	/*@ModifyVariable(method = "createSurfaceSettings", at = @At("STORE"), ordinal = 1)
	private boolean injected(boolean aquifers) {
	  return ConfigValues.hasAquifers;
	}*/
	
}
