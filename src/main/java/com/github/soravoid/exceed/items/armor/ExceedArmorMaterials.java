package com.github.soravoid.exceed.items.armor;

import com.github.soravoid.exceed.init.ExceedItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum ExceedArmorMaterials implements IArmorMaterial
{
    TETHERED("tethered", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2.0F, () -> Ingredient.fromItems(ExceedItems.TETHERED_INGOT.get())),
    ;

    public static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    final String name;
    final int durability;
    final int[] damageReduction;
    final int enchantability;
    final SoundEvent equipSound;
    final float toughness;
    final Supplier<Ingredient> repairMaterial;


    ExceedArmorMaterials(String name, int durability, int[] damageReduction, int enchantability, SoundEvent sound, float toughness, Supplier<Ingredient> repairMaterial)
    {
        this.name = name;
        this.durability = durability;
        this.damageReduction = damageReduction;
        this.enchantability = enchantability;
        this.equipSound = sound;
        this.toughness = toughness;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) { return MAX_DAMAGE_ARRAY[slotIn.getIndex()]*this.durability; }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) { return damageReduction[slotIn.getIndex()]; }

    @Override
    public int getEnchantability() { return enchantability; }

    @Override
    public SoundEvent getSoundEvent() { return equipSound; }

    @Override
    public Ingredient getRepairMaterial() { return repairMaterial.get(); }

    @OnlyIn(Dist.CLIENT)
    @Override
    public String getName() { return name; }

    @Override
    public float getToughness() { return toughness; }
}
