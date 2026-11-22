package com.bigdious.endgarden.init;

import com.bigdious.endgarden.EG;
import com.bigdious.endgarden.entities.EGBoat;
import com.bigdious.endgarden.entities.EGChestBoat;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EGEntities {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, EG.MODID);

	public static final DeferredHolder<EntityType<?>, EntityType<EGBoat>> WHISPERIA_BOAT = register(ResourceLocation.fromNamespaceAndPath(EG.MODID, "whisperia_boat"), EntityType.Builder.<EGBoat>of(EGBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F));
	public static final DeferredHolder<EntityType<?>, EntityType<EGChestBoat>> WHISPERIA_CHEST_BOAT = register(ResourceLocation.fromNamespaceAndPath(EG.MODID, "whisperia_chest_boat"), EntityType.Builder.<EGChestBoat>of(EGChestBoat::new, MobCategory.MISC).sized(1.375F, 0.5625F));

	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(ResourceLocation id, EntityType.Builder<T> builder) {
		return ENTITIES.register(id.getPath(), () -> builder.build(id.toString()));
	}

}
