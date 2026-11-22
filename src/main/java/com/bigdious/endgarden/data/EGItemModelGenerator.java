package com.bigdious.endgarden.data;

import com.bigdious.endgarden.EG;
import com.bigdious.endgarden.init.EGBlocks;
import com.bigdious.endgarden.init.EGItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.Objects;

public class EGItemModelGenerator extends ItemModelProvider {
	public EGItemModelGenerator(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
		super(packOutput, EG.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		toBlock(EGBlocks.WELKIN_GRASS.get());
		generatedRenderType(EGBlocks.WELKIN_SPROUTS.getId().getPath(), "minecraft:cutout", EG.prefix("block/welkin_sprouts"));
		toBlock(EGBlocks.WELKIN_GRASS_BUNDLE.get());
		toBlock(EGBlocks.STAR_FERN_TEAL.get());
		toBlock(EGBlocks.STAR_FERN_MAGENTA.get());
		generatedRenderType(EGBlocks.ORCHESTRAL_BLOOM.getId().getPath(), "minecraft:cutout", EG.prefix("block/orchestral_bloom_open"));
		toBlock(EGBlocks.WHISPERIA_LOG.get());
		toBlock(EGBlocks.WHISPERIA_WOOD.get());
		toBlock(EGBlocks.STRIPPED_WHISPERIA_LOG.get());
		toBlock(EGBlocks.STRIPPED_WHISPERIA_WOOD.get());
		toBlock(EGBlocks.WHISPERIA_PLANKS.get());
		toBlock(EGBlocks.WHISPERIA_SLAB.get());
		toBlock(EGBlocks.WHISPERIA_STAIRS.get());
		toBlock(EGBlocks.WHISPERIA_FENCE_GATE.get());
		toBlock(EGBlocks.WHISPERIA_PRESSURE_PLATE.get());
		toBlock(EGBlocks.WHISPERIA_LEAVES.get());
		toBlock(EGBlocks.GHOST_WHISPERIA_LEAVES.get());
		getBuilder(EGBlocks.WHISPERIA_FENCE.getId().getPath())
			.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/fence_inventory")))
			.texture("texture", EG.prefix("block/whisperia_planks"));
		getBuilder(EGBlocks.WHISPERIA_BUTTON.getId().getPath())
			.parent(getExistingFile(ResourceLocation.withDefaultNamespace("block/button_inventory")))
			.texture("texture", EG.prefix("block/whisperia_planks"));
		toBlockModel(EGBlocks.WHISPERIA_TRAPDOOR.get(), EG.prefix("block/whisperia_trapdoor_bottom"));
		generated(EGBlocks.WHISPERIA_DOOR.getId().getPath(), EG.prefix("item/whisperia_door"));
		generated(EGBlocks.WHISPERIA_SIGN.getId().getPath(), EG.prefix("item/whisperia_sign"));
		generated(EGBlocks.WHISPERIA_HANGING_SIGN.getId().getPath(), EG.prefix("item/whisperia_hanging_sign"));
		singleTex(EGBlocks.WHISPERIA_SAPLING.get());
		singleTex(EGBlocks.GHOST_WHISPERIA_SAPLING.get());
		singleTex(EGItems.WHISPERIA_CHEST_BOAT.get());
		singleTex(EGItems.WHISPERIA_BOAT.get());
		singleTex(EGBlocks.WHISPERIA_HANGINGS.get());
		singleTex(EGBlocks.GHOST_WHISPERIA_HANGINGS.get());
		singleTex(EGItems.COMPOSERS_NOTE.get());
	}

	private void toBlock(Block b) {
		toBlockModel(b, EG.prefix("block/" + Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(b)).getPath()));
	}

	private ItemModelBuilder generated(String name, ResourceLocation... layers) {
		return generated(name, "item/generated", layers);
	}

	private ItemModelBuilder generated(String name, String parent, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, parent);
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		return builder;
	}

	private ItemModelBuilder singleTex(String texture) {
		return generated(texture, "item/generated", EG.prefix("item/" + texture));
	}

	private ItemModelBuilder singleTex(ItemLike item) {
		return generated(BuiltInRegistries.ITEM.getKey(item.asItem()).getPath(), "item/generated", EG.prefix("item/" + BuiltInRegistries.ITEM.getKey(item.asItem()).getPath()));
	}

	private void toBlockModel(Block b, ResourceLocation model) {
		withExistingParent(Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(b)).getPath(), model);
	}

	private ItemModelBuilder generatedRenderType(String name, @Nullable String renderType, ResourceLocation... layers) {
		return buildItem(name, "item/generated", renderType, layers);
	}


	private ItemModelBuilder buildItem(String name, String parent, @Nullable String renderType, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, parent);
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		if (renderType != null) {
			builder = builder.renderType(renderType);
		}
		return builder;
	}
}
