package io.github.akashiikun.ccgen.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import me.jellysquid.mods.sodium.client.world.biome.BiomeCache;
import net.minecraft.client.MinecraftClient;

@Mixin(BiomeCache.class)
public class MixinSodiumBiomeCache {
	/*@Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/ChunkSectionPos;getMinY()I", opcode = Opcodes.PUTFIELD))
	private int injected(ChunkSectionPos origin) {
		System.out.println(origin.getMinY()));
		return origin.getMinY();
	}*/
	
	@ModifyVariable(method = "getBiome", at = @At("HEAD"), ordinal = 1, remap = false)
	private int injected(int y) {
	  return MinecraftClient.getInstance().world.getTopY();
	}
	
}
