package io.github.akashiikun.ccgen.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.level.ColorResolver;

  /**
  * @author Legosteenjaap
  */

@Mixin(BiomeColors.class)
public class MixinBiomeColors {

	@Shadow @Final
	private static ColorResolver GRASS_COLOR = Biome::getGrassColorAt;
	@Shadow @Final
	private static ColorResolver FOLIAGE_COLOR = (biome, d, e) -> {
		return biome.getFoliageColor();
	};
	@Shadow @Final
	private static ColorResolver WATER_COLOR = (biome, d, e) -> {
		return biome.getWaterColor();
	};

	@Shadow
	private static int getColor(BlockRenderView world, BlockPos pos, ColorResolver resolver) {
		return world.getColor(pos, resolver);
	}

	@Inject(method = "getGrassColor", at = @At("HEAD"), cancellable = true)
	private static void getGrassColor(BlockRenderView world, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(getColor(world, pos.withY(128), GRASS_COLOR));
	}

	@Inject(method = "getFoliageColor", at = @At("HEAD"), cancellable = true)
	private static void getFoliageColor(BlockRenderView world, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(getColor(world, pos.withY(128), FOLIAGE_COLOR));
	}
	
	@Inject(method = "getWaterColor", at = @At("HEAD"), cancellable = true)
	private static void getWaterColor(BlockRenderView world, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(getColor(world, pos.withY(128), WATER_COLOR));
	}
	
}
