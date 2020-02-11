package com.github.soravoid.exceed.init;

import com.github.soravoid.exceed.Exceed;
import com.github.soravoid.exceed.items.weapons.StaticElectricitySword;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

public final class ExceedItems
{
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Exceed.MODID);

    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", () -> new Item(new Item.Properties().group(Exceed.EXCEED_TAB)));
    public static final RegistryObject<Item> STATIC_SWORD = ITEMS.register("static_sword", StaticElectricitySword::new);
}
