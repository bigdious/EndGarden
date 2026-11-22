package com.bigdious.endgarden;

import com.bigdious.endgarden.blocks.OrchestralBloomBlock;
import com.bigdious.endgarden.init.EGBlocks;
import com.bigdious.endgarden.init.EGItems;
import com.bigdious.endgarden.init.EGPOITypes;
import com.bigdious.endgarden.init.EGTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.AdvancementEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.Optional;
import java.util.function.Predicate;

public class EGEvents {
	public static void initEvents(IEventBus bus) {
		NeoForge.EVENT_BUS.addListener(EGEvents::teleportBloom);
		NeoForge.EVENT_BUS.addListener(EGEvents::noteAppears);
	}

	private static void teleportBloom(EntityTickEvent.Post event) {
		if(event.getEntity().level() instanceof ServerLevel serverLevel &&
			event.getEntity() instanceof LivingEntity living &&
			!living.getType().is(EGTags.Entities.IMMUNE_TO_BLOOM_TELEPORT) &&
			event.getEntity().level().getBlockState(event.getEntity().getOnPos().above()).is(EGBlocks.ORCHESTRAL_BLOOM)) {
			BlockState bloom = event.getEntity().level().getBlockState(event.getEntity().getOnPos().above());
			Level level = event.getEntity().level();
			BlockPos pos = event.getEntity().getOnPos().above();
			if (bloom.getValue(OrchestralBloomBlock.OPEN)) {
				Predicate<Holder<PoiType>> predicate = holder -> holder.is(EGPOITypes.ORCHESTRAL_BLOOM.getKey());
				Optional<BlockPos> target = serverLevel.getPoiManager().findClosest(predicate, position -> position.getX() != pos.getX(), pos, 50, PoiManager.Occupancy.ANY);
				if (target.isPresent()) {
					living.setDeltaMovement(Vec3.ZERO);
					living.teleportTo(target.get().getX() + 0.5 , target.get().getY(), target.get().getZ() + 0.5);
					level.setBlock(target.get(), EGBlocks.ORCHESTRAL_BLOOM.get().defaultBlockState().setValue(OrchestralBloomBlock.OPEN, false), 11);
					level.playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.BLOCKS);
					level.gameEvent(GameEvent.TELEPORT, target.get(), GameEvent.Context.of(living));
				}
				level.setBlock(pos, EGBlocks.ORCHESTRAL_BLOOM.get().defaultBlockState().setValue(OrchestralBloomBlock.OPEN, false), 11);
			}
		}
	}

	private static void noteAppears(AdvancementEvent.AdvancementEarnEvent event) {
		Player player = event.getEntity();
		Level level = player.level();
		if (event.getAdvancement().id().toString().equals("endgarden:note")) {
			ItemEntity note = EntityType.ITEM.create(level);
			note.setItem(EGItems.COMPOSERS_NOTE.toStack());
			note.moveTo(player.getX(), player.getY(), player.getZ());
			level.addFreshEntity(note);
		}
	}
}
