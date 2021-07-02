package io.github.akashiikun.ccgen.mixin;

import net.minecraft.structure.MineshaftGenerator;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(MineshaftGenerator.MineshaftCorridor.class)
class MineshaftCorridorMixin {
	@Shadow private boolean hasSpawner;
	
	/**
	 * @author Legosteenjaap
	 */
	
    @Inject(method = "generate", at = @At(value = "HEAD"), cancellable = false)
	public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
    	if (hasSpawner) hasSpawner = false;
	}
}
