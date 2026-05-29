package com.bigdious.endgarden.worldgen.features;

import com.bigdious.endgarden.EG;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EGFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, EG.MODID);
//make sure size values are always even
	public static final DeferredHolder<Feature<?>, Feature<BlockStateConfiguration>> WELKIN_PATCH = FEATURES.register("welkin_patch", () -> new WelkinIslandFeature(BlockStateConfiguration.CODEC, 3, 7));

}
