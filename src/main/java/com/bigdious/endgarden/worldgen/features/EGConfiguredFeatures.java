package com.bigdious.endgarden.worldgen.features;

import com.bigdious.endgarden.EG;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class EGConfiguredFeatures {

	public static final ResourceKey<ConfiguredFeature<?, ?>> WELKIN_GRASS_PATCH = create("welkin_grass_patch");
	public static final ResourceKey<ConfiguredFeature<?, ?>> WELKIN_GRASSIFYING = create("welkin_grassifying");



	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

		context.register(WELKIN_GRASS_PATCH, new ConfiguredFeature<>(EGFeatures.WELKIN_PATCH.get(), new BlockStateConfiguration(Blocks.DIAMOND_BLOCK.defaultBlockState())));
		context.register(WELKIN_GRASSIFYING, new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchConfiguration(64, 16, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.EMERALD_BLOCK)), BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.DIAMOND_BLOCK))))); // [VanillaCopy] Registration of PATCH_GRASS_JUNGLE in VegetationFeatures
	}

	public static ResourceKey<ConfiguredFeature<?, ?>> create(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(EG.MODID, name));
	}
}
