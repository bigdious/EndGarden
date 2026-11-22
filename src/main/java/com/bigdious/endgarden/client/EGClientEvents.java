package com.bigdious.endgarden.client;

import com.bigdious.endgarden.client.renderer.AnimationRenderHelper;
import com.bigdious.endgarden.client.renderer.EGBoatRenderer;
import com.bigdious.endgarden.entities.EGBoat;
import com.bigdious.endgarden.init.EGEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;

public class EGClientEvents {
	public static void initEvents(IEventBus bus) {
		bus.addListener(EGClientEvents::registerEntityRenderers);
		NeoForge.EVENT_BUS.addListener(EGClientEvents::clientTick);
		bus.addListener(EGClientEvents::registerEntityLayers);
	}
	private static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(EGEntities.WHISPERIA_BOAT.get(), (context) -> new EGBoatRenderer(context, false));
		event.registerEntityRenderer(EGEntities.WHISPERIA_CHEST_BOAT.get(), (context) -> new EGBoatRenderer(context, true));
	}

	private static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		for (EGBoat.Type boatType : EGBoat.Type.values()) {
			event.registerLayerDefinition(EGBoatRenderer.createBoatModelName(boatType), BoatModel::createBodyModel);
			event.registerLayerDefinition(EGBoatRenderer.createChestBoatModelName(boatType), ChestBoatModel::createBodyModel);
		}
	}

	private static void clientTick(ClientTickEvent.Post event) {
		Minecraft mc = Minecraft.getInstance();

		if (!mc.isPaused()) {
			AnimationRenderHelper.animate();
		}
	}
}
