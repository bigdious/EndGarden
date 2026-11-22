package com.bigdious.endgarden.data;

import com.bigdious.endgarden.data.helper.EGLangProvider;
import com.bigdious.endgarden.init.EGBlocks;
import com.bigdious.endgarden.init.EGEntities;
import com.bigdious.endgarden.init.EGItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.HashMap;
import java.util.Map;


public class EGLangGenerator extends EGLangProvider {

	public static final Map<String, String> SUBTITLE_GENERATOR = new HashMap<>();

	public EGLangGenerator(PackOutput output) {
		super(output);
	}
	@Override
	protected void addTranslations() {
		this.addBlock(EGBlocks.ORCHESTRAL_BLOOM, "Orchestral Bloom");
		this.addBlock(EGBlocks.STAR_FERN_MAGENTA, "Magenta Star Fern");
		this.addBlock(EGBlocks.STAR_FERN_TEAL, "Teal Star Fern");
		this.addBlock(EGBlocks.WELKIN_GRASS, "Welkin Grass");
		this.addBlock(EGBlocks.WELKIN_GRASS_BUNDLE, "Welkin Grass Bundle");
		this.addBlock(EGBlocks.WELKIN_SPROUTS, "Welkin Sprouts");
		this.addBlock(EGBlocks.WHISPERIA_BUTTON, "Whisperia Button");
		this.addBlock(EGBlocks.WHISPERIA_DOOR, "Whisperia Door");
		this.addBlock(EGBlocks.WHISPERIA_FENCE, "Whisperia Fence");
		this.addBlock(EGBlocks.WHISPERIA_FENCE_GATE, "Whisperia Fence Gate");
		this.addBlock(EGBlocks.WHISPERIA_HANGING_SIGN, "Whisperia Hanging Sign");
		this.addBlock(EGBlocks.WHISPERIA_LOG, "Whisperia Log");
		this.addBlock(EGBlocks.STRIPPED_WHISPERIA_LOG, "Stripped Whisperia Log");
		this.addBlock(EGBlocks.WHISPERIA_PLANKS, "Whisperia Planks");
		this.addBlock(EGBlocks.WHISPERIA_PRESSURE_PLATE, "Whisperia Pressure Plate");
		this.addBlock(EGBlocks.WHISPERIA_SIGN, "Whisperia Sign");
		this.addBlock(EGBlocks.WHISPERIA_SLAB, "Whisperia Slab");
		this.addBlock(EGBlocks.WHISPERIA_STAIRS, "Whisperia Stairs");
		this.addBlock(EGBlocks.WHISPERIA_TRAPDOOR, "Whisperia Trapdoor");
		this.addBlock(EGBlocks.WHISPERIA_WOOD, "Whisperia Wood");
		this.addBlock(EGBlocks.STRIPPED_WHISPERIA_WOOD, "Stripped Whisperia Wood");
		this.addBlock(EGBlocks.WHISPERIA_SAPLING, "Whisperia Sapling");
		this.addBlock(EGBlocks.WHISPERIA_LEAVES, "Whisperia Leaves");
		this.addBlock(EGBlocks.WHISPERIA_HANGINGS, "Whisperia Hangings");
		this.addBlock(EGBlocks.GHOST_WHISPERIA_SAPLING, "Ghost Whisperia Sapling");
		this.addBlock(EGBlocks.GHOST_WHISPERIA_LEAVES, "Ghost Whisperia Leaves");
		this.addBlock(EGBlocks.GHOST_WHISPERIA_HANGINGS, "Ghost Whisperia Hangings");

		this.addItem(EGItems.WHISPERIA_BOAT, "Whisperia Boat");
		this.addItem(EGItems.WHISPERIA_CHEST_BOAT, "Whisperia Boat with Chest");
		this.addItem(EGItems.COMPOSERS_NOTE, "Abandoned Note");

		this.addEntityType(EGEntities.WHISPERIA_BOAT, "Whisperia Boat");
		this.addEntityType(EGEntities.WHISPERIA_CHEST_BOAT, "Whisperia Boat with Chest");

		this.add("itemGroup.eg.blocks", "End Garden");

		this.add("advancement.endgarden.note.desc", "Find the gardens that do not belong");
		this.add("advancement.endgarden.note", "Dissonance In A Serene Chorus");
		this.add("subtitles.endgarden.block.orchestral_bloom.opens", "Orchestral Bloom opens");
		this.add("tooltip.eg.welkin_grass", "Can be bonemealed");
		this.add("tooltip.eg.note", """
			When she showed me this World Layer that she had created, I was overwhelmed with guilt. Yet, as per her words, the End did not represent a tragedy, nor was it sorrow or melancholy. She didn't reveal to me its exact meaning, but she did let me know that it was hopeful - even happy.\s

			I find myself still questioning her truthfulness.

			I can't help but interfere now. Not out of disregard, but out of selfish care. I'll gift her gorgeous gardens that she can easily tear down if she wishes to.

			I have made sure to hide these from the young one though. He does not need to know this side of the story. One obsession is already plenty... I would know. \
			""");

		SUBTITLE_GENERATOR.forEach(this::add);
	}

	public void addBiome(ResourceKey<Biome> biome, String name) {
		this.add("biome.endgarden." + biome.location().getPath(), name);
	}
}
