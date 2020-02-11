package com.github.soravoid.exceed.items;

import com.github.soravoid.exceed.capabilities.SmithQualityCapability;
import com.github.soravoid.exceed.capabilities.interfaces.ISmithQuality;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ExceedSword extends SwordItem implements IExceedTool
{
    public ExceedSword(IItemTier tier, int atkDmg, float atkSpd, Item.Properties props)
    {
        super(tier, atkDmg, atkSpd, props);
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = HashMultimap.create();
        //TODO Take into account modifiers from Smith Quality
        return multimap;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        ISmithQuality cap = stack.getCapability(SmithQualityCapability.SMITH_QUALITY_CAPABILITY).orElse(null);
        if(cap != null) tooltip.add(new StringTextComponent(String.format("Quality: %s", cap.getQuality().getName())));
    }
}
