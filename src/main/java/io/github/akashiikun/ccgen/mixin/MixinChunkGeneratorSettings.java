package io.github.akashiikun.ccgen.mixin;

import io.github.akashiikun.ccgen.ConfigValues;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.chunk.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

    @Inject(method = "createSurfaceSettings", at = @At("TAIL"), cancellable = true)
    private static void createSurfaceSettings(StructuresConfig structuresConfig, boolean amplified, CallbackInfoReturnable<MixinChunkGeneratorSettings> cir) {
        double d = 0.9999999814507745D;
        cir.setReturnValue(new MixinChunkGeneratorSettings(structuresConfig, GenerationShapeConfig.create(ConfigValues.minYOverworld, ConfigValues.heightOverworld, new NoiseSamplingConfig(0.9999999814507745D, 0.9999999814507745D, 80.0D, 160.0D), new SlideConfig(-10, 3, 0), new SlideConfig(15, 3, 0), 1, 2, 1.0D, -0.46875D, true, true, false, amplified), Blocks.STONE.getDefaultState(), Blocks.WATER.getDefaultState(), Integer.MIN_VALUE, 0, ConfigValues.seaLevel, ConfigValues.minSurfaceLevel, false, ConfigValues.aquifers, ConfigValues.noiseCaves, ConfigValues.deepslate, ConfigValues.usePrototypeGeneration, ConfigValues.noodleCaves));
    }

}
