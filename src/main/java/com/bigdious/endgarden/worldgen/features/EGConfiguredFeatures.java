package com.bigdious.endgarden.worldgen.features;

import com.bigdious.endgarden.EG;
import com.bigdious.endgarden.blocks.OrchestralBloomBlock;
import com.bigdious.endgarden.init.EGBlocks;
import com.bigdious.endgarden.worldgen.tree.WisteriaFoliagePlacer;
import com.bigdious.endgarden.worldgen.tree.WisteriaTrunkPlacer;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class EGConfiguredFeatures {

	public static final ResourceKey<ConfiguredFeature<?, ?>> WELKIN_GRASS_PATCH = create("welkin_grass_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> WELKIN_GRASSIFYING = create("welkin_grassifying");
	public static final ResourceKey<ConfiguredFeature<?, ?>> WHISPERIA_TREE = create("whisperia_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> GHOST_WHISPERIA_TREE = create("ghost_whisperia_tree");
	public static final ResourceKey<ConfiguredFeature<?, ?>> STAR_FERN = create("star_fern");
	public static final ResourceKey<ConfiguredFeature<?, ?>> STAR_FERN_2 = create("star_fern_2");
	public static final ResourceKey<ConfiguredFeature<?, ?>> BLOOM_CLOSED = create("bloom_closed");
	public static final ResourceKey<ConfiguredFeature<?, ?>> BLOOM_OPEN = create("bloom_open");



	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

		context.register(WELKIN_GRASS_PATCH, new ConfiguredFeature<>(EGFeatures.WELKIN_PATCH.get(), new BlockStateConfiguration(EGBlocks.WELKIN_GRASS.get().defaultBlockState())));
		context.register(STAR_FERN, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(EGBlocks.STAR_FERN_TEAL.get()))));
		context.register(STAR_FERN_2, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(EGBlocks.STAR_FERN_MAGENTA.get()))));
		context.register(BLOOM_OPEN, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(EGBlocks.ORCHESTRAL_BLOOM.get().defaultBlockState().setValue(OrchestralBloomBlock.OPEN, true)))));
		context.register(BLOOM_CLOSED, new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(EGBlocks.ORCHESTRAL_BLOOM.get().defaultBlockState().setValue(OrchestralBloomBlock.OPEN, true)))));
		context.register(WELKIN_GRASSIFYING, new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(80, 16, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(EGBlocks.WELKIN_SPROUTS.get())), BlockPredicate.allOf(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), EGBlocks.WELKIN_GRASS.get()), BlockPredicate.matchesBlocks(Blocks.AIR)))))); // [VanillaCopy] Registration of PATCH_GRASS_JUNGLE in VegetationFeatures

		context.register(WHISPERIA_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(EGBlocks.WHISPERIA_LOG.get()),
			new WisteriaTrunkPlacer(
				7,
				1,
				0,
				new WeightedListInt(
					SimpleWeightedRandomList.<IntProvider>builder().add(ConstantInt.of(2), 3).add(ConstantInt.of(3), 3).add(ConstantInt.of(4), 1).build()
				),
				UniformInt.of(2, 4),
				UniformInt.of(-4, -3),
				UniformInt.of(-1, 0)
			),
			BlockStateProvider.simple(EGBlocks.WHISPERIA_LEAVES.get()),
			new WisteriaFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), BlockStateProvider.simple(EGBlocks.WHISPERIA_HANGINGS.get()), 0.25F, 0.5F, 0.25F),
			new TwoLayersFeatureSize(1, 0, 2)).build()));

		context.register(GHOST_WHISPERIA_TREE, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
			BlockStateProvider.simple(EGBlocks.WHISPERIA_LOG.get()),
			new WisteriaTrunkPlacer(
				7,
				1,
				0,
				new WeightedListInt(
					SimpleWeightedRandomList.<IntProvider>builder().add(ConstantInt.of(2), 3).add(ConstantInt.of(3), 3).add(ConstantInt.of(4), 1).build()
				),
				UniformInt.of(2, 4),
				UniformInt.of(-4, -3),
				UniformInt.of(-1, 0)
			),
			BlockStateProvider.simple(EGBlocks.GHOST_WHISPERIA_LEAVES.get()),
			new WisteriaFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), BlockStateProvider.simple(EGBlocks.GHOST_WHISPERIA_HANGINGS.get()), 0.25F, 0.5F, 0.25F),
			new TwoLayersFeatureSize(1, 0, 2)).build()));
	}

	public static ResourceKey<ConfiguredFeature<?, ?>> create(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(EG.MODID, name));
	}
}
