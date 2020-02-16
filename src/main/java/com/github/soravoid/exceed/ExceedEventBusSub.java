package com.github.soravoid.exceed;

import com.github.soravoid.exceed.capabilities.ChargesCapability;
import com.github.soravoid.exceed.capabilities.interfaces.ICharges;
import com.github.soravoid.exceed.init.ExceedBlocks;
import com.github.soravoid.exceed.network.ExceedPacketHandler;
import com.github.soravoid.exceed.network.capabilities.ChargePacket;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Exceed.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExceedEventBusSub
{

    @SubscribeEvent
    public static void onRegisterItem(RegistryEvent.Register<Item> e)
    {
        ExceedBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().group(Exceed.EXCEED_TAB);
            final BlockItem blockItem = new BlockItem(block, properties);
            e.getRegistry().register(setup(blockItem, block.getRegistryName()));
        });
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
        return setup(entry, new ResourceLocation(Exceed.MODID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
        entry.setRegistryName(registryName);
        return entry;
    }
}
