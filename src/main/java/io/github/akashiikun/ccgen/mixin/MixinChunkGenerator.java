package io.github.akashiikun.ccgen.mixin;

import java.util.Random;
import java.util.function.Predicate;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeCoords;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkGenerator.class)
public class MixinChunkGenerator {

    @Unique
    private static ChunkRegion region;

    @Shadow @Final protected BiomeSource populationSource;

    @Redirect(method = "generateStrongholdPositions", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/source/BiomeSource;locateBiome(IIIILjava/util/function/Predicate;Ljava/util/Random;)Lnet/minecraft/util/math/BlockPos;"))
    private BlockPos findY64Biome(BiomeSource source, int x, int y, int z, int radius, Predicate<Biome> predicate, Random random) {
        return source.locateBiome(x, y, z, radius, predicate, random);
    }

    @Redirect(method = "carve", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/source/BiomeSource;getBiomeForNoiseGen(III)Lnet/minecraft/world/biome/Biome;"))
    private Biome carveAtY64(BiomeSource storage, int biomeX, int biomeY, int biomeZ) {
        return storage.getBiomeForNoiseGen(biomeX, biomeY, biomeZ);
    }

    @Redirect(method = "locateStructure", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos$Mutable;set(III)Lnet/minecraft/util/math/BlockPos$Mutable;"))
    private BlockPos.Mutable setMutableAtY64(BlockPos.Mutable mutable, int x, int y, int z) {
        return mutable.set(x, y, z);
    }

    /**
     * @author SuperCoder79 & AkashiiKun
     */

    @Inject(method = "generateFeatures", at = @At(value = "TAIL"), cancellable = true)
    public void generateFeatures(ChunkRegion region, StructureAccessor accessor, CallbackInfo ci) {
        ChunkPos chunkPos = region.getCenterPos();
        int i = chunkPos.getStartX();
        int j = chunkPos.getStartZ();
        BlockPos blockPos = new BlockPos(i, region.getBottomY(), j);
        Biome underground = this.populationSource.getBiomeForNoiseGen(BiomeCoords.fromChunk(chunkPos.x) + BiomeCoords.fromBlock(8), 0, BiomeCoords.fromChunk(chunkPos.z) + BiomeCoords.fromBlock(8));
        Biome biome = this.populationSource.getBiomeForNoiseGen(BiomeCoords.fromChunk(chunkPos.x) + BiomeCoords.fromBlock(8), 64, BiomeCoords.fromChunk(chunkPos.z) + BiomeCoords.fromBlock(8));
        ChunkRandom chunkRandom = new ChunkRandom();
        long l = chunkRandom.setPopulationSeed(region.getSeed(), i, j);

        try {
            biome.generateFeatureStep(accessor, (ChunkGenerator) (Object) this, region, l, chunkRandom, blockPos);
            underground.generateFeatureStep(accessor, (ChunkGenerator) (Object) this, region, l, chunkRandom, blockPos);
        } catch (Exception var13) {
            CrashReport crashReport = CrashReport.create(var13, "Cave Biome decoration");
            crashReport.addElement("Generation").add("CenterX", (Object)chunkPos.x).add("CenterZ", (Object)chunkPos.z).add("Seed", (Object)l).add("UndergroundBiome", (Object)underground);
            throw new CrashException(crashReport);
        }
        ci.cancel();
    }
}
