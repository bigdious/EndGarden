package com.bigdious.endgarden.data;

import com.bigdious.endgarden.EG;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class EGItemModelGenerator extends ItemModelProvider {
	public EGItemModelGenerator(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
		super(packOutput, EG.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {


	}

	private void toBlock(Block b) {
		toBlockModel(b, EG.prefix("block/" + Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(b)).getPath()));
	}

	private void toBlockModel(Block b, ResourceLocation model) {
		withExistingParent(Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(b)).getPath(), model);
	}
}
