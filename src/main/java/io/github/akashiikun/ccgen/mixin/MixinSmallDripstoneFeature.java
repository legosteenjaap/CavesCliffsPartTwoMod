package io.github.akashiikun.ccgen.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DripstoneClusterFeatureConfig;
import net.minecraft.world.gen.feature.SmallDripstoneFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;

@Mixin(SmallDripstoneFeature.class)
public class MixinSmallDripstoneFeature {
	
	/**
	 * @author Legosteenjaap
	 */
	
	@Inject(method = "generate", at = @At("HEAD"), cancellable = true)
	public void generate(FeatureContext<DripstoneClusterFeatureConfig> context, CallbackInfoReturnable<Boolean> cir) {
		StructureWorldAccess world = context.getWorld();
		BlockPos pos = context.getOrigin();
		for (int i = 0; i < 5; i++) {
			if (world.getBlockState(pos).isOf(Blocks.WATER)) {
				cir.setReturnValue(false);
				break;
			} else {
				pos = pos.up();
			}
		}
				
		
	}
}
