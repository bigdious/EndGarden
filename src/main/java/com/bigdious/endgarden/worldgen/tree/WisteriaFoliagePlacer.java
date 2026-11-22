package com.bigdious.endgarden.worldgen.tree;

import com.bigdious.endgarden.init.EGBlocks;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.material.Fluids;

public class WisteriaFoliagePlacer extends FoliagePlacer {
	//altered copy of CherryFoliagePlacer
	public static final MapCodec<WisteriaFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((p_273246_) -> foliagePlacerParts(p_273246_).and(p_273246_.group(IntProvider.codec(4, 16).fieldOf("height").forGetter((p_273527_) -> p_273527_.height), BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter((p_161248_) -> p_161248_.hangProvider), Codec.floatRange(0.0F, 1.0F).fieldOf("wide_bottom_layer_hole_chance").forGetter((p_273760_) -> p_273760_.wideBottomLayerHoleChance), Codec.floatRange(0.0F, 1.0F).fieldOf("corner_hole_chance").forGetter((p_273020_) -> p_273020_.wideBottomLayerHoleChance), Codec.floatRange(0.0F, 1.0F).fieldOf("hanging_leaves_chance").forGetter((p_273148_) -> p_273148_.hangingLeavesChance))).apply(p_273246_, WisteriaFoliagePlacer::new));
	private final IntProvider height;
	public final BlockStateProvider hangProvider;
	private final float wideBottomLayerHoleChance;
	private final float cornerHoleChance;
	private final float hangingLeavesChance;

	public WisteriaFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height, BlockStateProvider hangProvider, float wideBottomLayerHoleChance, float cornerHoleChance, float hangingLeavesChance) {
		super(radius, offset);
		this.hangProvider = hangProvider;
		this.height = height;
		this.wideBottomLayerHoleChance = wideBottomLayerHoleChance;
		this.cornerHoleChance = cornerHoleChance;
		this.hangingLeavesChance = hangingLeavesChance;
	}

	protected FoliagePlacerType<?> type() {
		return EGTreestuffPlacerTypes.WISTERIA_FOLIAGE_PLACER.get();
	}

	protected void createFoliage(LevelSimulatedReader level, FoliagePlacer.FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliagePlacer.FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
		boolean flag = attachment.doubleTrunk();
		BlockPos blockpos = attachment.pos().above(offset);
		int i = foliageRadius + attachment.radiusOffset() - 2;
		this.placeLeavesRow(level, blockSetter, random, config, blockpos, i - 1, foliageHeight - 3, flag);
		this.placeWisteriaLeavesRow(level, blockSetter, random, config, blockpos, i , foliageHeight - 4, flag);

		for(int j = foliageHeight - 5; j >= 0; --j) {
			this.placeWisteriaLeavesRow(level, blockSetter, random, config, blockpos, i, j, flag);
		}

		this.placeWisteriaLeavesRowWithHangingLeavesBelow(level, blockSetter, random, config, blockpos, i, -1, flag, this.hangingLeavesChance);
		this.placeWisteriaLeavesRowWithHangingLeavesBelow(level, blockSetter, random, config, blockpos, i - 1, -2, flag, this.hangingLeavesChance);
	}

	protected void placeWisteriaLeavesRow(LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration treeConfiguration, BlockPos pos, int range, int localY, boolean large) {
		int i = large ? 1 : 0;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for(int j = -range; j <= range + i; ++j) {
			for(int k = -range; k <= range + i; ++k) {
				if (!this.shouldSkipLocationSigned(random, j, localY, k, range, large)) {
					if ((j==-range || j==range+i|| k==-range || k==range+i)) {
					blockpos$mutableblockpos.setWithOffset(pos, j, localY, k);
					tryPlaceLeafWithHang(level, foliageSetter, random, treeConfiguration, blockpos$mutableblockpos, false, this.hangProvider);
				} else {
						if (random.nextFloat() < 0.6) {
							blockpos$mutableblockpos.setWithOffset(pos, j, localY, k);
							tryPlaceLeafWithHang(level, foliageSetter, random, treeConfiguration, blockpos$mutableblockpos, false, this.hangProvider);
						}
					}
				}
			}
		}

	}

	protected final void placeWisteriaLeavesRowWithHangingLeavesBelow(LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration treeConfiguration, BlockPos pos, int range, int localY, boolean large, float hangingLeavesChance) {
		this.placeWisteriaLeavesRow(level, foliageSetter, random, treeConfiguration, pos, range, localY, large);
		int i = large ? 1 : 0;
		BlockPos blockpos = pos.below();
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for(Direction direction : Direction.Plane.HORIZONTAL) {
			Direction direction1 = direction.getClockWise();
			int j = direction1.getAxisDirection() == Direction.AxisDirection.POSITIVE ? range + i : range;
			blockpos$mutableblockpos.setWithOffset(pos, 0, localY - 1, 0).move(direction1, j).move(direction, -range);
			int k = -range;

			while(k < range + i) {
				boolean flag = foliageSetter.isSet(blockpos$mutableblockpos.move(Direction.UP));
				blockpos$mutableblockpos.move(Direction.DOWN);
				tryPlaceExtension(level, foliageSetter, random, treeConfiguration, hangingLeavesChance, blockpos, blockpos$mutableblockpos, this.hangProvider);
				++k;
				blockpos$mutableblockpos.move(direction);
			}
		}

	}

	protected static boolean tryPlaceLeafWithHang(LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration treeConfiguration, BlockPos pos, Boolean guaranteeHanging, BlockStateProvider hangings) {
		if (!TreeFeature.validTreePos(level, pos)) {
			return false;
		} else {
			BlockState blockstate = treeConfiguration.foliageProvider.getState(random, pos);
			if (blockstate.hasProperty(BlockStateProperties.WATERLOGGED)) {
				blockstate = blockstate.setValue(BlockStateProperties.WATERLOGGED, level.isFluidAtPosition(pos, (p_225638_) -> p_225638_.isSourceOfType(Fluids.WATER)));
			}
			if (level.isStateAtPosition(pos.below(), BlockBehaviour.BlockStateBase::isAir)) {
				foliageSetter.set(pos, blockstate);
				if (level.isStateAtPosition(pos.below(), BlockBehaviour.BlockStateBase::isAir) && (guaranteeHanging || random.nextFloat() > 0.8F)) {
					foliageSetter.set(pos.below(), hangings.getState(random, pos));
				}
			}
			return true;
		}
	}


	private static boolean tryPlaceExtension(LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration treeConfiguration, float extensionChance, BlockPos logPos, BlockPos.MutableBlockPos pos, BlockStateProvider hangings) {
		if (pos.distManhattan(logPos) >= 7) {
			return false;
		} else {
			return random.nextFloat() > extensionChance ? false : tryPlaceLeafWithHang(level, foliageSetter, random, treeConfiguration, pos, true, hangings);
		}
	}

	public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
		return this.height.sample(random);
	}

	protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
		if (localY == -1 && (localX == range || localZ == range) && random.nextFloat() < this.wideBottomLayerHoleChance) {
			return true;
		} else {
			boolean flag = localX == range && localZ == range;
			boolean flag1 = range > 2;
			return flag1 ? flag || localX + localZ > range * 2 - 2 && random.nextFloat() < this.cornerHoleChance : flag && random.nextFloat() < this.cornerHoleChance;
		}
	}
}