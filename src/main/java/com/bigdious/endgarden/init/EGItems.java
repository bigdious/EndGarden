package com.bigdious.endgarden.init;

import com.bigdious.endgarden.EG;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class EGItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EG.MODID);
	public static <T extends Item> DeferredItem<T> register(String name, Function<Item.Properties, T> item, Supplier<Item.Properties> properties) {
		return ITEMS.register(name, () -> item.apply(properties.get()));
	}

}