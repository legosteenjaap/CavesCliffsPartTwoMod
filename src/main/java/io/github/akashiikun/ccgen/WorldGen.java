package io.github.akashiikun.ccgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext.GenerationSettingsContext;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeatures;

@SuppressWarnings("deprecation")
public class WorldGen {

	private static final String modid = CavesCliffsGenerationMod.modid;
	
	
	public static void init () {
		BiomeModifications.create(new Identifier(modid, "lush_caves_gen")).add(ModificationPhase.POST_PROCESSING,
				BiomeSelectors.categories(Biome.Category.UNDERGROUND), (s) -> {
					GenerationSettingsContext gen = s.getGenerationSettings();
					gen.removeBuiltInFeature(ConfiguredFeatures.PATCH_TALL_GRASS_2);
					gen.removeBuiltInFeature(ConfiguredFeatures.PLAIN_VEGETATION);
					gen.removeBuiltInFeature(ConfiguredFeatures.FLOWER_PLAIN_DECORATED);
					gen.removeBuiltInFeature(ConfiguredFeatures.PATCH_GRASS_PLAIN);
					gen.removeBuiltInFeature(ConfiguredFeatures.DISK_SAND);
					gen.removeBuiltInFeature(ConfiguredFeatures.DISK_CLAY);
					gen.removeBuiltInFeature(ConfiguredFeatures.DISK_GRAVEL);
				});
	}
	
}
