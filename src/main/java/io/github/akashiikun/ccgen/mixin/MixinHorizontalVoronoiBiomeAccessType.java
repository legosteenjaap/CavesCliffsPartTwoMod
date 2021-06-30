package io.github.akashiikun.ccgen.mixin;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.HorizontalVoronoiBiomeAccessType;
import net.minecraft.world.biome.source.VoronoiBiomeAccessType;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HorizontalVoronoiBiomeAccessType.class)
public class MixinHorizontalVoronoiBiomeAccessType {

    /**
     * @author SuperCoder79
     */

    @Inject(method = "getBiome", at = @At("HEAD"), cancellable = true)
    public void getBiome(long seed, int x, int y, int z, BiomeAccess.Storage storage, CallbackInfoReturnable<Biome> cir) {
        cir.setReturnValue(VoronoiBiomeAccessType.INSTANCE.getBiome(seed, x, y, z, storage));
    }
}
