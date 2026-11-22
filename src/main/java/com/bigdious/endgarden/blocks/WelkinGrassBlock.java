package com.bigdious.endgarden.blocks;

import com.bigdious.endgarden.init.EGItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.ItemAbilities;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class WelkinGrassBlock extends Block implements SimpleWaterloggedBlock {
	protected static final VoxelShape SHAPE = Block.box(0.0D, 9.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public WelkinGrassBlock(Properties properties) {
		super(properties);
		this.registerDefaultState((this.stateDefinition.any()).setValue(WATERLOGGED, false));
	}
	@Override
	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext p_221508_) {
		FluidState fluidstate = p_221508_.getLevel().getFluidState(p_221508_.getClickedPos());
		boolean flag = fluidstate.getType() == Fluids.WATER;
		return super.getStateForPlacement(p_221508_).setValue(WATERLOGGED, flag);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	protected boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}
	@Override
	protected BlockState updateShape(BlockState p_221514_, Direction p_221515_, BlockState p_221516_, LevelAccessor p_221517_, BlockPos p_221518_, BlockPos p_221519_) {
		if (p_221514_.getValue(WATERLOGGED)) {
			p_221517_.scheduleTick(p_221518_, Fluids.WATER, Fluids.WATER.getTickDelay(p_221517_));
		}

		return super.updateShape(p_221514_, p_221515_, p_221516_, p_221517_, p_221518_, p_221519_);
	}

	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		if (stack.is(Items.BONE_MEAL) && !level.isClientSide()) {
			stack.consume(1, player);
			popResource(level, pos.above(), new ItemStack(EGItems.WELKIN_GRASS.get()));
			if (level instanceof ServerLevel serverLevel) {
				spawnParticlesOnBlockFace(serverLevel, pos, ParticleTypes.HAPPY_VILLAGER, UniformInt.of(4, 6), Direction.UP, () -> new Vec3(Mth.nextDouble(level.random, -0.5, 0.5), Mth.nextDouble(level.random, -0.5, 0.5), Mth.nextDouble(level.random, -0.5, 0.5)), 0.6);
			}
			level.playSound(null, pos, SoundEvents.MOSS_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
			return ItemInteractionResult.sidedSuccess(level.isClientSide());

		}
		return super.useItemOn(stack, state, level, pos, player, hand, result);
	}


	public static void spawnParticlesOnBlockFace(Level level, BlockPos pos, ParticleOptions particle, IntProvider count, Direction direction, Supplier<Vec3> speedSupplier, double spread) {
		int i = count.sample(level.random);

		for(int j = 0; j < i; ++j) {
			spawnParticleOnFace(level, pos, direction, particle, speedSupplier.get(), spread);
		}

	}

	public static void spawnParticleOnFace(Level level, BlockPos pos, Direction direction, ParticleOptions particle, Vec3 speed, double spread) {
		if (level instanceof ServerLevel serverLevel) {
			Vec3 vec3 = Vec3.atCenterOf(pos);
			int i = direction.getStepX();
			int j = direction.getStepY();
			int k = direction.getStepZ();
			double d0 = vec3.x + (i == 0 ? Mth.nextDouble(level.random, -0.5, 0.5) : (double) i * spread);
			double d1 = vec3.y + (j == 0 ? Mth.nextDouble(level.random, -0.5, 0.5) : (double) j * spread);
			double d2 = vec3.z + (k == 0 ? Mth.nextDouble(level.random, -0.5, 0.5) : (double) k * spread);
			double d3 = i == 0 ? speed.x() : 0.0;
			double d4 = j == 0 ? speed.y() : 0.0;
			double d5 = k == 0 ? speed.z() : 0.0;
			serverLevel.sendParticles(particle, d0, d1, d2, 1, 0, 0, 0 , (d3+d4+d5)/3);
		}
	}

	@Override
	protected FluidState getFluidState(BlockState p_221523_) {
		return p_221523_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_221523_);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_221521_) {
		p_221521_.add(WATERLOGGED);
	}

	@Override
	public float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
		return 1.0F;
	}
}
