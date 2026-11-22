package com.bigdious.endgarden.data;

import com.bigdious.endgarden.EG;
import com.bigdious.endgarden.blocks.OrchestralBloomBlock;
import com.bigdious.endgarden.init.EGBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class EGBlockModelGenerator extends BlockStateProvider {
	public EGBlockModelGenerator(PackOutput output, ExistingFileHelper helper) {
		super(output, EG.MODID, helper);
	}
	@Override
	protected void registerStatesAndModels() {
		saplingBlock(EGBlocks.WHISPERIA_SAPLING);
		saplingBlock(EGBlocks.GHOST_WHISPERIA_SAPLING);
		simpleBlock(EGBlocks.WELKIN_GRASS.get(), ConfiguredModel.builder()
			.weight(1).modelFile(models().getExistingFile(EG.prefix("block/welkin_grass_0"))).nextModel()
			.weight(1).modelFile(models().getExistingFile(EG.prefix("block/welkin_grass_1"))).nextModel()
			.weight(1).modelFile(models().getExistingFile(EG.prefix("block/welkin_grass_2"))).nextModel()
			.weight(1).modelFile(models().getExistingFile(EG.prefix("block/welkin_grass_3")))
			.build());

		ModelFile bundle = models().cubeAll(EGBlocks.WELKIN_GRASS_BUNDLE.getId().getPath(), blockTexture(EGBlocks.WELKIN_GRASS_BUNDLE.get()));
		simpleBlock(EGBlocks.WELKIN_GRASS_BUNDLE.get(), ConfiguredModel.builder()
			.modelFile(bundle).nextModel()
			.rotationY(180).modelFile(bundle).nextModel()
			.rotationY(90).modelFile(bundle).nextModel()
			.rotationY(270).modelFile(bundle).build()
		);

		simpleBlock(EGBlocks.WELKIN_SPROUTS.get(), models().cross("welkin_sprouts", EG.prefix("block/welkin_sprouts")).renderType("minecraft:cutout"));

		getVariantBuilder(EGBlocks.ORCHESTRAL_BLOOM.get()).forAllStatesExcept(state -> {
				ModelFile open = models().cross("orchestral_bloom_open", EG.prefix("block/orchestral_bloom_open")).renderType("minecraft:cutout");
				ModelFile closed = models().cross("orchestral_bloom_closed", EG.prefix("block/orchestral_bloom_closed")).renderType("minecraft:cutout");
				return ConfiguredModel.builder().modelFile(state.getValue(OrchestralBloomBlock.OPEN) ? open : closed).build();
			});


		directionalBlock(EGBlocks.STRIPPED_WHISPERIA_LOG.get(),models().cubeColumn("whisperia_log_stripped", EG.prefix("block/stripped_whisperia_log"), EG.prefix("block/stripped_whisperia_log_top")));
		directionalBlock(EGBlocks.STRIPPED_WHISPERIA_WOOD.get(), models().cubeColumn("whisperia_wood_stripped", EG.prefix("block/stripped_whisperia_log"), EG.prefix("block/stripped_whisperia_log")));
		directionalBlock(EGBlocks.WHISPERIA_LOG.get(), models().cubeColumn("whisperia_log", EG.prefix("block/whisperia_log"), EG.prefix("block/whisperia_log_top")));
		directionalBlock(EGBlocks.WHISPERIA_WOOD.get(), models().cubeColumn("whisperia_wood", EG.prefix("block/whisperia_log"), EG.prefix("block/whisperia_log")));
		simpleBlock(EGBlocks.WHISPERIA_PLANKS.get());
		fenceBlock(EGBlocks.WHISPERIA_FENCE.get(), EG.prefix("block/whisperia_planks"));
		fenceGateBlock(EGBlocks.WHISPERIA_FENCE_GATE.get(), EG.prefix("block/whisperia_planks"));
		pressurePlateBlock(EGBlocks.WHISPERIA_PRESSURE_PLATE.get(), EG.prefix("block/whisperia_planks"));
		buttonBlock(EGBlocks.WHISPERIA_BUTTON.get(), EG.prefix("block/whisperia_planks"));
		trapdoorBlockWithRenderType(EGBlocks.WHISPERIA_TRAPDOOR.get(), EG.prefix("block/whisperia_trapdoor"), true, ResourceLocation.withDefaultNamespace("cutout"));
		doorBlockWithRenderType(EGBlocks.WHISPERIA_DOOR.get(), EG.prefix("block/whisperia_door_bottom"), EG.prefix("block/whisperia_door_top"), ResourceLocation.withDefaultNamespace("cutout"));
		builtinEntity(EGBlocks.WHISPERIA_SIGN.get(), EG.prefix("block/whisperia_planks"));
		builtinEntity(EGBlocks.WHISPERIA_WALL_SIGN.get(), EG.prefix("block/whisperia_planks"));
		builtinEntity(EGBlocks.WHISPERIA_HANGING_SIGN.get(), EG.prefix("block/stripped_whisperia_log"));
		builtinEntity(EGBlocks.WHISPERIA_WALL_HANGING_SIGN.get(), EG.prefix("block/stripped_whisperia_log"));
		stairsBlock(EGBlocks.WHISPERIA_STAIRS.get(), EG.prefix("block/whisperia_planks"));
		slabBlock(EGBlocks.WHISPERIA_SLAB.get(), EG.prefix("block/whisperia_planks"), EG.prefix("block/whisperia_planks"));

		simpleBlock(EGBlocks.WHISPERIA_LEAVES.get(), models().getExistingFile(EG.prefix("block/whisperia_leaves")));
		simpleBlock(EGBlocks.GHOST_WHISPERIA_LEAVES.get(), models().getExistingFile(EG.prefix("block/ghost_whisperia_leaves")));
		simpleBlock(EGBlocks.STAR_FERN_TEAL.get(), models().withExistingParent("star_fern_teal", EG.prefix("block/star_fern")).texture("color", EG.prefix("block/star_fern_teal")));
		simpleBlock(EGBlocks.STAR_FERN_MAGENTA.get(), models().withExistingParent("star_fern_magenta", EG.prefix("block/star_fern")).texture("color", EG.prefix("block/star_fern_magenta")));
		simpleBlock(EGBlocks.WHISPERIA_HANGINGS.get(), models().getExistingFile(EG.prefix("block/whisperia_hangings")));
		simpleBlock(EGBlocks.GHOST_WHISPERIA_HANGINGS.get(), models().getExistingFile(EG.prefix("block/ghost_whisperia_hangings")));

	}

	private void saplingBlock(DeferredBlock<Block> block) {
		simpleBlock(block.get(),
			models().cross(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(), blockTexture(block.get())).renderType("cutout"));
	}

	protected void builtinEntity(Block b, ResourceLocation particle) {
		simpleBlock(b, models().getBuilder(BuiltInRegistries.BLOCK.getKey(b).getPath())
			.parent(new ModelFile.UncheckedModelFile("builtin/entity"))
			.texture("particle", particle));
	}
}
