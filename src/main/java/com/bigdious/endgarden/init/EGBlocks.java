package com.bigdious.endgarden.init;

import com.bigdious.endgarden.EG;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class EGBlocks {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(EG.MODID);


	public static <T extends Block> DeferredBlock<T> register(String name, Function<BlockBehaviour.Properties, T> block, Supplier<BlockBehaviour.Properties> properties) {
		return BLOCKS.register(name, () -> block.apply(properties.get()));
	}
}
