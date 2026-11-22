package com.bigdious.endgarden.items;

import com.bigdious.endgarden.init.EGBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.List;

public class WelkinGrassBlockItem extends BlockItem {
	public WelkinGrassBlockItem(Block block, Properties properties) {
		super(block, properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
		tooltipComponents.add(Component.translatable("tooltip.eg.welkin_grass").withStyle(ChatFormatting.GRAY));
	}


	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		int range = (int) player.getAttribute(Attributes.BLOCK_INTERACTION_RANGE).getValue();
		if (!level.isClientSide()) {
			//bad at math, so accounting for negative coordinate adaption this way
			int x = (int) (player.getEyePosition().x() + (player.getEyePosition().x() < 0 ? -1 : 0) + range * player.getLookAngle().x);
			int y = (int) (player.getEyePosition().y() + (player.getEyePosition().y() < 0 ? -1 : 0) + range * player.getLookAngle().y);
			int z = (int) (player.getEyePosition().z() + (player.getEyePosition().z() < 0 ? -1 : 0) + range * player.getLookAngle().z);

			BlockPos blockPos = new BlockPos(x, y, z);
			if (level.getBlockState(blockPos).isAir() || level.getBlockState(blockPos).canBeReplaced()) {
				level.setBlock(blockPos, this.getBlock().defaultBlockState(), 11);
				if (!player.isCreative()) {
					itemstack.consume(1, player);
				}
				level.gameEvent(player, GameEvent.BLOCK_PLACE, blockPos);
				player.awardStat(Stats.ITEM_USED.get(this));
				SoundType soundtype = this.getBlock().defaultBlockState().getSoundType(level, blockPos, player);
				level.playSound(null, blockPos, this.getPlaceSound(this.getBlock().defaultBlockState(), level, blockPos, player), SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

				return InteractionResultHolder.consume(itemstack);
			}
		}
		return InteractionResultHolder.fail(itemstack);
	}
}
