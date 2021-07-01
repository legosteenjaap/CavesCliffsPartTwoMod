package io.github.akashiikun.ccgen.mixin;

import io.github.akashiikun.ccgen.ConfigValues;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(DefaultBiomeFeatures.class)
public class MixinDefaultBiomeFeatures {

    /**
     * @author AkashiiKun
     */
    @Overwrite
    public static void addMineables(GenerationSettings.Builder builder) {
        addMineables(builder, false);
    }

    /**
     * @author AkashiiKun
     */
    @Overwrite
    public static void addMineables(GenerationSettings.Builder builder, boolean bl) {
        if (ConfigValues.usePrototypeGeneration) {

            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_DIRT);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_GRAVEL);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_GRANITE);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_DIORITE);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_ANDESITE);
            if (!bl) {
                builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PROTOTYPE_GLOW_LICHEN);
            }
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_TUFF);
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.RARE_DRIPSTONE_CLUSTER);
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.RARE_SMALL_DRIPSTONE);
        } else {
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_DIRT);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_GRAVEL);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_GRANITE);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_DIORITE);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_ANDESITE);
            if (!bl) {
                builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.GLOW_LICHEN);
            }

            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_TUFF);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_DEEPSLATE);
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.RARE_DRIPSTONE_CLUSTER);
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.RARE_SMALL_DRIPSTONE);
        }
    }

    /**
     * @author AkashiiKun
     */
    @Overwrite
    public static void addDefaultOres(GenerationSettings.Builder builder) {
        if(ConfigValues.usePrototypeGeneration) {
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_COAL_UPPER);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_COAL_LOWER);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_IRON_UPPER);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_IRON_MIDDLE);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_IRON_SMALL);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_GOLD);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_REDSTONE);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_REDSTONE_LOWER);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_DIAMOND);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_DIAMOND_LARGE);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_LAPIS);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_LAPIS_BURIED);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_COPPER);
        } else {
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_COAL);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_IRON);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_GOLD);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_REDSTONE);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_DIAMOND);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_LAPIS);
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_COPPER);
        }
    }


    /**
     * @author AkashiiKun
     */
    @Overwrite
    public static void addEmeraldOre(GenerationSettings.Builder builder) {
        if(ConfigValues.usePrototypeGeneration) {
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_EMERALD);
        } else {
            builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_EMERALD);
        }
    }

    /**
     * @author AkashiiKun
     */
    @Overwrite
    public static void addSprings(GenerationSettings.Builder builder) {
        if(ConfigValues.usePrototypeGeneration) {
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PROTOTYPE_SPRING_WATER);
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA);
        } else {
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_WATER);
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA);
        }
    }

    /**
     * @author AkashiiKun
     */
    @Overwrite
    public static void addInfestedStone(GenerationSettings.Builder builder) {
        if(ConfigValues.usePrototypeGeneration) {
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PROTOTYPE_ORE_INFESTED);
        } else {
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.ORE_INFESTED);
        }
    }

    /**
     * @author AkashiiKun
     */
    @Overwrite
    public static void addFossils(GenerationSettings.Builder builder) {
        if(ConfigValues.usePrototypeGeneration) {
            builder.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, ConfiguredFeatures.PROTOTYPE_FOSSIL_UPPER);
            builder.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, ConfiguredFeatures.PROTOTYPE_FOSSIL_LOWER);
        } else {
            builder.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, ConfiguredFeatures.FOSSIL);
        }
    }

    /**
     * @author AkashiiKun
     */
    @Overwrite
    public static void addLandCarvers(GenerationSettings.Builder builder) {
        if(ConfigValues.usePrototypeGeneration) {
            builder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.PROTOTYPE_CAVE);
            builder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.PROTOTYPE_CANYON);
            builder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.PROTOTYPE_CREVICE);
        } else {
            builder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CAVE);
            builder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CANYON);
        }
    }

    /**
     * @author AkashiiKun
     */
    @Overwrite
    public static void addLushCavesDecoration(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.LUSH_CAVES_CEILING_VEGETATION);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.CAVE_VINES);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.LUSH_CAVES_CLAY);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.LUSH_CAVES_VEGETATION);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.ROOTED_AZALEA_TREES);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SPORE_BLOSSOM);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.CLASSIC_VINES_CAVE_FEATURE);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PLAIN_VEGETATION);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.FLOWER_PLAIN_DECORATED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PATCH_GRASS_PLAIN);
        builder.structureFeature(ConfiguredStructureFeatures.MINESHAFT);
        builder.structureFeature(ConfiguredStructureFeatures.STRONGHOLD);
        builder.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, ConfiguredFeatures.MONSTER_ROOM);
    }

    /**
     * @author AkashiiKun
     */
    @Overwrite
    public static void addDripstone(GenerationSettings.Builder builder) {
        if(ConfigValues.usePrototypeGeneration) {
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.RARE_DRIPSTONE_CLUSTER);
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.RARE_SMALL_DRIPSTONE);
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.DRIPSTONE_CLUSTER);
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SMALL_DRIPSTONE);
        } else {
            builder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, ConfiguredFeatures.LARGE_DRIPSTONE);
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.DRIPSTONE_CLUSTER);
            builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SMALL_DRIPSTONE);
        }
        builder.structureFeature(ConfiguredStructureFeatures.MINESHAFT);
        builder.structureFeature(ConfiguredStructureFeatures.STRONGHOLD);
        builder.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, ConfiguredFeatures.MONSTER_ROOM);
    }

}
