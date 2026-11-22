package com.bigdious.endgarden.worldgen.tree;

import com.bigdious.endgarden.EG;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EGTreestuffPlacerTypes {
	public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, EG.MODID);
	public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, EG.MODID);

	public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<WisteriaTrunkPlacer>> WISTERIA_TRUNK_PLACER = TRUNK_PLACERS.register("wisteria_trunk_placer", () -> new TrunkPlacerType<>(WisteriaTrunkPlacer.CODEC));
	public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<WisteriaFoliagePlacer>> WISTERIA_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("wisteria_foliage_placer", () -> new FoliagePlacerType<>(WisteriaFoliagePlacer.CODEC));

}
