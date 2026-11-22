package com.bigdious.endgarden.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HangingRootsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class WhisperiaHangingsBlock extends HangingRootsBlock {
	protected static final VoxelShape SHAPE = Block.box(2.0F, 0.0F, 2.0F, 14.0F, 16.0F, 14.0F);;

	public WhisperiaHangingsBlock(Properties properties) {
		super(properties);
	}

	protected VoxelShape getShape(BlockState p_153342_, BlockGetter p_153343_, BlockPos p_153344_, CollisionContext p_153345_) {
		return SHAPE;
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader levelReader, BlockPos pos) {
		BlockPos blockpos = pos.above();
		BlockState blockstate = levelReader.getBlockState(blockpos);
		return blockstate.isFaceSturdy(levelReader, blockpos, Direction.DOWN) || blockstate.is(BlockTags.LEAVES);
	}

	@Override
	protected float getShadeBrightness(BlockState p_308911_, BlockGetter p_308952_, BlockPos p_308918_) {
		return 1.0F;
	}
}
