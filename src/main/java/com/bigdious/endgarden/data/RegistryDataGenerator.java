package com.bigdious.endgarden.data;

import com.bigdious.endgarden.EG;
import com.bigdious.endgarden.worldgen.features.EGBiomeModifiers;
import com.bigdious.endgarden.worldgen.features.EGConfiguredFeatures;
import com.bigdious.endgarden.worldgen.features.EGPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RegistryDataGenerator extends DatapackBuiltinEntriesProvider {
	private static final RegistrySetBuilder REGISTRIES = new RegistrySetBuilder()
		.add(Registries.CONFIGURED_FEATURE, EGConfiguredFeatures::bootstrap)
		.add(Registries.PLACED_FEATURE, EGPlacedFeatures::bootstrap)
		.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, EGBiomeModifiers::bootstrap);

	public RegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {

		super(output, registries, REGISTRIES, Set.of("minecraft", EG.MODID));
	}
}
