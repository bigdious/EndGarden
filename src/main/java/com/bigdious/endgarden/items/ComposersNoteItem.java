package com.bigdious.endgarden.items;

import com.bigdious.endgarden.init.EGTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ComposersNoteItem extends Item {
	public ComposersNoteItem(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
		tooltipComponents.add(Component.translatable("tooltip.eg.note").withStyle(ChatFormatting.BLUE, ChatFormatting.ITALIC));
	}

}
