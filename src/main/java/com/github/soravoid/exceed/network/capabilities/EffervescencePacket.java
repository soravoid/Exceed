package com.github.soravoid.exceed.network.capabilities;

import com.github.soravoid.exceed.capabilities.ChargesCapability;
import com.github.soravoid.exceed.capabilities.EffervescenceCapability;
import com.github.soravoid.exceed.capabilities.instances.EffervescenceInstance;
import com.github.soravoid.exceed.capabilities.interfaces.ICharges;
import com.github.soravoid.exceed.capabilities.interfaces.IEffervescence;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class EffervescencePacket
{
    public int effervescence;
    public int maxEffervescence;

    public EffervescencePacket(int max, int current)
    {
        this.effervescence = current;
        this.maxEffervescence = max;
    }

    public static void encode(EffervescencePacket pkt, PacketBuffer buf)
    {
        buf.writeInt(pkt.maxEffervescence);
        buf.writeInt(pkt.effervescence);
    }

    public static EffervescencePacket decode(PacketBuffer buf) { return new EffervescencePacket(buf.readInt(), buf.readInt()); }

    public static void handle(EffervescencePacket pkt, Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection().getReceptionSide().isClient())
        {
            ctx.get().enqueueWork(() -> {
                ClientPlayerEntity player = Minecraft.getInstance().player;
                IEffervescence cap = player.getCapability(EffervescenceCapability.EFFERVESCENCE_CAPABILITY).orElse(null);
                if(cap != null) cap.setEffervescencesSilent(pkt.maxEffervescence, pkt.effervescence);
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
