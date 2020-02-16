package com.github.soravoid.exceed.network;

import com.github.soravoid.exceed.Exceed;
import com.github.soravoid.exceed.network.capabilities.ChargePacket;
import com.github.soravoid.exceed.network.capabilities.EffervescencePacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class ExceedPacketHandler
{
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Exceed.MODID, "main_simple"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION:: equals
    );

    public static void init()
    {
        int id = 0;

        INSTANCE.registerMessage(id++, ChargePacket.class, ChargePacket::encode, ChargePacket::decode, ChargePacket::handle);
        INSTANCE.registerMessage(id++, EffervescencePacket.class, EffervescencePacket::encode, EffervescencePacket:: decode, EffervescencePacket::handle);
    }
}
