package com.bigdious.endgarden.data;

import com.bigdious.endgarden.EG;
import com.bigdious.endgarden.init.EGSoundEffects;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.Nullable;

public class SoundDefinitionGenerator extends SoundDefinitionsProvider {
	public SoundDefinitionGenerator(PackOutput output, ExistingFileHelper helper) {
		super(output, EG.MODID, helper);
	}

	@Override
	public void registerSounds() {
		this.generateExistingSoundWithSubtitle(EGSoundEffects.ORCHESTRAL_BLOOM_OPENS, SoundEvents.ITEM_FRAME_REMOVE_ITEM, "Orchestral Bloom opens");
	}

	public void generateExistingSoundWithSubtitle(DeferredHolder<SoundEvent, SoundEvent> event, SoundEvent referencedSound, String subtitle) {
		this.generateExistingSoundWithSubtitle(event, referencedSound, subtitle, 1.0F, 1.0F);
	}

	public void generateExistingSoundWithSubtitle(DeferredHolder<SoundEvent, SoundEvent> event, SoundEvent referencedSound, String subtitle, float volume, float pitch) {
		this.generateExistingSound(event, referencedSound, subtitle, volume, pitch);
	}

	public void generateExistingSound(DeferredHolder<SoundEvent, SoundEvent> event, SoundEvent referencedSound, @Nullable String subtitle, float volume, float pitch) {
		SoundDefinition definition = SoundDefinition.definition();
		if (subtitle != null) {
			this.createSubtitleAndLangEntry(event, definition, subtitle);
		}
		this.add(event, definition
			.with(SoundDefinition.Sound.sound(referencedSound.getLocation(), SoundDefinition.SoundType.EVENT).volume(volume).pitch(pitch)));
	}


	private void createSubtitleAndLangEntry(DeferredHolder<SoundEvent, SoundEvent> event, SoundDefinition definition, String subtitle) {
		String subtitleKey = "subtitles.endgarden." + event.getId().getPath();
		definition.subtitle(subtitleKey);
		EGLangGenerator.SUBTITLE_GENERATOR.put(subtitleKey, subtitle);
	}
}
