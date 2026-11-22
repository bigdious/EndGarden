package com.bigdious.endgarden.data;

import com.bigdious.endgarden.EG;
import com.bigdious.endgarden.init.EGBlocks;
import com.bigdious.endgarden.init.EGTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EGBlockTagsGenerator extends BlockTagsProvider {
	public EGBlockTagsGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, EG.MODID, existingFileHelper);
	}
	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(BlockTags.SAPLINGS).add(EGBlocks.WHISPERIA_SAPLING.get(), EGBlocks.GHOST_WHISPERIA_SAPLING.get());
		this.tag(BlockTags.MUSHROOM_GROW_BLOCK).add(EGBlocks.WELKIN_GRASS.get(), EGBlocks.WELKIN_GRASS_BUNDLE.get());
		this.tag(BlockTags.SMALL_DRIPLEAF_PLACEABLE).add(EGBlocks.WELKIN_GRASS.get(), EGBlocks.WELKIN_GRASS_BUNDLE.get());
		this.tag(BlockTags.DIRT).add(EGBlocks.WELKIN_GRASS.get(), EGBlocks.WELKIN_GRASS_BUNDLE.get());
		this.tag(BlockTags.MINEABLE_WITH_HOE).add(EGBlocks.WELKIN_GRASS.get(), EGBlocks.WELKIN_GRASS_BUNDLE.get());
		this.tag(BlockTags.ENDERMAN_HOLDABLE).remove(EGBlocks.WELKIN_GRASS.get(), EGBlocks.WELKIN_GRASS_BUNDLE.get());

		this.tag(BlockTags.REPLACEABLE_BY_TREES).add(EGBlocks.STAR_FERN_MAGENTA.get(), EGBlocks.STAR_FERN_TEAL.get(), EGBlocks.WELKIN_SPROUTS.get());

		this.tag(EGTags.Blocks.WHISPERING_WISTERIA_LOGS)
			.add(EGBlocks.WHISPERIA_LOG.get(), EGBlocks.STRIPPED_WHISPERIA_LOG.get())
			.add(EGBlocks.WHISPERIA_WOOD.get(), EGBlocks.STRIPPED_WHISPERIA_WOOD.get());

		this.tag(BlockTags.LOGS).addTag(EGTags.Blocks.WHISPERING_WISTERIA_LOGS);
		this.tag(BlockTags.LOGS_THAT_BURN).addTag(EGTags.Blocks.WHISPERING_WISTERIA_LOGS);
		this.tag(BlockTags.PLANKS).add(EGBlocks.WHISPERIA_PLANKS.get());
		this.tag(BlockTags.LEAVES).add(EGBlocks.WHISPERIA_LEAVES.get(), EGBlocks.GHOST_WHISPERIA_LEAVES.get());

		this.tag(BlockTags.FENCES).add(
			EGBlocks.WHISPERIA_FENCE.get()
		);
		this.tag(BlockTags.WOODEN_FENCES).add(
			EGBlocks.WHISPERIA_FENCE.get()
		);
		this.tag(Tags.Blocks.FENCES).add(
			EGBlocks.WHISPERIA_FENCE.get()
		);
		this.tag(Tags.Blocks.FENCE_GATES).add(
			EGBlocks.WHISPERIA_FENCE_GATE.get()
		);
		this.tag(BlockTags.FENCE_GATES).add(
			EGBlocks.WHISPERIA_FENCE_GATE.get()
		);
		this.tag(Tags.Blocks.FENCES_WOODEN).add(
			EGBlocks.WHISPERIA_FENCE.get()
		);
		this.tag(Tags.Blocks.FENCE_GATES_WOODEN).add(
			EGBlocks.WHISPERIA_FENCE_GATE.get()
		);

		this.tag(BlockTags.WOODEN_SLABS).add(EGBlocks.WHISPERIA_SLAB.get());
		this.tag(BlockTags.SLABS).add(
			EGBlocks.WHISPERIA_SLAB.get()
		);
		this.tag(BlockTags.WOODEN_STAIRS).add(EGBlocks.WHISPERIA_STAIRS.get());
		this.tag(BlockTags.STAIRS).add(
			EGBlocks.WHISPERIA_STAIRS.get()
		);
		this.tag(BlockTags.WOODEN_BUTTONS).add(EGBlocks.WHISPERIA_BUTTON.get());
		this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(EGBlocks.WHISPERIA_PRESSURE_PLATE.get());

		this.tag(BlockTags.DOORS).add(
			EGBlocks.WHISPERIA_DOOR.get()
		);
		this.tag(BlockTags.WOODEN_DOORS).add(EGBlocks.WHISPERIA_DOOR.get());

		this.tag(BlockTags.TRAPDOORS).add(
			EGBlocks.WHISPERIA_TRAPDOOR.get()
		);
		this.tag(BlockTags.WOODEN_TRAPDOORS).add(EGBlocks.WHISPERIA_TRAPDOOR.get());

		this.tag(BlockTags.SIGNS).add(
			EGBlocks.WHISPERIA_SIGN.get(),
			EGBlocks.WHISPERIA_WALL_SIGN.get()
		);

		this.tag(BlockTags.CEILING_HANGING_SIGNS).add(
			EGBlocks.WHISPERIA_HANGING_SIGN.get()
		);

		this.tag(BlockTags.WALL_HANGING_SIGNS).add(
			EGBlocks.WHISPERIA_WALL_HANGING_SIGN.get()
		);

		this.tag(BlockTags.STANDING_SIGNS).add(EGBlocks.WHISPERIA_SIGN.get());
		this.tag(BlockTags.WALL_SIGNS).add(EGBlocks.WHISPERIA_WALL_SIGN.get());
		this.tag(BlockTags.MINEABLE_WITH_HOE).add(EGBlocks.WHISPERIA_LEAVES.get());

	}
}
