package com.bigdious.endgarden.data;

import com.bigdious.endgarden.init.EGBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class EGBlockLootTables extends BlockLootSubProvider {
	protected EGBlockLootTables(HolderLookup.Provider provider) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
	}

	@Override
	protected void generate() {

	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return EGBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
	}
}
