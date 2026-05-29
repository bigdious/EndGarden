package com.bigdious.endgarden;

import com.bigdious.endgarden.client.EGClientEvents;
import com.bigdious.endgarden.config.ConfigSetup;
import com.bigdious.endgarden.data.*;
import com.bigdious.endgarden.init.EGBlockEntities;
import com.bigdious.endgarden.init.EGBlocks;
import com.bigdious.endgarden.init.EGItems;
import com.bigdious.endgarden.init.EGTab;
import com.bigdious.endgarden.worldgen.features.EGFeatures;
import com.google.common.reflect.Reflection;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

@Mod(EG.MODID)
public class EG {
	public static final String MODID = "endgarden";

	public static final Logger LOGGER = LogManager.getLogger();

	public EG(IEventBus bus, Dist dist) {
		Reflection.initialize(ConfigSetup.class);
		ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> ConfigurationScreen::new);
		bus.addListener(ConfigSetup::loadConfigs);
		bus.addListener(ConfigSetup::reloadConfigs);
		NeoForge.EVENT_BUS.addListener(ConfigSetup::syncConfigOnLogin);
		bus.addListener(this::registerPacket);
		bus.addListener(EGTab::addToTabs);

		EGBlockEntities.BLOCK_ENTITIES.register(bus);
		EGBlocks.BLOCKS.register(bus);
		EGItems.ITEMS.register(bus);
		EGFeatures.FEATURES.register(bus);

		if (dist.isClient()) {
			EGClientEvents.initEvents(bus);
		}

		bus.addListener(this::gatherData);


	}

	private void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		DatapackBuiltinEntriesProvider datapackProvider = new RegistryDataGenerator(packOutput, event.getLookupProvider());
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		boolean isClient = event.includeClient();
		generator.addProvider(isClient, new EGBlockModelGenerator(packOutput, existingFileHelper));
		generator.addProvider(isClient, new EGItemModelGenerator(packOutput, existingFileHelper));
		generator.addProvider(isClient, new EGLangGenerator(packOutput));

		boolean isServer = event.includeServer();

		RegistryDataGenerator registryDataGenerator = new RegistryDataGenerator(packOutput, datapackProvider.getRegistryProvider());
		var lookupProvider = registryDataGenerator.getRegistryProvider();
		var blocktags = new EGBlockTagGenerator(packOutput, lookupProvider, existingFileHelper);
		generator.addProvider(isServer, blocktags);

		//FUCKER, BE ATTENTIVE FOR THE BELOW
		generator.addProvider(isServer, registryDataGenerator);

		generator.addProvider(isServer, new EGItemTagGenerator(packOutput, lookupProvider, blocktags.contentsGetter(), existingFileHelper));
		generator.addProvider(isServer, new EGLootGenerator(packOutput, lookupProvider));
		generator.addProvider(isServer, new EGCraftingGenerator(packOutput, lookupProvider));
		generator.addProvider(isServer, new BiomeTagsGenerator(packOutput, lookupProvider, existingFileHelper));
	}

	public void registerPacket(RegisterPayloadHandlersEvent event) {
		PayloadRegistrar registrar = event.registrar(MODID).versioned("1.0.1");
	}
	public static ResourceLocation prefix(String name) {
		return ResourceLocation.fromNamespaceAndPath(MODID, name.toLowerCase(Locale.ROOT));
	}

}
