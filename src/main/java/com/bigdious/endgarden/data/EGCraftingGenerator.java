package com.bigdious.endgarden.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public class EGCraftingGenerator extends RecipeProvider {
	public EGCraftingGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
		super(packOutput, provider);
	}

	@Override
	protected void buildRecipes(RecipeOutput consumer) {

	}

}
