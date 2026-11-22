package com.bigdious.endgarden.blocks;

import com.bigdious.endgarden.init.EGBlocks;
import com.bigdious.endgarden.init.EGPOITypes;
import com.bigdious.endgarden.init.EGSoundEffects;
import com.bigdious.endgarden.init.EGTags;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.level.CollisionGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;
import java.util.function.Predicate;

public class OrchestralBloomBlock extends FlowerBlock {
	//big thanks to Commoble from Neoforge discord for all the help with this headache


	public static final ImmutableList<Vec3i> HORIZONTAL_OFFSETS = ImmutableList.of(new Vec3i(0, 0, -1), new Vec3i(-1, 0, 0), new Vec3i(0, 0, 1), new Vec3i(1, 0, 0), new Vec3i(-1, 0, -1), new Vec3i(1, 0, -1), new Vec3i(-1, 0, 1), new Vec3i(1, 0, 1));

	public static final BooleanProperty OPEN = BooleanProperty.create("open");
	public OrchestralBloomBlock(Holder<MobEffect> effect, float seconds, Properties properties) {
		super(effect, seconds, properties);
		this.registerDefaultState(this.getStateDefinition().any()
			.setValue(OPEN, false)
		);
	}

	public boolean isRandomlyTicking(BlockState state) {
		return !state.getValue(OPEN);
	}

	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!state.getValue(OPEN)) {
			level.setBlock(pos, state.setValue(OPEN, true), 11);
			level.playSound(null, pos, EGSoundEffects.ORCHESTRAL_BLOOM_OPENS.get(), SoundSource.BLOCKS);
		}

	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
		if (!state.getValue(OPEN)) {
			Vec3 offsetPos = state.getOffset(level, pos);
			for (int i = 0; i < 3; ++i) {
				double d0 = pos.getX() + offsetPos.x() + random.nextDouble();
				double d1 = pos.getY() + offsetPos.y() + random.nextDouble() * (double) 0.5F + (double) 0.5F;
				double d2 = pos.getZ() + offsetPos.z() + random.nextDouble();
				level.addParticle(ParticleTypes.PORTAL, d0, d1, d2, 0.0F, 0.0F, 0.0F);
			}
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(OPEN);
	}
}
