package com.bigdious.endgarden.client;

import com.bigdious.endgarden.client.renderer.AnimationRenderHelper;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;

public class EGClientEvents {
	public static void initEvents(IEventBus bus) {
		bus.addListener(EGClientEvents::registerEntityRenderers);
		NeoForge.EVENT_BUS.addListener(EGClientEvents::clientTick);
	}
	private static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {

//		event.registerBlockEntityRenderer(EGBlockEntities.DISPLAY_NOTCH.get(), DisplayNotchRenderer::new);
	}

	private static void clientTick(ClientTickEvent.Post event) {
		Minecraft mc = Minecraft.getInstance();

		if (!mc.isPaused()) {
			AnimationRenderHelper.animate();
		}
	}
}
