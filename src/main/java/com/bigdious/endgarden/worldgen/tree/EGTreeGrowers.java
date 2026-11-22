package com.bigdious.endgarden.worldgen.tree;

import com.bigdious.endgarden.EG;
import com.bigdious.endgarden.worldgen.features.EGConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class EGTreeGrowers {
	public static final TreeGrower WHISPERIA = new TreeGrower(EG.MODID + ":whisperia", Optional.empty(), Optional.of(EGConfiguredFeatures.WHISPERIA_TREE), Optional.empty());
	public static final TreeGrower GHOST_WHISPERIA = new TreeGrower(EG.MODID + ":ghost_whisperia", Optional.empty(), Optional.of(EGConfiguredFeatures.GHOST_WHISPERIA_TREE), Optional.empty());
}
