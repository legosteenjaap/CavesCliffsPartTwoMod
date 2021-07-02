package io.github.akashiikun.ccgen.api;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

import com.mojang.serialization.Codec;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class IceFloorFeature extends Feature<IceFloorFeatureConfig> {
	   public IceFloorFeature(Codec<IceFloorFeatureConfig> codec) {
	      super(codec);
	   }

	   public boolean generate(FeatureContext<IceFloorFeatureConfig> context) {
	      System.out.println("hiiiiiiiiiii");
		   StructureWorldAccess structureWorldAccess = context.getWorld();
	      IceFloorFeatureConfig IceFloorFeatureConfig = (IceFloorFeatureConfig)context.getConfig();
	      Random random = context.getRandom();
	      BlockPos blockPos = context.getOrigin();
	      Predicate<BlockState> predicate = getReplaceablePredicate(IceFloorFeatureConfig);
	      int i = IceFloorFeatureConfig.horizontalRadius.get(random) + 1;
	      int j = IceFloorFeatureConfig.horizontalRadius.get(random) + 1;
	      Set<BlockPos> set = this.placeGroundAndGetPositions(structureWorldAccess, IceFloorFeatureConfig, random, blockPos, predicate, i, j);
	      this.generateIceFloor(context, structureWorldAccess, IceFloorFeatureConfig, random, set, i, j);
	      return !set.isEmpty();
	   }

	   protected Set<BlockPos> placeGroundAndGetPositions(StructureWorldAccess world, IceFloorFeatureConfig config, Random random, BlockPos pos, Predicate<BlockState> replaceable, int radiusX, int radiusZ) {
	      BlockPos.Mutable mutable = pos.mutableCopy();
	      BlockPos.Mutable mutable2 = mutable.mutableCopy();
	      Direction direction = config.surface.getDirection();
	      Direction direction2 = direction.getOpposite();
	      Set<BlockPos> set = new HashSet();

	      for(int i = -radiusX; i <= radiusX; ++i) {
	         boolean bl = i == -radiusX || i == radiusX;

	         for(int j = -radiusZ; j <= radiusZ; ++j) {
	            boolean bl2 = j == -radiusZ || j == radiusZ;
	            boolean bl3 = bl || bl2;
	            boolean bl4 = bl && bl2;
	            boolean bl5 = bl3 && !bl4;
	            if (!bl4 && (!bl5 || config.extraEdgeColumnChance != 0.0F && !(random.nextFloat() > config.extraEdgeColumnChance))) {
	               mutable.set((Vec3i)pos, i, 0, j);

	               int k;
	               for(k = 0; world.testBlockState(mutable, AbstractBlock.AbstractBlockState::isAir) && k < config.verticalRange; ++k) {
	                  mutable.move(direction);
	               }

	               for(k = 0; world.testBlockState(mutable, (blockStatex) -> {
	                  return !blockStatex.isAir();
	               }) && k < config.verticalRange; ++k) {
	                  mutable.move(direction2);
	               }

	               mutable2.set(mutable, (Direction)config.surface.getDirection());
	               BlockState blockState = world.getBlockState(mutable2);
	               if (world.isAir(mutable) && blockState.isSideSolidFullSquare(world, mutable2, config.surface.getDirection().getOpposite())) {
	                  int l = config.depth.get(random) + (config.extraBottomBlockChance > 0.0F && random.nextFloat() < config.extraBottomBlockChance ? 1 : 0);
	                  BlockPos blockPos = mutable2.toImmutable();
	                  boolean bl6 = this.placeGround(world, config, replaceable, random, mutable2, l);
	                  if (bl6) {
	                     set.add(blockPos);
	                  }
	               }
	            }
	         }
	      }

	      return set;
	   }

	   protected void generateIceFloor(FeatureContext<IceFloorFeatureConfig> context, StructureWorldAccess world, IceFloorFeatureConfig config, Random random, Set<BlockPos> positions, int radiusX, int radiusZ) {
	      Iterator var8 = positions.iterator();

	      while(var8.hasNext()) {
	         BlockPos blockPos = (BlockPos)var8.next();
	         if (config.iceFloorChance > 0.0F && random.nextFloat() < config.iceFloorChance) {
	        	 System.out.println("yes");
	            this.generateIceFloorFeature(world, config, context.getGenerator(), random, blockPos);
	         }
	      }

	   }

	   protected boolean generateIceFloorFeature(StructureWorldAccess world, IceFloorFeatureConfig config, ChunkGenerator generator, Random random, BlockPos pos) {
	      return ((ConfiguredFeature)config.iceFloorFeature.get()).generate(world, generator, random, pos.offset(config.surface.getDirection().getOpposite()));
	   }

	   protected boolean placeGround(StructureWorldAccess world, IceFloorFeatureConfig config, Predicate<BlockState> replaceable, Random random, BlockPos.Mutable pos, int depth) {
	      for(int i = 0; i < depth; ++i) {
	         BlockState blockState = config.groundState.getBlockState(random, pos);
	         BlockState blockState2 = world.getBlockState(pos);
	         if (!blockState.isOf(blockState2.getBlock())) {
	            if (!replaceable.test(blockState2)) {
	               return i != 0;
	            }

	            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
	            pos.move(config.surface.getDirection());
	         }
	      }

	      return true;
	   }

	   private static Predicate<BlockState> getReplaceablePredicate(IceFloorFeatureConfig config) {
	      Tag<Block> tag = BlockTags.getTagGroup().getTag(config.replaceable);
	      return tag == null ? (state) -> {
	         return true;
	      } : (state) -> {
	         return state.isIn(tag);
	      };
	   }
	}
