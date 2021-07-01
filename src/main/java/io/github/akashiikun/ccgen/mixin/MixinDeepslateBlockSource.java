package io.github.akashiikun.ccgen.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.akashiikun.ccgen.ConfigValues;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.DeepslateBlockSource;

@Mixin(DeepslateBlockSource.class)
class MixinDeepslateBlockSource {

    @Shadow @Final private long seed;
    @Shadow	@Final private ChunkRandom random;
    @Shadow	@Final private BlockState defaultBlock;
    @Shadow	@Final private BlockState deepslateState;

    @Inject(method = "sample", at = @At("HEAD"), cancellable = true)
    public void sample(int x, int y, int z, CallbackInfoReturnable<BlockState> cir) {
        if (!ConfigValues.deepslate) {
            cir.setReturnValue(this.defaultBlock);
        } else if (y < ConfigValues.deepslateLevel - 8) {
            cir.setReturnValue(this.deepslateState);
        } else if (y > ConfigValues.deepslateLevel) {
            cir.setReturnValue(this.defaultBlock);
        } else {
            double d = MathHelper.lerpFromProgress((double) y, (double)(ConfigValues.deepslateLevel - 8), (double)(ConfigValues.deepslateLevel), 1.0D, 0.0D);
            this.random.setGrimstoneSeed(this.seed, x, y, z);
            cir.setReturnValue((double) this.random.nextFloat() < d ? this.deepslateState : this.defaultBlock);
        }
    }
}