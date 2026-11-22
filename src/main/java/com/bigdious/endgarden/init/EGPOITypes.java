package com.bigdious.endgarden.init;

import com.bigdious.endgarden.EG;
import com.bigdious.endgarden.blocks.OrchestralBloomBlock;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.stream.Collectors;

public class EGPOITypes {
	public static final DeferredRegister<PoiType> POIS = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, EG.MODID);
	public static final DeferredHolder<PoiType, PoiType> ORCHESTRAL_BLOOM = POIS.register("orchestral_bloom", () -> new PoiType(ImmutableSet.copyOf(EGBlocks.ORCHESTRAL_BLOOM.get().getStateDefinition().getPossibleStates().stream().filter(state -> state.getValue(OrchestralBloomBlock.OPEN)).collect(Collectors.toSet())), 0, 1));

}
