package com.bigdious.endgarden.init;

import com.bigdious.endgarden.EG;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EGSoundEffects {
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, EG.MODID);

	public static final DeferredHolder<SoundEvent, SoundEvent> ORCHESTRAL_BLOOM_OPENS = register("block.orchestral_bloom.opens");

	private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
		return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(EG.prefix(name)));
	}

}
