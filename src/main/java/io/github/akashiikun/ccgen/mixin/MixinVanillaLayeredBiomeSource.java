package io.github.akashiikun.ccgen.mixin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import io.github.akashiikun.ccgen.api.CaveBiomeApi;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.MixedNoisePoint;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.BiomeLayerSampler;
import net.minecraft.world.biome.source.MultiNoiseBiomeSource;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSource;

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
        
    	List<Pair<MixedNoisePoint, Supplier<Biome>>> biomes = new ArrayList<Pair<MixedNoisePoint, Supplier<Biome>>>();
    	List<Pair<MixedNoisePoint, RegistryKey<Biome>>> biomeList = CaveBiomeApi.getCaveBiomes();
    	
    	for (int i = 0; i < biomeList.size(); i++) {
    		Pair<MixedNoisePoint, RegistryKey<Biome>> pair = biomeList.get(i);
    		biomes.add(Pair.of(pair.getFirst(), () -> {
                return (Biome) biomeRegistry.getOrThrow(pair.getSecond());
            }));
    	}
    	
    	
    	
    	multiNoise = new MultiNoiseBiomeSource(seed, ImmutableList.copyOf(biomes));
        
    }

    /**
     * @author SuperCoder79 & AkashiiKun
     */

    @Inject(method = "getBiomeForNoiseGen", at = @At("TAIL"), cancellable = true)
    private void injected(int biomeX, int biomeY, int biomeZ, CallbackInfoReturnable<Biome> cir) {
        if(biomeY < 14) {
            Biome caveBiome = multiNoise.getBiomeForNoiseGen(biomeX, biomeY, biomeZ);
            //System.out.println(caveBiome.toString());
            if (!caveBiome.equals((Biome) biomeRegistry.getOrThrow(BiomeKeys.PLAINS))) {
                cir.setReturnValue(caveBiome);
            }
        }
    }
}
