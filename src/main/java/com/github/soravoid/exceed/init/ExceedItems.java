package com.github.soravoid.exceed.init;

import com.github.soravoid.exceed.Exceed;
import com.github.soravoid.exceed.items.armor.TetheredArmor;
import com.github.soravoid.exceed.items.scrolls.LooseCannonScroll;
import com.github.soravoid.exceed.items.weapons.HellfireSword;
import com.github.soravoid.exceed.items.weapons.StaticElectricitySword;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

import java.rmi.registry.Registry;

public final class ExceedItems
{
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Exceed.MODID);

    public static final RegistryObject<Item> TETHERED_INGOT = ITEMS.register("tethered_ingot", () -> new Item(new Item.Properties().group(Exceed.EXCEED_TAB)));

    //Scrolls
    public static final RegistryObject<Item> LOOSE_CANNON_SCROLL = ITEMS.register("loose_cannon_scroll", () -> new LooseCannonScroll());

    //Armor
    public static final RegistryObject<Item> TETHERED_HELMET = ITEMS.register("tethered_helmet", () -> new TetheredArmor(EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> TETHERED_CHESTPLATE = ITEMS.register("tethered_chestplate", () -> new TetheredArmor(EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> TETHERED_LEGGINGS = ITEMS.register("tethered_leggings", () -> new TetheredArmor(EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> TETHERED_BOOTS = ITEMS.register("tethered_boots", () -> new TetheredArmor(EquipmentSlotType.FEET));

    //Swords
    public static final RegistryObject<Item> STATIC_SWORD = ITEMS.register("static_sword", StaticElectricitySword::new);
    public static final RegistryObject<Item> HELLFIRE_SWORD = ITEMS.register("hellfire_sword", HellfireSword::new);
}
