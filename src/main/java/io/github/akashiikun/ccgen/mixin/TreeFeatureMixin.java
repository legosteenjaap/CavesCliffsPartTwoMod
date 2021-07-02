package io.github.akashiikun.ccgen.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;
import java.util.function.BiConsumer;

@Mixin(TreeFeature.class)
public class TreeFeatureMixin {

	/**
	 * @author Legosteenjaap
	 */
	
	@Inject(method = "generate(Lnet/minecraft/world/StructureWorldAccess;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;Ljava/util/function/BiConsumer;Ljava/util/function/BiConsumer;Lnet/minecraft/world/gen/feature/TreeFeatureConfig;)Z", at = @At("HEAD"), cancellable = true)
	private void generate(StructureWorldAccess world, Random random, BlockPos pos, BiConsumer<BlockPos, BlockState> trunkReplacer, BiConsumer<BlockPos, BlockState> foliageReplacer, TreeFeatureConfig config, CallbackInfoReturnable<Boolean> cir) {
		if (world.getBlockState(pos).isOf(Blocks.WATER) || world.getBlockState(pos.up()).isOf(Blocks.WATER)) cir.setReturnValue(false);
	}
	
}
