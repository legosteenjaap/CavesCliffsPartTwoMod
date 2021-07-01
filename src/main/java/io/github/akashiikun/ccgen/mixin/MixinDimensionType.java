package io.github.akashiikun.ccgen.mixin;

import io.github.akashiikun.ccgen.ConfigValues;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.source.HorizontalVoronoiBiomeAccessType;
import net.minecraft.world.biome.source.VoronoiBiomeAccessType;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.OptionalLong;

@Mixin(DimensionType.class)
public class MixinDimensionType {
	
	@Shadow @Final @Mutable
    public static final Identifier THE_NETHER_ID = new Identifier("the_nether");

    @Shadow @Final @Mutable
    protected static final DimensionType THE_NETHER = DimensionType.create(OptionalLong.of(18000L), false, true, true, false, 8.0D, false, true, false, true, false, 0, 256, 128, VoronoiBiomeAccessType.INSTANCE, BlockTags.INFINIBURN_NETHER.getId(), THE_NETHER_ID, 0.1F);
	
	@Shadow @Final @Mutable
    public static final Identifier THE_END_ID = new Identifier("the_end");

    @Shadow @Final @Mutable
    protected static final DimensionType THE_END = DimensionType.create(OptionalLong.of(6000L), false, false, false, false, 1.0D, true, false, false, false, true, ConfigValues.minYEnd, ConfigValues.heightEnd, ConfigValues.logicalHeightEnd, VoronoiBiomeAccessType.INSTANCE, BlockTags.INFINIBURN_END.getId(), THE_END_ID, 0.0F);

    @Shadow @Final @Mutable
    public static final Identifier OVERWORLD_ID = new Identifier("overworld");

    @Shadow @Final @Mutable
    protected static final DimensionType OVERWORLD = DimensionType.create(OptionalLong.empty(), true, false, false, true, 1.0D, false, false, true, false, true, ConfigValues.minYOverworld, ConfigValues.heightOverworld, ConfigValues.logicalHeightOverworld, HorizontalVoronoiBiomeAccessType.INSTANCE, BlockTags.INFINIBURN_OVERWORLD.getId(), OVERWORLD_ID, 0.0F);
}
