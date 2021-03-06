package io.github.akashiikun.ccgen.mixin.structure;

import io.github.akashiikun.ccgen.core.api.CaveLayer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.feature.WoodlandMansionFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Set;

//<>

/**
 * @author TelepathicGrunt
 */
@Mixin(WoodlandMansionFeature.class)
public class WoodlandMansionFeatureMixin {
    @Redirect(method = "shouldStartAt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/source/BiomeSource;getBiomesInArea(IIII)Ljava/util/Set;"))
    private Set<Biome> cba$getSurfaceBiomes(BiomeSource source, int x, int y, int z, int radius) {
        Set<Biome> biomeSet = source.getBiomesInArea(x, y, z, radius);
        biomeSet.removeIf(CaveLayer.CAVE_BIOME_LIST::contains);
        return biomeSet;
    }
}