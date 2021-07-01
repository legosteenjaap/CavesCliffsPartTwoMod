package io.github.akashiikun.ccgen.mixin;

import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.source.HorizontalVoronoiBiomeAccessType;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.OptionalLong;

@Mixin(DimensionType.class)
public class MixinDimensionType {

    @Shadow @Final @Mutable
    public static final Identifier OVERWORLD_ID = new Identifier("overworld");

    @Shadow @Final @Mutable
    protected static final DimensionType OVERWORLD = DimensionType.create(OptionalLong.empty(), true, false, false, true, 1.0D, false, false, true, false, true, -64, 384, 384, HorizontalVoronoiBiomeAccessType.INSTANCE, BlockTags.INFINIBURN_OVERWORLD.getId(), OVERWORLD_ID, 0.0F);
}
