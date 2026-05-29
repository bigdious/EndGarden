package com.bigdious.endgarden.data;

import com.bigdious.endgarden.data.helper.EGLangProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;


public class EGLangGenerator extends EGLangProvider {

	public EGLangGenerator(PackOutput output) {
		super(output);
	}
	@Override
	protected void addTranslations() {
	}

	public void addBiome(ResourceKey<Biome> biome, String name) {
		this.add("biome.endgarden." + biome.location().getPath(), name);
	}
}
