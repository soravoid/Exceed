package com.github.soravoid.exceed;

import com.github.soravoid.exceed.capabilities.ChargesCapability;
import com.github.soravoid.exceed.capabilities.SmithQualityCapability;
import com.github.soravoid.exceed.capabilities.instances.ChargesInstance;
import com.github.soravoid.exceed.capabilities.instances.SmithQualityInstance;
import com.github.soravoid.exceed.capabilities.interfaces.ICharges;
import com.github.soravoid.exceed.capabilities.interfaces.ISmithQuality;
import com.github.soravoid.exceed.init.ExceedBlocks;
import com.github.soravoid.exceed.init.ExceedItemGroups;
import com.github.soravoid.exceed.init.ExceedItems;
import com.github.soravoid.exceed.network.ExceedPacketHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Exceed.MODID)
public class Exceed
{
    public static final String MODID = "exceed";
    public static final Logger LOG = LogManager.getLogger(MODID);

    public static final ItemGroup EXCEED_TAB = new ExceedItemGroups.EXCEED_GROUP(MODID, () -> new ItemStack(ExceedItems.TEST_ITEM.get()));

    public Exceed()
    {
        LOG.info("\"Hello World\" -Exceed");
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ExceedItems.ITEMS.register(modEventBus);
        ExceedBlocks.BLOCKS.register(modEventBus);
        modEventBus.addListener(this::onCommonSetup);
    }

    private void onCommonSetup(FMLCommonSetupEvent e)
    {
        ExceedPacketHandler.init();
        CapabilityManager.INSTANCE.register(ISmithQuality.class, new SmithQualityCapability.Storage(), SmithQualityInstance::new);
        CapabilityManager.INSTANCE.register(ICharges.class, new ChargesCapability.Storage(), ChargesInstance::new);
    }
}
