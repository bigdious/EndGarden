package com.bigdious.endgarden.data;

import com.bigdious.endgarden.EG;
import com.bigdious.endgarden.init.EGBlocks;
import com.bigdious.endgarden.init.EGItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.packs.VanillaTheEndAdvancements;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Consumer;

public class EGAdvancementGenerator implements AdvancementProvider.AdvancementGenerator{

	@Override
	public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> consumer, ExistingFileHelper existingFileHelper) {
		AdvancementHolder note = Advancement.Builder.advancement().parent(Advancement.Builder.advancement().build(ResourceLocation.withDefaultNamespace("end/kill_dragon")))
			.display(EGBlocks.ORCHESTRAL_BLOOM.get(),
				Component.translatable("advancement.endgarden.note"),
				Component.translatable("advancement.endgarden.note.desc"), null, AdvancementType.GOAL, true, true, true)
			.requirements(AdvancementRequirements.Strategy.OR)
			.addCriterion("anyEGitem1", InventoryChangeTrigger.TriggerInstance.hasItems(EGItems.WELKIN_GRASS.get()))
			.addCriterion("anyEGitem2", InventoryChangeTrigger.TriggerInstance.hasItems(EGBlocks.ORCHESTRAL_BLOOM.get()))
			.addCriterion("anyEGitem3", InventoryChangeTrigger.TriggerInstance.hasItems(EGBlocks.WELKIN_SPROUTS.get()))
			.addCriterion("anyEGitem4", InventoryChangeTrigger.TriggerInstance.hasItems(EGBlocks.WHISPERIA_SAPLING.get()))
			.addCriterion("anyEGitem5", InventoryChangeTrigger.TriggerInstance.hasItems(EGBlocks.WHISPERIA_LOG.get()))
			.addCriterion("anyEGitem6", InventoryChangeTrigger.TriggerInstance.hasItems(EGBlocks.WHISPERIA_HANGINGS.get()))
			.addCriterion("anyEGitem7", InventoryChangeTrigger.TriggerInstance.hasItems(EGBlocks.WHISPERIA_LEAVES.get()))
			.addCriterion("anyEGitem8", InventoryChangeTrigger.TriggerInstance.hasItems(EGBlocks.STAR_FERN_MAGENTA.get()))
			.addCriterion("anyEGitem9", InventoryChangeTrigger.TriggerInstance.hasItems(EGBlocks.STAR_FERN_TEAL.get()))
			.save(consumer, "endgarden:note");
	}
}
