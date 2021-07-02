package io.github.akashiikun.ccgen.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import me.jellysquid.mods.sodium.client.world.biome.BiomeCache;
//import me.jellysquid.mods.sodium.client.world.biome.BiomeCache;
import net.minecraft.client.MinecraftClient;

@Mixin(BiomeCache.class)
public class MixinSodiumBiomeCache {
	
	@ModifyVariable(method = "getBiome", at = @At("HEAD"), ordinal = 1, remap = false)
	private int injected(int y) {
	  return MinecraftClient.getInstance().world.getTopY();
	}
	
}