package com.github.soravoid.exceed.items.armor;

import com.github.soravoid.exceed.Exceed;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class TetheredArmor extends ArmorItem
{
    public TetheredArmor(EquipmentSlotType slotType)
    {
        super(ExceedArmorMaterials.TETHERED, slotType, new Properties().group(Exceed.EXCEED_TAB));
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        if (slot == EquipmentSlotType.LEGS) {
            return Exceed.MODID + ":textures/armor/tethered_2.png";
        } else {
            return Exceed.MODID + ":textures/armor/tethered_1.png";
        }
    }
}
