package com.bigdious.endgarden.data;

import com.bigdious.endgarden.init.EGBlocks;
import com.bigdious.endgarden.init.EGItems;
import com.bigdious.endgarden.init.EGTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class EGCraftingsGenerator extends RecipeProvider {
	public EGCraftingsGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
		super(packOutput, provider);
	}

	@Override
	protected void buildRecipes(RecipeOutput consumer) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EGBlocks.WHISPERIA_WOOD.get(), 3)
			.pattern("##")
			.pattern("##")
			.define('#', Ingredient.of(EGBlocks.WHISPERIA_LOG.get()))
			.unlockedBy("has_item", has(EGBlocks.WHISPERIA_LOG.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EGBlocks.STRIPPED_WHISPERIA_WOOD.get(), 3)
			.pattern("##")
			.pattern("##")
			.define('#', Ingredient.of(EGBlocks.STRIPPED_WHISPERIA_LOG.get()))
			.unlockedBy("has_item", has(EGBlocks.STRIPPED_WHISPERIA_LOG.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EGBlocks.WHISPERIA_PLANKS.get(), 4)
			.requires(Ingredient.of(EGTags.Items.WHISPERIA_LOGS))
			.unlockedBy("has_item", has(EGTags.Items.WHISPERIA_LOGS))
			.save(consumer);


		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EGBlocks.WHISPERIA_STAIRS.get(), 4)
			.pattern("#  ")
			.pattern("## ")
			.pattern("###")
			.define('#', Ingredient.of(EGBlocks.WHISPERIA_PLANKS.get()))
			.unlockedBy("has_item", has(EGBlocks.WHISPERIA_PLANKS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EGBlocks.WHISPERIA_SLAB.get(), 6)
			.pattern("###")
			.define('#', Ingredient.of(EGBlocks.WHISPERIA_PLANKS.get()))
			.unlockedBy("has_item", has(EGBlocks.WHISPERIA_PLANKS.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, EGBlocks.WHISPERIA_BUTTON.get())
			.requires(Ingredient.of(EGBlocks.WHISPERIA_PLANKS.get()))
			.unlockedBy("has_item", has(EGBlocks.WHISPERIA_PLANKS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, EGBlocks.WHISPERIA_FENCE.get(), 3)
			.pattern("#/#")
			.pattern("#/#")
			.define('#', Ingredient.of(EGBlocks.WHISPERIA_PLANKS.get()))
			.define('/', Ingredient.of(Tags.Items.RODS_WOODEN))
			.unlockedBy("has_item", has(EGBlocks.WHISPERIA_PLANKS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, EGBlocks.WHISPERIA_FENCE_GATE.get())
			.pattern("/#/")
			.pattern("/#/")
			.define('#', Ingredient.of(EGBlocks.WHISPERIA_PLANKS.get()))
			.define('/', Ingredient.of(Tags.Items.RODS_WOODEN))
			.unlockedBy("has_item", has(EGBlocks.WHISPERIA_PLANKS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, EGBlocks.WHISPERIA_PRESSURE_PLATE.get())
			.pattern("##")
			.define('#', Ingredient.of(EGBlocks.WHISPERIA_PLANKS.get()))
			.unlockedBy("has_item", has(EGBlocks.WHISPERIA_PLANKS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, EGBlocks.WHISPERIA_DOOR.get(), 3)
			.pattern("##")
			.pattern("##")
			.pattern("##")
			.define('#', Ingredient.of(EGBlocks.WHISPERIA_PLANKS.get()))
			.unlockedBy("has_item", has(EGBlocks.WHISPERIA_PLANKS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, EGBlocks.WHISPERIA_TRAPDOOR.get(), 2)
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(EGBlocks.WHISPERIA_PLANKS.get()))
			.unlockedBy("has_item", has(EGBlocks.WHISPERIA_PLANKS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, EGBlocks.WHISPERIA_SIGN.get().asItem(), 3)
			.pattern("###")
			.pattern("###")
			.pattern(" / ")
			.define('#', Ingredient.of(EGBlocks.WHISPERIA_PLANKS.get()))
			.define('/', Ingredient.of(Tags.Items.RODS_WOODEN))
			.unlockedBy("has_item", has(EGBlocks.WHISPERIA_PLANKS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, EGBlocks.WHISPERIA_HANGING_SIGN.get().asItem(), 6)
			.pattern("/ /")
			.pattern("###")
			.pattern("###")
			.define('#', Ingredient.of(EGBlocks.STRIPPED_WHISPERIA_LOG.get()))
			.define('/', Ingredient.of(Tags.Items.CHAINS))
			.unlockedBy("has_item", has(EGBlocks.WHISPERIA_PLANKS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, EGItems.WHISPERIA_BOAT, 1)
			.pattern("# #")
			.pattern("###")
			.define('#', Ingredient.of(EGBlocks.WHISPERIA_PLANKS.get()))
			.unlockedBy("has_item", has(EGBlocks.WHISPERIA_PLANKS.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, EGItems.WHISPERIA_CHEST_BOAT, 1)
			.requires(Tags.Items.CHESTS)
			.requires(EGItems.WHISPERIA_BOAT.get())
			.unlockedBy("has_item", has(EGBlocks.WHISPERIA_PLANKS.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, EGBlocks.WHISPERIA_HANGINGS, 8)
			.pattern("#")
			.pattern("#")
			.define('#', Ingredient.of(EGBlocks.WHISPERIA_LEAVES.get()))
			.unlockedBy("has_item", has(EGBlocks.WHISPERIA_LEAVES.get()))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, EGBlocks.WELKIN_GRASS_BUNDLE.get(), 2)
			.pattern("##")
			.pattern("##")
			.define('#', Ingredient.of(EGBlocks.WELKIN_GRASS.get()))
			.unlockedBy("has_item", has(EGBlocks.WELKIN_GRASS.get()))
			.save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, EGBlocks.GHOST_WHISPERIA_SAPLING.get())
			.requires(Ingredient.of(EGBlocks.WHISPERIA_SAPLING))
			.requires(Ingredient.of(ItemTags.SOUL_FIRE_BASE_BLOCKS))
			.unlockedBy("has_item", has(EGBlocks.WHISPERIA_SAPLING))
			.save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, EGBlocks.GHOST_WHISPERIA_HANGINGS, 8)
			.pattern("#")
			.pattern("#")
			.define('#', Ingredient.of(EGBlocks.GHOST_WHISPERIA_LEAVES.get()))
			.unlockedBy("has_item", has(EGBlocks.GHOST_WHISPERIA_LEAVES.get()))
			.save(consumer);
	}

}
