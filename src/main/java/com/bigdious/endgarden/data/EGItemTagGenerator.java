package com.bigdious.endgarden.data;

import com.bigdious.endgarden.EG;
import com.bigdious.endgarden.init.EGBlocks;
import com.bigdious.endgarden.init.EGItems;
import com.bigdious.endgarden.init.EGTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EGItemTagGenerator extends ItemTagsProvider {

	public EGItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagLookup, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, blockTagLookup, EG.MODID, existingFileHelper);
	}
	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(EGTags.Items.WHISPERIA_LOGS).add(
			EGBlocks.STRIPPED_WHISPERIA_LOG.get().asItem(),
			EGBlocks.WHISPERIA_LOG.get().asItem(),
			EGBlocks.STRIPPED_WHISPERIA_WOOD.get().asItem(),
			EGBlocks.WHISPERIA_WOOD.get().asItem()
			);
		this.tag(EGTags.Items.ORCHESTRAL_BLOOM_TRIGGERS).add(Items.ENDER_PEARL);
		this.copy(BlockTags.PLANKS, ItemTags.PLANKS);

		this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
		this.copy(Tags.Blocks.FENCES, Tags.Items.FENCES);
		this.copy(Tags.Blocks.FENCE_GATES, Tags.Items.FENCE_GATES);
		this.copy(Tags.Blocks.FENCES_WOODEN, Tags.Items.FENCES_WOODEN);
		this.copy(Tags.Blocks.FENCE_GATES_WOODEN, Tags.Items.FENCE_GATES_WOODEN);

		this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
		this.copy(BlockTags.SLABS, ItemTags.SLABS);
		this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
		this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
		this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
		this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);

		this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
		this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
		this.tag(ItemTags.SIGNS).add(EGBlocks.WHISPERIA_SIGN.get().asItem());

		this.tag(ItemTags.BOATS).add(
			EGItems.WHISPERIA_BOAT.get(),
			EGItems.WHISPERIA_CHEST_BOAT.get()
		);

	}
}
