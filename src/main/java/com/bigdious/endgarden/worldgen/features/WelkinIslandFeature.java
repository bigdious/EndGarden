package com.bigdious.endgarden.worldgen.features;


import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

public class WelkinIslandFeature extends Feature<BlockStateConfiguration> {
	int minSize;
	int maxSize;

	public WelkinIslandFeature(Codec<BlockStateConfiguration> codec, int minSize, int maxSize) {
		super(codec);
		this.minSize = minSize;
		this.maxSize = maxSize;
	}

	@Override
	public boolean place(FeaturePlaceContext<BlockStateConfiguration> ctx) {
		WorldGenLevel world = ctx.level();
		BlockPos origin = ctx.origin();
		RandomSource random = ctx.random();

		int placed = 0;
		if (random.nextIntBetweenInclusive(0, 4) != 1) { return false;}
		int size = random.nextIntBetweenInclusive(this.minSize, this.maxSize);
			circle(random.nextIntBetweenInclusive(2, size), world, ctx.config().state, origin.north().east());
			circle(random.nextIntBetweenInclusive(2, size), world, ctx.config().state, origin.north().west());
			circle(random.nextIntBetweenInclusive(2, size), world, ctx.config().state, origin.south().east());
			circle(random.nextIntBetweenInclusive(2, size), world, ctx.config().state, origin.south().west());
			blob(random.nextIntBetweenInclusive(1, size), world, ctx.config().state, origin.north(size/2));
			blob(random.nextIntBetweenInclusive(1, size), world, ctx.config().state, origin.east(size/2));
			blob(random.nextIntBetweenInclusive(1, size), world, ctx.config().state, origin.west(size/2));
			blob(random.nextIntBetweenInclusive(1, size), world, ctx.config().state, origin.south(size/2));


		placed++;
		return  placed > 0;
	}

	private void circle(int size, WorldGenLevel level, BlockState state, BlockPos pos) {
		for (BlockPos pos1 : BlockPos.betweenClosed(pos.offset(-size, 0, -size), pos.offset(size, 0, size))) {
			int i1 = pos1.getX() - pos.getX();
			int j1 = pos1.getZ() - pos.getZ();
			if (i1 * i1 + j1 * j1 <= size * size) {
				level.setBlock(pos1, state, 11);
			}
		}

	}

	private void blob(int size, WorldGenLevel level, BlockState state, BlockPos pos) {
		for(int i = -(size-2); i <= size-2; ++i) {
			for(int j = -(size-2); j <= size-2; ++j) {
				if (Math.abs(i) != size-2 || Math.abs(j) != size-2) {
					level.setBlock(pos.offset(i, 0, j), state, 11);
				}
			}
		}
	}
}
