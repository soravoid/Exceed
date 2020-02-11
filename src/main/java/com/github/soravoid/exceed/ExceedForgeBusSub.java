package com.github.soravoid.exceed;

import com.github.soravoid.exceed.capabilities.ChargesCapability;
import com.github.soravoid.exceed.capabilities.SmithQualityCapability;
import com.github.soravoid.exceed.items.IExceedTool;
import com.github.soravoid.exceed.items.weapons.IChargeable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Exceed.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ExceedForgeBusSub
{
    private static final ResourceLocation SMITH_QUAL_RESOURCE = new ResourceLocation(Exceed.MODID, "smith_quality");
    private static final ResourceLocation CHARGES_RESOURCE = new ResourceLocation(Exceed.MODID, "charges");

    @SubscribeEvent
    public static void onAttachItems(AttachCapabilitiesEvent<ItemStack> e)
    {
        if(e.getObject().getItem() instanceof IExceedTool) e.addCapability(SMITH_QUAL_RESOURCE, new SmithQualityCapability());
        if(e.getObject().getItem() instanceof IChargeable) e.addCapability(CHARGES_RESOURCE, new ChargesCapability());
    }
}
