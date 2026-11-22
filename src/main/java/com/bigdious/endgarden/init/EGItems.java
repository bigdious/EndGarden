package com.bigdious.endgarden.init;

import com.bigdious.endgarden.EG;
import com.bigdious.endgarden.entities.EGBoat;
import com.bigdious.endgarden.entities.EGChestBoat;
import com.bigdious.endgarden.items.ComposersNoteItem;
import com.bigdious.endgarden.items.EGBoatItem;
import com.bigdious.endgarden.items.WelkinGrassBlockItem;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class EGItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EG.MODID);


	public static final DeferredItem<Item> WELKIN_GRASS = register("welkin_grass", properties -> new WelkinGrassBlockItem(EGBlocks.WELKIN_GRASS.get(), properties), Item.Properties::new);
	public static final DeferredItem<Item> WHISPERING_WISTERIA_DOOR = register("whisperia_door", properties -> new DoubleHighBlockItem(EGBlocks.WHISPERIA_DOOR.get(), properties), Item.Properties::new);
	public static final DeferredItem<Item> WHISPERING_WISTERIA_SIGN = register("whisperia_sign", properties -> new SignItem(properties, EGBlocks.WHISPERIA_SIGN.get(), EGBlocks.WHISPERIA_WALL_SIGN.get()), () -> new Item.Properties().stacksTo(16));
	public static final DeferredItem<Item> WHISPERING_WISTERIA_HANGING_SIGN = register("whisperia_hanging_sign", properties -> new HangingSignItem(EGBlocks.WHISPERIA_HANGING_SIGN.get(), EGBlocks.WHISPERIA_WALL_HANGING_SIGN.get(), properties), () -> new Item.Properties().stacksTo(16));
	public static final DeferredItem<Item> WHISPERIA_BOAT = ITEMS.register("whisperia_boat", () -> new EGBoatItem(false, EGBoat.Type.WHISPERIA, new Item.Properties().stacksTo(1)));
	public static final DeferredItem<Item> WHISPERIA_CHEST_BOAT = register("whisperia_chest_boat", properties -> new EGBoatItem(true, EGBoat.Type.WHISPERIA, properties), () -> new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> COMPOSERS_NOTE = register("composers_note", ComposersNoteItem::new, () -> new Item.Properties().stacksTo(1));

	public static <T extends Item> DeferredItem<T> register(String name, Function<Item.Properties, T> item, Supplier<Item.Properties> properties) {
		return ITEMS.register(name, () -> item.apply(properties.get()));
	}

}