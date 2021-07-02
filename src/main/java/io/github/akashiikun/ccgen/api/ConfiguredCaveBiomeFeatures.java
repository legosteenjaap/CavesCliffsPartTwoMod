package io.github.akashiikun.ccgen.api;

import io.github.akashiikun.ccgen.CavesCliffsGenerationMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

public class ConfiguredCaveBiomeFeatures<FC extends FeatureConfig>  {
	public static ConfiguredFeature<IceFloorFeatureConfig, ?> CONFIGURED_ICE_CAVES_FLOOR;
	public static Feature<IceFloorFeatureConfig> ICE_CAVES_FLOOR;
	public static ConfiguredFeature<SimpleBlockFeatureConfig, ?> SNOW_PATCH;

	private static final String modid = CavesCliffsGenerationMod.modid;
	
	@SuppressWarnings("unchecked")
	private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String id,
			ConfiguredFeature<?, ?> configuredFeature) {
		return (ConfiguredFeature<FC, ?>) Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier (modid, id),
				configuredFeature);
	}

	private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
		return (F) Registry.register(Registry.FEATURE, (String) name, feature);
	}

	public static void init() {
		ICE_CAVES_FLOOR = register("ice_caves_floor", new IceFloorFeature(IceFloorFeatureConfig.CODEC));

		SNOW_PATCH = register("snow_patch", Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(
				new SimpleBlockStateProvider(Blocks.SNOW_BLOCK.getDefaultState()))));

		CONFIGURED_ICE_CAVES_FLOOR = register("ice_caves_floor",
				ICE_CAVES_FLOOR.configure(new IceFloorFeatureConfig(BlockTags.MOSS_REPLACEABLE.getId(),
						new SimpleBlockStateProvider(Blocks.SNOW_BLOCK.getDefaultState()), () -> {return SNOW_PATCH;},
						VerticalSurfaceType.FLOOR, ConstantIntProvider.create(1), 0.8F, 5, 0.8F,
						UniformIntProvider.create(4, 7), 0.3F)));

	}

	static DataPool.Builder<BlockState> method_35926() {
		return DataPool.builder();
	}

	/*protected static final class Decorators {
		public static final BeehiveTreeDecorator VERY_RARE_BEEHIVES_TREES = new BeehiveTreeDecorator(0.002F);
		public static final BeehiveTreeDecorator REGULAR_BEEHIVES_TREES = new BeehiveTreeDecorator(0.02F);
		public static final BeehiveTreeDecorator MORE_BEEHIVES_TREES = new BeehiveTreeDecorator(0.05F);
		public static final ConfiguredDecorator<HeightmapDecoratorConfig> HEIGHTMAP;
		public static final ConfiguredDecorator<HeightmapDecoratorConfig> TOP_SOLID_HEIGHTMAP;
		public static final ConfiguredDecorator<HeightmapDecoratorConfig> HEIGHTMAP_WORLD_SURFACE;
		public static final ConfiguredDecorator<HeightmapDecoratorConfig> HEIGHTMAP_OCEAN_FLOOR;
		public static final ConfiguredDecorator<HeightmapDecoratorConfig> HEIGHTMAP_SPREAD_DOUBLE;
		public static final RangeDecoratorConfig BOTTOM_TO_TOP;
		public static final RangeDecoratorConfig BOTTOM_TO_TOP_OFFSET_10;
		public static final RangeDecoratorConfig BOTTOM_TO_TOP_OFFSET_8;
		public static final RangeDecoratorConfig BOTTOM_TO_TOP_OFFSET_4;
		public static final RangeDecoratorConfig BOTTOM_TO_60;
		public static final ConfiguredDecorator<?> FIRE;
		public static final ConfiguredDecorator<?> SPREAD_32_ABOVE;
		public static final ConfiguredDecorator<?> HEIGHTMAP_OCEAN_FLOOR_NO_WATER;
		public static final ConfiguredDecorator<?> SQUARE_HEIGHTMAP_OCEAN_FLOOR_NO_WATER;
		public static final ConfiguredDecorator<?> SQUARE_HEIGHTMAP;
		public static final ConfiguredDecorator<?> SQUARE_HEIGHTMAP_SPREAD_DOUBLE;
		public static final ConfiguredDecorator<?> SQUARE_TOP_SOLID_HEIGHTMAP;
		public static final ConfiguredDecorator<?> DARK_OAK_TREE_HEIGHTMAP;

		static {
			HEIGHTMAP = Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.MOTION_BLOCKING));
			TOP_SOLID_HEIGHTMAP = Decorator.HEIGHTMAP
					.configure(new HeightmapDecoratorConfig(Heightmap.Type.OCEAN_FLOOR_WG));
			HEIGHTMAP_WORLD_SURFACE = Decorator.HEIGHTMAP
					.configure(new HeightmapDecoratorConfig(Heightmap.Type.WORLD_SURFACE_WG));
			HEIGHTMAP_OCEAN_FLOOR = Decorator.HEIGHTMAP
					.configure(new HeightmapDecoratorConfig(Heightmap.Type.OCEAN_FLOOR));
			HEIGHTMAP_SPREAD_DOUBLE = Decorator.HEIGHTMAP_SPREAD_DOUBLE
					.configure(new HeightmapDecoratorConfig(Heightmap.Type.MOTION_BLOCKING));
			BOTTOM_TO_TOP = new RangeDecoratorConfig(
					UniformHeightProvider.create(YOffset.getBottom(), YOffset.getTop()));
			BOTTOM_TO_TOP_OFFSET_10 = new RangeDecoratorConfig(
					UniformHeightProvider.create(YOffset.aboveBottom(10), YOffset.belowTop(10)));
			BOTTOM_TO_TOP_OFFSET_8 = new RangeDecoratorConfig(
					UniformHeightProvider.create(YOffset.aboveBottom(8), YOffset.belowTop(8)));
			BOTTOM_TO_TOP_OFFSET_4 = new RangeDecoratorConfig(
					UniformHeightProvider.create(YOffset.aboveBottom(4), YOffset.belowTop(4)));
			BOTTOM_TO_60 = new RangeDecoratorConfig(
					UniformHeightProvider.create(YOffset.getBottom(), YOffset.fixed(60)));
			FIRE = (ConfiguredDecorator) ((ConfiguredDecorator) Decorator.RANGE.configure(BOTTOM_TO_TOP_OFFSET_4)
					.spreadHorizontally()).repeatRandomly(5);
			SPREAD_32_ABOVE = Decorator.SPREAD_32_ABOVE.configure(NopeDecoratorConfig.INSTANCE);
			HEIGHTMAP_OCEAN_FLOOR_NO_WATER = HEIGHTMAP_OCEAN_FLOOR
					.decorate(Decorator.WATER_DEPTH_THRESHOLD.configure(new WaterDepthThresholdDecoratorConfig(0)));
			SQUARE_HEIGHTMAP_OCEAN_FLOOR_NO_WATER = (ConfiguredDecorator) HEIGHTMAP_OCEAN_FLOOR_NO_WATER
					.spreadHorizontally();
			SQUARE_HEIGHTMAP = (ConfiguredDecorator) HEIGHTMAP.spreadHorizontally();
			SQUARE_HEIGHTMAP_SPREAD_DOUBLE = (ConfiguredDecorator) HEIGHTMAP_SPREAD_DOUBLE.spreadHorizontally();
			SQUARE_TOP_SOLID_HEIGHTMAP = (ConfiguredDecorator) TOP_SOLID_HEIGHTMAP.spreadHorizontally();
			DARK_OAK_TREE_HEIGHTMAP = HEIGHTMAP_OCEAN_FLOOR_NO_WATER
					.decorate(Decorator.DARK_OAK_TREE.configure(DecoratorConfig.DEFAULT));
		}
	}*/

}
