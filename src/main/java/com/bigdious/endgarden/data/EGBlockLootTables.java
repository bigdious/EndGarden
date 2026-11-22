package com.bigdious.endgarden.data;

import com.bigdious.endgarden.init.EGBlocks;
import com.bigdious.endgarden.init.EGItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class EGBlockLootTables extends BlockLootSubProvider {
	protected EGBlockLootTables(HolderLookup.Provider provider) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
	}

	@Override
	protected void generate() {
		dropSelf(EGBlocks.WHISPERIA_SAPLING.get());
		dropSelf(EGBlocks.GHOST_WHISPERIA_SAPLING.get());
		dropOther(EGBlocks.WELKIN_GRASS.get(), EGItems.WELKIN_GRASS.get());

		dropSelf(EGBlocks.WELKIN_GRASS_BUNDLE.get());
		dropSelf(EGBlocks.ORCHESTRAL_BLOOM.get());

		dropSelf(EGBlocks.WHISPERIA_LOG.get());
		dropSelf(EGBlocks.WHISPERIA_WOOD.get());
		dropSelf(EGBlocks.STRIPPED_WHISPERIA_LOG.get());
		dropSelf(EGBlocks.STRIPPED_WHISPERIA_WOOD.get());
		dropSelf(EGBlocks.WHISPERIA_PLANKS.get());
		dropSelf(EGBlocks.WHISPERIA_STAIRS.get());
		add(EGBlocks.WHISPERIA_SLAB.get(), createSlabItemTable(EGBlocks.WHISPERIA_SLAB.get()));
		dropSelf(EGBlocks.WHISPERIA_BUTTON.get());
		dropSelf(EGBlocks.WHISPERIA_FENCE_GATE.get());
		dropSelf(EGBlocks.WHISPERIA_FENCE.get());
		dropSelf(EGBlocks.WHISPERIA_PRESSURE_PLATE.get());
		dropSelf(EGBlocks.WHISPERIA_TRAPDOOR.get());
		add(EGBlocks.WHISPERIA_DOOR.get(), createSinglePropConditionTable(EGBlocks.WHISPERIA_DOOR.get(), DoorBlock.HALF, DoubleBlockHalf.LOWER));
		dropOther(EGBlocks.WHISPERIA_SIGN.get(), EGBlocks.WHISPERIA_SIGN.get().asItem());
		dropOther(EGBlocks.WHISPERIA_WALL_SIGN.get(), EGBlocks.WHISPERIA_SIGN.get().asItem());
		dropOther(EGBlocks.WHISPERIA_HANGING_SIGN.get(), EGBlocks.WHISPERIA_HANGING_SIGN.get().asItem());
		dropOther(EGBlocks.WHISPERIA_WALL_HANGING_SIGN.get(), EGBlocks.WHISPERIA_HANGING_SIGN.get().asItem());
		add(EGBlocks.WHISPERIA_LEAVES.get(), silkAndStick(EGBlocks.WHISPERIA_LEAVES.get(), EGBlocks.WHISPERIA_SAPLING , 0.05F, 0.0625F, 0.083333336F, 0.1F));
		add(EGBlocks.GHOST_WHISPERIA_LEAVES.get(), silkAndStick(EGBlocks.GHOST_WHISPERIA_LEAVES.get(), EGBlocks.GHOST_WHISPERIA_SAPLING , 0.05F, 0.0625F, 0.083333336F, 0.1F));
		add(EGBlocks.WHISPERIA_HANGINGS.get(), createShearsOnlyDrop(EGBlocks.WHISPERIA_HANGINGS.get()));
		add(EGBlocks.GHOST_WHISPERIA_HANGINGS.get(), createShearsOnlyDrop(EGBlocks.GHOST_WHISPERIA_HANGINGS.get()));
		add(EGBlocks.STAR_FERN_TEAL.get(), createShearsOnlyDrop(EGBlocks.STAR_FERN_TEAL.get()));
		add(EGBlocks.STAR_FERN_MAGENTA.get(), createShearsOnlyDrop(EGBlocks.STAR_FERN_MAGENTA.get()));
		add(EGBlocks.WELKIN_SPROUTS.get(), createShearsOnlyDrop(EGBlocks.WELKIN_SPROUTS.get()));
	}

	private LootTable.Builder silkAndStick(Block block, ItemLike nonSilk, float... nonSilkFortune) {
		HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
		return createSilkTouchOrShearsDispatchTable(block, this.applyExplosionCondition(block, LootItem.lootTableItem(nonSilk.asItem())).when(BonusLevelTableCondition.bonusLevelFlatChance(registrylookup.getOrThrow(Enchantments.FORTUNE), nonSilkFortune))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when((HAS_SHEARS.or(this.hasSilkTouch())).invert()).add(applyExplosionDecay(block, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(registrylookup.getOrThrow(Enchantments.FORTUNE), 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return EGBlocks.BLOCKS.getEntries().stream().map(Supplier::get).collect(Collectors.toList());
	}
}
