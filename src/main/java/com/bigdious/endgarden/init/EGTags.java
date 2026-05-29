package com.bigdious.endgarden.init;

import com.bigdious.endgarden.EG;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class EGTags {
	public static class Biomes {
		public static final TagKey<Biome> HAS_WELKIN_FIELDS = create("has_feature/welkin_fields");
		private static TagKey<Biome> create(String name) {
			return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(EG.MODID, name));
		}
	}
}
