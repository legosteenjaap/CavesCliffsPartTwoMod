package io.github.akashiikun.ccgen.mixin;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
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
        addMineables(builder, true);
    }

    /**
     * @author AkashiiKun
     */
    @Overwrite
    public static void addMineables(GenerationSettings.Builder builder, boolean bl) {
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
    }

    /**
     * @author AkashiiKun
     */
    @Overwrite
    public static void addDripstone(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.RARE_DRIPSTONE_CLUSTER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.RARE_SMALL_DRIPSTONE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.DRIPSTONE_CLUSTER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.SMALL_DRIPSTONE);
    }

    /**
     * @author AkashiiKun
     */
    @Overwrite
    public static void addDefaultOres(GenerationSettings.Builder builder) {
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
    }


    /**
     * @author AkashiiKun
     */
    @Overwrite
    public static void addEmeraldOre(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.PROTOTYPE_ORE_EMERALD);
    }

    /**
     * @author AkashiiKun
     */
    @Overwrite
    public static void addSprings(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.PROTOTYPE_SPRING_WATER);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPRING_LAVA);
    }

    /**
     * @author AkashiiKun
     */
    @Overwrite
    public static void addInfestedStone(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ConfiguredFeatures.PROTOTYPE_ORE_INFESTED);
    }

    /**
     * @author AkashiiKun
     */
    @Overwrite
    public static void addFossils(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, ConfiguredFeatures.PROTOTYPE_FOSSIL_UPPER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, ConfiguredFeatures.PROTOTYPE_FOSSIL_LOWER);
    }
}
