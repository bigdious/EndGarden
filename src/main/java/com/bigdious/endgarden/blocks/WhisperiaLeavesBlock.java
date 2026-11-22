package com.bigdious.endgarden.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

import java.util.OptionalInt;

public class WhisperiaLeavesBlock extends LeavesBlock {
	public WhisperiaLeavesBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected int getLightBlock(BlockState state, BlockGetter level, BlockPos pos) {
		return 1;
	}

	@Override
	protected float getShadeBrightness(BlockState p_308911_, BlockGetter p_308952_, BlockPos p_308918_) {
		return 1.0F;
	}

	@Override
	protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		level.setBlock(pos, updateDistance(state, level, pos), 3);
	}

	@Override
	protected boolean decaying(BlockState state) {
		return !state.getValue(PERSISTENT) && state.getValue(DISTANCE) > 4;
	}

	@Override
	protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (this.decaying(state)) {
			dropResources(state, level, pos);
			level.removeBlock(pos, false);
		}
	}

	@Override
	protected boolean isRandomlyTicking(BlockState state) {
		return state.getValue(DISTANCE) > 4 && !state.getValue(PERSISTENT);
	}

	@Override
	protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		int i = getDistanceAt(facingState) + 1;
		if (i != 1 || state.getValue(DISTANCE) != i) {
			level.scheduleTick(currentPos, this, 1);
		}

		return state;
	}

	private static BlockState updateDistance(BlockState state, LevelAccessor level, BlockPos pos) {
		int i = 7;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for(Direction direction : Direction.values()) {
			for (int d = 0; d < 3; d++) {
				blockpos$mutableblockpos.set(pos.getX() + direction.getStepX()*(d+1), pos.getY() + direction.getStepY()*(d+1), pos.getZ() + direction.getStepZ()*(d+1));
				i = Math.min(i, getDistanceAt(level.getBlockState(blockpos$mutableblockpos)) + 1);
				if (i == 1) {
					break;
				}
			}
		}

		return state.setValue(DISTANCE, i);
	}

	private static int getDistanceAt(BlockState neighbor) {
		return getOptionalDistanceAt(neighbor).orElse(7);
	}

	public static OptionalInt getOptionalDistanceAt(BlockState state) {
		if (state.is(BlockTags.LOGS)) {
			return OptionalInt.of(0);
		} else {
			return state.hasProperty(DISTANCE) ? OptionalInt.of(state.getValue(DISTANCE)) : OptionalInt.empty();
		}
	}

	@Override
	protected boolean propagatesSkylightDown(BlockState p_309084_, BlockGetter p_309133_, BlockPos p_309097_) {
		return true;
	}
}
