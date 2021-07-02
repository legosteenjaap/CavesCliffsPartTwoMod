package io.github.akashiikun.ccgen.api;

import java.util.function.Supplier;

import com.mojang.datafixers.util.Function10;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class IceFloorFeatureConfig implements FeatureConfig {
	   @SuppressWarnings("unchecked")
	public static final Codec<IceFloorFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
	      return instance.group(Identifier.CODEC.fieldOf("replaceable").forGetter((IceFloorFeatureConfig) -> {
	         return IceFloorFeatureConfig.replaceable;
	      }), BlockStateProvider.TYPE_CODEC.fieldOf("ground_state").forGetter((IceFloorFeatureConfig) -> {
	         return IceFloorFeatureConfig.groundState;
	      }), ConfiguredFeature.REGISTRY_CODEC.fieldOf("icefloor_feature").forGetter((iceFloorPatchFeatureConfig) -> {
	          return iceFloorPatchFeatureConfig.iceFloorFeature;
	      }), VerticalSurfaceType.CODEC.fieldOf("surface").forGetter((IceFloorFeatureConfig) -> {
	         return IceFloorFeatureConfig.surface;
	      }), IntProvider.createValidatingCodec(1, 128).fieldOf("depth").forGetter((IceFloorFeatureConfig) -> {
	         return IceFloorFeatureConfig.depth;
	      }), Codec.floatRange(0.0F, 1.0F).fieldOf("extra_bottom_block_chance").forGetter((IceFloorFeatureConfig) -> {
	         return IceFloorFeatureConfig.extraBottomBlockChance;
	      }), Codec.intRange(1, 256).fieldOf("vertical_range").forGetter((IceFloorFeatureConfig) -> {
	         return IceFloorFeatureConfig.verticalRange;
	      }), Codec.floatRange(0.0F, 1.0F).fieldOf("ice_floor_chance").forGetter((IceFloorFeatureConfig) -> {
	         return IceFloorFeatureConfig.iceFloorChance;
	      }), IntProvider.VALUE_CODEC.fieldOf("xz_radius").forGetter((IceFloorFeatureConfig) -> {
	         return IceFloorFeatureConfig.horizontalRadius;
	      }), Codec.floatRange(0.0F, 1.0F).fieldOf("extra_edge_column_chance").forGetter((IceFloorFeatureConfig) -> {
	         return IceFloorFeatureConfig.extraEdgeColumnChance;
	      })).apply(instance, (IceFloorFeatureConfig::new));
	   });
	   public final Identifier replaceable;
	   public final BlockStateProvider groundState;
	   public final Supplier<ConfiguredFeature<?, ?>> iceFloorFeature;
	   public final VerticalSurfaceType surface;
	   public final IntProvider depth;
	   public final float extraBottomBlockChance;
	   public final int verticalRange;
	   public final float iceFloorChance;
	   public final IntProvider horizontalRadius;
	   public final float extraEdgeColumnChance;

	   public IceFloorFeatureConfig(Identifier replaceable, BlockStateProvider groundState, Supplier<ConfiguredFeature<?, ?>> iceFloorFeature, VerticalSurfaceType surface, IntProvider depth, float extraBottomBlockChance, int verticalRange, float iceFloorChance, IntProvider horizontalRadius, float extraEdgeColumnChance) {
	      this.replaceable = replaceable;
	      this.groundState = groundState;
	      this.iceFloorFeature = iceFloorFeature;
	      this.surface = surface;
	      this.depth = depth;
	      this.extraBottomBlockChance = extraBottomBlockChance;
	      this.verticalRange = verticalRange;
	      this.iceFloorChance = iceFloorChance;
	      this.horizontalRadius = horizontalRadius;
	      this.extraEdgeColumnChance = extraEdgeColumnChance;
	   }
	}
