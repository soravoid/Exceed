package com.github.soravoid.exceed.network.capabilities;

import com.github.soravoid.exceed.capabilities.ChargesCapability;
import com.github.soravoid.exceed.capabilities.interfaces.ICharges;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ChargePacket
{
    public int charge;

    public ChargePacket(int amt)
    {
        this.charge = amt;
    }

    public static void encode(ChargePacket pkt, PacketBuffer buf)
    {
        buf.writeInt(pkt.charge);
    }

    public static ChargePacket decode(PacketBuffer buf)
    {
        return new ChargePacket(buf.readInt());
    }

    public static void handle(ChargePacket pkt, Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection().getReceptionSide().isClient())
        {
            ctx.get().enqueueWork(() -> {
                ClientPlayerEntity player = Minecraft.getInstance().player;
                ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
                ICharges cap = stack.getCapability(ChargesCapability.CHARGES_CAPABILITY).orElse(null);
                if(cap != null) cap.setCharges(pkt.charge);
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
