package com.bigdious.endgarden.data;

import com.bigdious.endgarden.EG;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class EGBlockModelGenerator extends BlockStateProvider {
	public EGBlockModelGenerator(PackOutput output, ExistingFileHelper helper) {
		super(output, EG.MODID, helper);
	}
	@Override
	protected void registerStatesAndModels() {

	}
}
