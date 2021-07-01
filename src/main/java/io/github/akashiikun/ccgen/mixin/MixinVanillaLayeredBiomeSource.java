package io.github.akashiikun.ccgen.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.BiomeLayerSampler;
import net.minecraft.world.biome.source.MultiNoiseBiomeSource;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSource;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VanillaLayeredBiomeSource.class)
public class MixinVanillaLayeredBiomeSource {

    @Shadow @Final private BiomeLayerSampler biomeSampler;

    @Shadow @Final private Registry<Biome> biomeRegistry;

    /**
     * @author legosteenjaap
     */
    @Unique
    MultiNoiseBiomeSource multiNoise;



    @Inject(method = "<init>", at = @At("RETURN"))
    private void makeNoise(long seed, boolean legacyBiomeInitLayer, boolean largeBiomes, Registry<Biome> biomeRegistry, CallbackInfo ci) {
        multiNoise = new MultiNoiseBiomeSource(seed, ImmutableList.of(Pair.of(new Biome.MixedNoisePoint(0.6F, 0.0F, 0.0F, 0.0F, 0.0F), () -> {
            return (Biome) biomeRegistry.getOrThrow(BiomeKeys.LUSH_CAVES);
        }), Pair.of(new Biome.MixedNoisePoint(0.0F, -0.6F, 0.0F, 0.0F, 0.0F), () -> {
            return (Biome) biomeRegistry.getOrThrow(BiomeKeys.DRIPSTONE_CAVES);
        }), Pair.of(new Biome.MixedNoisePoint(0.0F, 0.0F, 0.0F, 0.0F, 0.0F), () -> {
            return (Biome) biomeRegistry.getOrThrow(BiomeKeys.PLAINS);

        })));
    }

    /**
     * @author SuperCoder79 & AkashiiKun
     */

    @Inject(method = "getBiomeForNoiseGen", at = @At("TAIL"), cancellable = true)
    private void injected(int biomeX, int biomeY, int biomeZ, CallbackInfoReturnable<Biome> cir) {
        if(biomeY < 14) {
            Biome caveBiome = multiNoise.getBiomeForNoiseGen(biomeX, biomeY, biomeZ);
            if (!caveBiome.equals((Biome) biomeRegistry.getOrThrow(BiomeKeys.PLAINS))) {
                cir.setReturnValue(caveBiome);
            }
        }
    }
}