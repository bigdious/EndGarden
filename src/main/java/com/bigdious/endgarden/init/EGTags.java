package com.bigdious.endgarden.init;

import com.bigdious.endgarden.EG;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class EGTags {
	public static class Biomes {
		public static final TagKey<Biome> HAS_WELKIN_FIELDS = create("has_feature/welkin_fields");
		private static TagKey<Biome> create(String name) {
			return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(EG.MODID, name));
		}
	}

	public static class Blocks {
		public static final TagKey<Block> WHISPERING_WISTERIA_LOGS = BlockTags.create(EG.prefix("whispering_wisteria_logs"));

		private static TagKey<Block> create(String name) {
			return BlockTags.create(ResourceLocation.fromNamespaceAndPath(EG.MODID, name));
		}
	}

	public static class Items {
		public static final TagKey<Item> WHISPERIA_LOGS = ItemTags.create(EG.prefix("whisperia_logs"));
		public static final TagKey<Item> ORCHESTRAL_BLOOM_TRIGGERS = ItemTags.create(EG.prefix("orchestral_bloom_triggers"));

		private static TagKey<Item> create(String name) {
			return ItemTags.create(ResourceLocation.fromNamespaceAndPath(EG.MODID, name));
		}
	}

	public static class Entities {
		public static final TagKey<EntityType<?>> IMMUNE_TO_BLOOM_TELEPORT = create("immune_to_bloom_teleport");

		private static TagKey<EntityType<?>> create(String name) {
			return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EG.MODID, name));
		}
	}
}
