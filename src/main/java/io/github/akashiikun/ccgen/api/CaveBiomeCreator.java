package io.github.akashiikun.ccgen.api;

import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders;

public class CaveBiomeCreator {

	private static int getSkyColor(float temperature) {
		float f = temperature / 3.0F;
		f = MathHelper.clamp(f, -1.0F, 1.0F);
		return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
	}

	public static Biome createIceCaves() {
		SpawnSettings.Builder builder = new SpawnSettings.Builder();
		DefaultBiomeFeatures.addBatsAndMonsters(builder);
		GenerationSettings.Builder builder2 = (new GenerationSettings.Builder())
				.surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
		DefaultBiomeFeatures.addDefaultUndergroundStructures(builder2);
		builder2.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL);
		DefaultBiomeFeatures.addLandCarvers(builder2);
		DefaultBiomeFeatures.addDefaultLakes(builder2);
		DefaultBiomeFeatures.addAmethystGeodes(builder2);
		DefaultBiomeFeatures.addDungeons(builder2);
		DefaultBiomeFeatures.addMineables(builder2);
		DefaultBiomeFeatures.addDefaultOres(builder2);
		DefaultBiomeFeatures.addSprings(builder2);
		DefaultBiomeFeatures.addFrozenTopLayer(builder2);
		//builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredCaveBiomeFeatures.SNOW_PATCH);
		//builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredCaveBiomeFeatures.CONFIGURED_ICE_CAVES_FLOOR);
		//builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.HUGE_BROWN_MUSHROOM);
		return (new Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.UNDERGROUND)
				.depth(0.125F).scale(0.05F).temperature(0.0F).downfall(0.4F)
				.effects((new BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463)
						.skyColor(getSkyColor(0.8F)).moodSound(BiomeMoodSound.CAVE).build())
				.spawnSettings(builder.build()).generationSettings(builder2.build()).build();
	}

}
