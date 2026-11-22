package com.bigdious.endgarden.worldgen.features;

import com.bigdious.endgarden.EG;
import com.bigdious.endgarden.blocks.OrchestralBloomBlock;
import com.bigdious.endgarden.init.EGBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class EGPlacedFeatures {

	public static final ResourceKey<PlacedFeature> WELKIN_GRASS_PATCH = create("welkin_grass_patch");
	public static final ResourceKey<PlacedFeature> WELKIN_GRASSIFYING = create("welkin_grassifying");
	public static final ResourceKey<PlacedFeature> WHISPERIA = create("whisperia");
	public static final ResourceKey<PlacedFeature> STAR_FERN = create("star_fern");
	public static final ResourceKey<PlacedFeature> STAR_FERN_2 = create("star_fern_2");
	public static final ResourceKey<PlacedFeature> BLOOM_OPEN = create("bloom_open");
	public static final ResourceKey<PlacedFeature> BLOOM_CLOSED = create("bloom_closed");

	public static ResourceKey<PlacedFeature> create(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(EG.MODID, name));
	}

	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> features = context.lookup(Registries.CONFIGURED_FEATURE);
		context.register(WELKIN_GRASS_PATCH, new PlacedFeature(features.getOrThrow(EGConfiguredFeatures.WELKIN_GRASS_PATCH), patchWithFilter(1, BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.AIR))));
		context.register(WELKIN_GRASSIFYING, new PlacedFeature(features.getOrThrow(EGConfiguredFeatures.WELKIN_GRASSIFYING), ImmutableList.<PlacementModifier>builder().add(CountPlacement.of(100), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(25), VerticalAnchor.absolute(75)), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.AIR)) ,BiomeFilter.biome()).build()));
		context.register(WHISPERIA, new PlacedFeature(features.getOrThrow(EGConfiguredFeatures.WHISPERIA_TREE), VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), EGBlocks.WHISPERIA_SAPLING.get())));
		context.register(STAR_FERN, new PlacedFeature(features.getOrThrow(EGConfiguredFeatures.STAR_FERN), VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.5f, 5), EGBlocks.STAR_FERN_TEAL.get())));
		context.register(STAR_FERN_2, new PlacedFeature(features.getOrThrow(EGConfiguredFeatures.STAR_FERN_2), VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.5f, 5), EGBlocks.STAR_FERN_MAGENTA.get())));
		context.register(BLOOM_OPEN, new PlacedFeature(features.getOrThrow(EGConfiguredFeatures.BLOOM_OPEN), VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.5f, 1))));
		context.register(BLOOM_CLOSED, new PlacedFeature(features.getOrThrow(EGConfiguredFeatures.BLOOM_CLOSED), VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.5f, 1))));

	}

	private static List<PlacementModifier> patchWithFilter(int count, BlockPredicate filter) {

		return List.of(CountPlacement.of(count), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(25), VerticalAnchor.absolute(70)), BlockPredicateFilter.forPredicate(filter), BiomeFilter.biome());
	}

}
