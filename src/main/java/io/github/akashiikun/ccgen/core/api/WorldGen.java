package io.github.akashiikun.ccgen.core.api;

import io.github.akashiikun.ccgen.CavesCliffsGenerationMod;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.ConfiguredFeatures;

@SuppressWarnings("deprecation")
public class WorldGen {

    private static final String modid = CavesCliffsGenerationMod.MOD_ID;


    public static void init () {
        BiomeModifications.create(new Identifier(modid, "lush_caves_gen")).add(ModificationPhase.POST_PROCESSING,
                BiomeSelectors.categories(Biome.Category.UNDERGROUND), (s) -> {
                    BiomeModificationContext.GenerationSettingsContext gen = s.getGenerationSettings();
                    gen.removeBuiltInFeature(ConfiguredFeatures.PATCH_TALL_GRASS_2);
                    gen.removeBuiltInFeature(ConfiguredFeatures.PLAIN_VEGETATION);
                    gen.removeBuiltInFeature(ConfiguredFeatures.FLOWER_PLAIN_DECORATED);
                    gen.removeBuiltInFeature(ConfiguredFeatures.PATCH_GRASS_PLAIN);
                    gen.removeBuiltInFeature(ConfiguredFeatures.DISK_SAND);
                    gen.removeBuiltInFeature(ConfiguredFeatures.DISK_CLAY);
                    gen.removeBuiltInFeature(ConfiguredFeatures.DISK_GRAVEL);
                });

        BiomeModifications.create(new Identifier(modid, "lush_caves_gen")).add(ModificationPhase.POST_PROCESSING,
                BiomeSelectors.excludeByKey(BiomeKeys.LUSH_CAVES), (s) -> {
                    s.getSpawnSettings().removeSpawnsOfEntityType(EntityType.AXOLOTL);
                });
        BiomeModifications.create(new Identifier(modid, "lush_caves_gen")).add(ModificationPhase.POST_PROCESSING,
                BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES), (s) -> {
                    s.getSpawnSettings().removeSpawnsOfEntityType(EntityType.AXOLOTL);
                    s.getSpawnSettings().addSpawn(SpawnGroup.UNDERGROUND_WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.AXOLOTL, 15, 4, 6));
                });
    }

}
