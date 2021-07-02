package io.github.akashiikun.ccgen.core.api;

import io.github.akashiikun.ccgen.mixin.VanillaLayeredBiomeSourceMixin;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.MultiNoiseBiomeSource;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//<>

/**
 * Special Thanks to TelepathicGrunt and LudoCrypt!
 */
public class CaveGenerationAPI {
    private static MultiNoiseBiomeSource caveBiomeSource;

    /**
     * Initializes the cave generation into the biome provider.
     *
     * @implNote This initialization calls the biome provider seed and the cave biome size.
     *
     * @param seed the biome provider seed
     *
     * @see VanillaLayeredBiomeSourceMixin#cba$initialize(long, boolean, boolean, Registry, CallbackInfo)
     */
    public static void initializeCaveBiomes(Registry<Biome> biomeRegistry, long seed) {
    	caveBiomeSource = CaveLayer.create(biomeRegistry, seed);
    }

    /**
     * Injects the CaveBiome generation into a biomeSource
     *
     * @param surfaceBiomes the generated surface biomes
     * @param x the {@link net.minecraft.world.biome.source.BiomeSource#getBiomeForNoiseGen(int, int, int)} x value
     * @param y the {@link net.minecraft.world.biome.source.BiomeSource#getBiomeForNoiseGen(int, int, int)} y value
     * @param z the {@link net.minecraft.world.biome.source.BiomeSource#getBiomeForNoiseGen(int, int, int)} z value
     *
     * @return the CaveBiomes injected into the biomeSource
     * @see VanillaLayeredBiomeSourceMixin#getBiomeForNoiseGen(int, int, int)
     */
    public static Biome injectCaveBiomes(Biome surfaceBiomes, int x, int y, int z) {
        Biome caveBiome = caveBiomeSource.getBiomeForNoiseGen(x, 0, z);
    	if (y <= 12 && caveBiome.getCategory() == Biome.Category.UNDERGROUND) {
            return caveBiome;
        }
        return surfaceBiomes;
    }

    /**
     * Injects a CaveBiome into the biomeSource (needs to be in the underground biome category)
     *
     * @see #addDefaultCaves()
     *
     * @param biome the biome for injection
     * @param noise the mixed noise point used for generation
     */
    public static void addCaveBiome(RegistryKey<Biome> biome, Biome.MixedNoisePoint noise) {
        if (biome == null) {
            throw new NullPointerException("CaveGenerationAPI's addCaveBiome method must take a registered biome. Null or unregistered biomes will be rejected.");
        }
        // Store the key as we will get the correct biome instance when the biome source is created.
        
        CaveLayer.addCaveBiome(biome, noise);
    }

    /**
     * Injects a CaveBiome into the biomeSource (needs to be in the underground biome category)
     *
     * @param biome the biome for injection
     * @param noise the mixed noise point used for generation
     */
    public static void addCaveBiome(Biome biome, Biome.MixedNoisePoint noise) {
        if (biome == null || BuiltinRegistries.BIOME.getKey(biome).isEmpty()) {
            throw new NullPointerException("CaveGenerationAPI's addCaveBiome method must take a registered biome. Null or unregistered biomes will be rejected.");
        }
        addCaveBiome(BuiltinRegistries.BIOME.getKey(biome).get(), noise);
    }

    /**
     * Example of injection for Cave Biomes into the biomeSource
     *
     * @see #addCaveBiome(Biome, Biome.MixedNoisePoint)
     */
    public static void addDefaultCaves() {
		CaveGenerationAPI.addCaveBiome(BiomeKeys.PLAINS, new Biome.MixedNoisePoint(0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		CaveGenerationAPI.addCaveBiome(BiomeKeys.LUSH_CAVES, new Biome.MixedNoisePoint(-0.2F, 0.2F, 0.325F, -0.15F, 0.0F));
		CaveGenerationAPI.addCaveBiome(BiomeKeys.DRIPSTONE_CAVES, new Biome.MixedNoisePoint(0.0F, -0.325F, -0.275F, 0.2F, 0.0F));
    }

    static {
        addDefaultCaves();
    }
}