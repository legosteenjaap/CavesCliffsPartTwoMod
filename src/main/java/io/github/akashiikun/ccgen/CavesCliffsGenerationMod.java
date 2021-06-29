package io.github.akashiikun.ccgen;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.minecraft.world.biome.BiomeKeys;


public class CavesCliffsGenerationMod implements ModInitializer {

	@Override
	public void onInitialize() {
		OverworldBiomes.addContinentalBiome(BiomeKeys.LUSH_CAVES, OverworldClimate.COOL, 1D);
		OverworldBiomes.addContinentalBiome(BiomeKeys.DRIPSTONE_CAVES, OverworldClimate.COOL, 1D);
	}
}
