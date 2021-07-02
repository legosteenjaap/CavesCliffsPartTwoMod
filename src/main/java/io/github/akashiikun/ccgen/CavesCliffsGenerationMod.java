package io.github.akashiikun.ccgen;

import io.github.akashiikun.ccgen.api.CaveBiomeApi;
import io.github.akashiikun.ccgen.api.CaveBiomeCreator;
import io.github.akashiikun.ccgen.api.ConfiguredCaveBiomeFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class CavesCliffsGenerationMod implements ModInitializer{
	
	public static final String modid = "ccgen";
	
	public static final Biome ICE_CAVES = CaveBiomeCreator.createIceCaves();
	public static final RegistryKey<Biome> ICE_CAVES_KEY = RegistryKey.of(Registry.BIOME_KEY,
			new Identifier(modid, "ice_caves"));
	@Override
	public void onInitialize() {
		ConfiguredCaveBiomeFeatures.init();
		WorldGen.init();
		
		
		CaveBiomeApi.registerCaveBiome(BiomeKeys.LUSH_CAVES, 0.6F, 0.0F, 0.0F, 0.0F, 0.0F);
		CaveBiomeApi.registerCaveBiome(BiomeKeys.DRIPSTONE_CAVES, 0.0F, -0.6F, 0.0F, 0.0F, 0.0F);
		
		// This cave biome is hardcoded to be replaced by the biome above
		CaveBiomeApi.registerCaveBiome(BiomeKeys.PLAINS, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
		
		Registry.register(BuiltinRegistries.BIOME, ICE_CAVES_KEY.getValue(), ICE_CAVES);
		
		CaveBiomeApi.registerCaveBiome(ICE_CAVES_KEY, 0.0f, 0.0f, 0.6f, 0.0f, 0.0f);
		
		OverworldBiomes.addContinentalBiome(ICE_CAVES_KEY, OverworldClimate.TEMPERATE, 15);
	}

}
