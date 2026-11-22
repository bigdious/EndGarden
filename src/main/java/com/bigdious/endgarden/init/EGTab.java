package com.bigdious.endgarden.init;

import com.bigdious.endgarden.EG;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EGTab {
	public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EG.MODID);

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOCKS = CREATIVE_TABS.register("blocks", () -> CreativeModeTab.builder()
			.title(Component.translatable("itemGroup.eg.blocks"))
			.icon(() -> new ItemStack(EGBlocks.WELKIN_GRASS.get()))
			.displayItems(
				(parameters, output) -> {
					output.accept(EGBlocks.WELKIN_GRASS.get());
					output.accept(EGBlocks.WELKIN_GRASS_BUNDLE.get());
					output.accept(EGBlocks.WELKIN_SPROUTS.get());
					output.accept(EGBlocks.ORCHESTRAL_BLOOM.get());
					output.accept(EGBlocks.STAR_FERN_TEAL.get());
					output.accept(EGBlocks.STAR_FERN_MAGENTA.get());
					output.accept(EGBlocks.WHISPERIA_SAPLING.get());
					output.accept(EGBlocks.WHISPERIA_LEAVES.get());
					output.accept(EGBlocks.WHISPERIA_HANGINGS.get());
					output.accept(EGBlocks.GHOST_WHISPERIA_SAPLING.get());
					output.accept(EGBlocks.GHOST_WHISPERIA_LEAVES.get());
					output.accept(EGBlocks.GHOST_WHISPERIA_HANGINGS.get());
					output.accept(EGBlocks.WHISPERIA_LOG.get());
					output.accept(EGBlocks.WHISPERIA_WOOD.get());
					output.accept(EGBlocks.STRIPPED_WHISPERIA_LOG.get());
					output.accept(EGBlocks.STRIPPED_WHISPERIA_WOOD.get());
					output.accept(EGBlocks.WHISPERIA_PLANKS.get());
					output.accept(EGBlocks.WHISPERIA_STAIRS.get());
					output.accept(EGBlocks.WHISPERIA_SLAB.get());
					output.accept(EGBlocks.WHISPERIA_PRESSURE_PLATE.get());
					output.accept(EGBlocks.WHISPERIA_BUTTON.get());
					output.accept(EGBlocks.WHISPERIA_DOOR.get());
					output.accept(EGBlocks.WHISPERIA_TRAPDOOR.get());
					output.accept(EGBlocks.WHISPERIA_SIGN.get());
					output.accept(EGBlocks.WHISPERIA_HANGING_SIGN.get());
					output.accept(EGBlocks.WHISPERIA_FENCE.get());
					output.accept(EGBlocks.WHISPERIA_FENCE_GATE.get());
					output.accept(EGItems.WHISPERIA_BOAT.get());
					output.accept(EGItems.WHISPERIA_CHEST_BOAT.get());
	}
			).build());
}
