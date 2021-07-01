package io.github.akashiikun.ccgen.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import io.github.akashiikun.ccgen.ConfigValues;
import net.minecraft.world.gen.surfacebuilder.NetherForestSurfaceBuilder;

@Mixin(NetherForestSurfaceBuilder.class)
public class MixinNetherForestSurfaceBuilder {

	/**
	 * @author Legosteenjaap
	 */
	
	@ModifyConstant(constant = @Constant(intValue = 127), method = "generate")
	private static int setHeightLimit(int original) {
		return ConfigValues.generationHeightNether - 1;
	}
	
}
