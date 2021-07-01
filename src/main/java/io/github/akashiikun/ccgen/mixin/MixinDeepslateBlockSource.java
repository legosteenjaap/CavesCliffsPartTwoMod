package io.github.akashiikun.ccgen.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Constant.Condition;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.akashiikun.ccgen.ConfigValues;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.DeepslateBlockSource;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;

@Mixin(DeepslateBlockSource.class)
class MixinDeepslateBlockSource {

	/**
	 * @author Legosteenjaap
	 */
	
	@ModifyConstant(constant = @Constant(intValue = -8), method = "sample")
	private static int setDeepSlateTransitionStartLevel(int original) {
		return ConfigValues.deepslateLevel - 8;
	}

	@ModifyConstant(constant = @Constant(expandZeroConditions = { Condition.GREATER_THAN_ZERO }), method = "sample")
	private static int setDeepslateEndLevel(int original) {
		return ConfigValues.deepslateLevel;
	}

	@ModifyConstant(constant = @Constant(doubleValue = -8.0D), method = "sample")
	private static double setDeepslateTransitionStartLevel(double original) {
		return ConfigValues.deepslateLevel - 8;
	}

	@ModifyConstant(constant = @Constant(doubleValue = 0.0D, ordinal = 0), method = "sample")
	private static double setDeepslateEndLevel(double original) {
		return ConfigValues.deepslateLevel;
	}
}