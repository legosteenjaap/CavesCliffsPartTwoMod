package io.github.akashiikun.ccgen.mixin;

import java.util.Map;
import java.util.Optional;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.Maps;

import io.github.akashiikun.ccgen.ConfigValues;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.GenerationShapeConfig;
import net.minecraft.world.gen.chunk.NoiseSamplingConfig;
import net.minecraft.world.gen.chunk.SlideConfig;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.feature.StructureFeature;

@Mixin(ChunkGeneratorSettings.class)
public class MixinChunkGeneratorSettings {

    @Shadow @Final @Mutable
    private final StructuresConfig structuresConfig;
    @Shadow @Final @Mutable
    private final GenerationShapeConfig generationShapeConfig;
    @Shadow @Final @Mutable
    private final BlockState defaultBlock;
    @Shadow @Final @Mutable
    private final BlockState defaultFluid;
    @Shadow @Final @Mutable
    private final int bedrockCeilingY;
    @Shadow @Final @Mutable
    private final int bedrockFloorY;
    @Shadow @Final @Mutable
    private final int seaLevel;
    @Shadow @Final @Mutable
    private final int minSurfaceLevel;
    @Shadow @Final @Mutable
    private final boolean mobGenerationDisabled;
    @Shadow @Final @Mutable
    private final boolean aquifers;
    @Shadow @Final @Mutable
    private final boolean noiseCaves;
    @Shadow @Final @Mutable
    private final boolean deepslate;
    @Shadow @Final @Mutable
    private final boolean oreVeins;
    @Shadow @Final @Mutable
    private final boolean noodleCaves;


    public MixinChunkGeneratorSettings(StructuresConfig structuresConfig, GenerationShapeConfig generationShapeConfig, BlockState defaultBlock, BlockState defaultFluid, int bedrockCeilingY, int bedrockFloorY, int seaLevel, int minSurfaceLevel, boolean mobGenerationDisabled, boolean aquifers, boolean noiseCaves, boolean deepslate, boolean oreVeins, boolean noodleCaves) {
        this.structuresConfig = structuresConfig;
        this.generationShapeConfig = generationShapeConfig;
        this.defaultBlock = defaultBlock;
        this.defaultFluid = defaultFluid;
        this.bedrockCeilingY = bedrockCeilingY;
        this.bedrockFloorY = bedrockFloorY;
        this.seaLevel = seaLevel;
        this.minSurfaceLevel = minSurfaceLevel;
        this.mobGenerationDisabled = mobGenerationDisabled;
        this.aquifers = aquifers;
        this.noiseCaves = noiseCaves;
        this.deepslate = deepslate;
        this.oreVeins = oreVeins;
        this.noodleCaves = noodleCaves;
    }

    /**
	 * @author Legosteenjaap
	 */
    
    @Inject(method = "createIslandSettings", at = @At("TAIL"), cancellable = true)
    private static void createIslandSettings(StructuresConfig structuresConfig, BlockState defaultBlock, BlockState defaultFluid, boolean bl, boolean bl2, CallbackInfoReturnable<MixinChunkGeneratorSettings> cir) {
        cir.setReturnValue(new MixinChunkGeneratorSettings(structuresConfig, GenerationShapeConfig.create(ConfigValues.minYEnd, ConfigValues.generationHeightEnd, new NoiseSamplingConfig(2.0D, 1.0D, 80.0D, 160.0D), new SlideConfig(-3000, 64, -46), new SlideConfig(-30, 7, 1), 2, 1, 0.0D, 0.0D, true, false, bl2, false), defaultBlock, defaultFluid, Integer.MIN_VALUE, Integer.MIN_VALUE, ConfigValues.minYEnd, 0, bl, false, false, false, false, false));
    }
    
    @Inject(method = "createUndergroundSettings", at = @At("TAIL"), cancellable = true)
    private static void createUndergroundSettings(StructuresConfig structuresConfig, BlockState defaultBlock, BlockState defaultFluid, CallbackInfoReturnable<MixinChunkGeneratorSettings> cir) {
        Map<StructureFeature<?>, StructureConfig> map = Maps.newHashMap(StructuresConfig.DEFAULT_STRUCTURES);
        map.put(StructureFeature.RUINED_PORTAL, new StructureConfig(25, 10, 34222645));
        cir.setReturnValue(new MixinChunkGeneratorSettings(new StructuresConfig(Optional.ofNullable(structuresConfig.getStronghold()), map), GenerationShapeConfig.create(ConfigValues.minYNether, ConfigValues.generationHeightNether, new NoiseSamplingConfig(1.0D, 3.0D, 80.0D, 60.0D), new SlideConfig(120, 3, 0), new SlideConfig(320, 4, -1), 1, 2, 0.0D, 0.019921875D, false, false, false, false), defaultBlock, defaultFluid, 0, 0, ConfigValues.lavaSeaLevel, ConfigValues.minYNether, false, false, false, false, false, false));
     }
    
    @Inject(method = "createSurfaceSettings", at = @At("TAIL"), cancellable = true)
    private static void createSurfaceSettings(StructuresConfig structuresConfig, boolean amplified, CallbackInfoReturnable<MixinChunkGeneratorSettings> cir) {
        double d = 0.9999999814507745D;
        cir.setReturnValue(new MixinChunkGeneratorSettings(structuresConfig, GenerationShapeConfig.create(ConfigValues.minYOverworld, ConfigValues.heightOverworld, new NoiseSamplingConfig(0.9999999814507745D, 0.9999999814507745D, 80.0D, 160.0D), new SlideConfig(-10, 3, 0), new SlideConfig(15, 3, 0), 1, 2, 1.0D, -0.46875D, true, true, false, amplified), Blocks.STONE.getDefaultState(), Blocks.WATER.getDefaultState(), Integer.MIN_VALUE, 0, ConfigValues.seaLevel, ConfigValues.minSurfaceLevel, false, ConfigValues.aquifers, ConfigValues.noiseCaves, ConfigValues.deepslate, ConfigValues.usePrototypeGeneration, ConfigValues.noodleCaves));
    }

}
