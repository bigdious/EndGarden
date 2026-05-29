package com.bigdious.endgarden.config;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigSetup {
	private static final ModConfigSpec COMMON_SPEC;
	private static final EGCommonConfig COMMON_CONFIG;

	static {
		{
			final Pair<EGCommonConfig, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(EGCommonConfig::new);
			ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.COMMON, COMMON_SPEC = specPair.getRight());
			COMMON_CONFIG = specPair.getLeft();
		}

	}

	public static void loadConfigs(ModConfigEvent.Loading event) {
		if (event.getConfig().getSpec() == COMMON_SPEC) {
			EGConfig.rebakeCommonOptions(COMMON_CONFIG);
		}
	}

	public static void reloadConfigs(ModConfigEvent.Reloading event) {
		 if (event.getConfig().getSpec() == ConfigSetup.COMMON_SPEC) {
			EGConfig.rebakeCommonOptions(COMMON_CONFIG);
		}
	}

	public static void syncConfigOnLogin(PlayerEvent.PlayerLoggedInEvent event) {
		MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
		if (server != null && server.isDedicatedServer() && event.getEntity() instanceof ServerPlayer player) {
		}
	}
}
