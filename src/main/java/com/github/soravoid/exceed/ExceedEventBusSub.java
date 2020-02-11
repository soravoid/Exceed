package com.github.soravoid.exceed;

import com.github.soravoid.exceed.capabilities.ChargesCapability;
import com.github.soravoid.exceed.capabilities.SmithQualityCapability;
import com.github.soravoid.exceed.init.ExceedBlocks;
import com.github.soravoid.exceed.items.IExceedTool;
import com.github.soravoid.exceed.items.weapons.IChargeable;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = Exceed.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExceedEventBusSub
{
    public static final Logger LOG = LogManager.getLogger(Exceed.MODID + "_EVENTBUS");

    private static final ResourceLocation SMITH_QUAL_RESOURCE = new ResourceLocation(Exceed.MODID, "smith_quality");
    private static final ResourceLocation CHARGES_RESOURCE = new ResourceLocation(Exceed.MODID, "charges");

    @SubscribeEvent
    public static void onRegisterItem(RegistryEvent.Register<Item> e)
    {
        ExceedBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().group(Exceed.EXCEED_TAB);
            final BlockItem blockItem = new BlockItem(block, properties);
            e.getRegistry().register(setup(blockItem, block.getRegistryName()));
        });
    }

    @SubscribeEvent
    public static void onAttachItems(AttachCapabilitiesEvent<Item> e)
    {
        if(e.getObject() instanceof IExceedTool) e.addCapability(SMITH_QUAL_RESOURCE, new SmithQualityCapability());
        if(e.getObject() instanceof IChargeable) e.addCapability(CHARGES_RESOURCE, new ChargesCapability());
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
        return setup(entry, new ResourceLocation(Exceed.MODID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
        entry.setRegistryName(registryName);
        return entry;
    }
}