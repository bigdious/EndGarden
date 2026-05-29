package com.bigdious.endgarden.data;

import com.bigdious.endgarden.EG;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EGBlockTagGenerator extends BlockTagsProvider {
	public EGBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, EG.MODID, existingFileHelper);
	}
	@Override
	protected void addTags(HolderLookup.Provider provider) {

	}
}
