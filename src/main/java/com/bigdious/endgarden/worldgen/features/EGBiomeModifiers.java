package com.bigdious.endgarden.worldgen.features;

import com.bigdious.endgarden.EG;
import com.bigdious.endgarden.init.EGTags;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class EGBiomeModifiers {

	public static final ResourceKey<BiomeModifier> ADD_WELKIN_FIELDS = create("add_welkin_fields");
	public static final ResourceKey<BiomeModifier> ADD_GRASS_TO_WELKIN = create("add_grass_to_welkin");

	public static void bootstrap(BootstrapContext<BiomeModifier> context) {
		var biomes = context.lookup(Registries.BIOME);
		var placedFeatures = context.lookup(Registries.PLACED_FEATURE);

		context.register(ADD_WELKIN_FIELDS, new BiomeModifiers.AddFeaturesBiomeModifier(
			biomes.getOrThrow(EGTags.Biomes.HAS_WELKIN_FIELDS),
			HolderSet.direct(placedFeatures.getOrThrow(EGPlacedFeatures.WELKIN_GRASS_PATCH)),
			GenerationStep.Decoration.LOCAL_MODIFICATIONS
		));

		context.register(ADD_GRASS_TO_WELKIN, new BiomeModifiers.AddFeaturesBiomeModifier(
			biomes.getOrThrow(EGTags.Biomes.HAS_WELKIN_FIELDS),
			HolderSet.direct(placedFeatures.getOrThrow(EGPlacedFeatures.WELKIN_GRASSIFYING)),
			GenerationStep.Decoration.VEGETAL_DECORATION
		));
	}

	public static ResourceKey<BiomeModifier> create(String name) {
		return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(EG.MODID, name));
	}

}
