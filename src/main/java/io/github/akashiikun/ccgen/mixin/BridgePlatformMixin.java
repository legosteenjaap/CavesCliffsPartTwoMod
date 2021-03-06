package io.github.akashiikun.ccgen.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.structure.NetherFortressGenerator;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

@Mixin(NetherFortressGenerator.BridgePlatform.class)
public class BridgePlatformMixin {
	@Shadow private boolean hasBlazeSpawner;
	
	/**
	 * @author Legosteenjaap
	 */
	
    @Inject(method = "generate", at = @At(value = "HEAD"), cancellable = false)
	public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
    	if (hasBlazeSpawner) hasBlazeSpawner = false;
	}
}
