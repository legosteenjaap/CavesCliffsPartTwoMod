package io.github.akashiikun.ccgen.core.api;

import com.google.common.base.Preconditions;
import com.mojang.datafixers.util.Pair;
import io.github.akashiikun.ccgen.CavesCliffsGenerationMod;
import io.github.akashiikun.ccgen.mixin.MultiNoiseBiomeSourceAccessor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.MultiNoiseBiomeSource;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

//<>

/**
 * @author LudoCrypt
 */
public class CaveLayer {
	public static final Map<RegistryKey<Biome>, Biome.MixedNoisePoint> CAVE_BIOMES = new HashMap<>();
	public static final List<Biome> CAVE_BIOME_LIST = new ArrayList<>();

	public static MultiNoiseBiomeSource create(Registry<Biome> biomeRegistry, long seed) {
		CAVE_BIOME_LIST.addAll(CAVE_BIOMES.keySet().stream().map(biomeRegistry::get).collect(Collectors.toList()));
		return CENTER_BIOME_SOURCE.getBiomeSource(biomeRegistry, seed);
	}

	public static void addCaveBiome(RegistryKey<Biome> biome, Biome.MixedNoisePoint noise) {
		Preconditions.checkNotNull(biome, "biome is null");
		Preconditions.checkNotNull(noise, "noise is null");
		CAVE_BIOMES.put(biome, noise);
	}

	public static final MultiNoiseBiomeSource.Preset CENTER_BIOME_SOURCE = new MultiNoiseBiomeSource.Preset(new Identifier(CavesCliffsGenerationMod.MOD_ID, "cave_biome_source"), (preset, registry, long_) -> {
		List<Pair<Biome.MixedNoisePoint, Supplier<Biome>>> biomes = new ArrayList<>();
		CAVE_BIOMES.forEach((biomeKey, noisePoint) -> {
			Biome biome = registry.getOrThrow(biomeKey);
			biomes.add(Pair.of(noisePoint, () -> biome));
		});

		MultiNoiseBiomeSource.NoiseParameters altitudeNoise = new MultiNoiseBiomeSource.NoiseParameters(-9, 1.0D, 0.0D, 3.0D, 3.0D, 3.0D, 3.0D);
		MultiNoiseBiomeSource.NoiseParameters temperatureNoise = new MultiNoiseBiomeSource.NoiseParameters(-7, 1.0D, 2.0D, 4.0D, 4.0D);
		MultiNoiseBiomeSource.NoiseParameters humidityNoise = new MultiNoiseBiomeSource.NoiseParameters(-9, 1.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0D);
		MultiNoiseBiomeSource.NoiseParameters weirdnessNoise = new MultiNoiseBiomeSource.NoiseParameters(-8, 1.2D, 0.6D, 0.0D, 0.0D, 1.0D, 0.0D);

		return MultiNoiseBiomeSourceAccessor.createMultiNoiseBiomeSource(long_, biomes, altitudeNoise, temperatureNoise, humidityNoise, weirdnessNoise, Optional.empty());
//		return MultiNoiseBiomeSourceAccessor.createMultiNoiseBiomeSource(long_, biomes, Optional.of(Pair.of(registry, preset)));
	});
}