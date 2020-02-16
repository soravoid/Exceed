package com.github.soravoid.exceed;

import com.github.soravoid.exceed.capabilities.ChargesCapability;
import com.github.soravoid.exceed.capabilities.EffervescenceCapability;
import com.github.soravoid.exceed.capabilities.SmithQualityCapability;
import com.github.soravoid.exceed.capabilities.interfaces.IEffervescence;
import com.github.soravoid.exceed.events.ExceedEvents;
import com.github.soravoid.exceed.items.tools.IExceedTool;
import com.github.soravoid.exceed.items.weapons.HellfireSword;
import com.github.soravoid.exceed.items.weapons.IChargeable;
import com.github.soravoid.exceed.network.ExceedPacketHandler;
import com.github.soravoid.exceed.network.capabilities.EffervescencePacket;
import com.github.soravoid.exceed.render.RenderEffervescenceBar;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkDirection;

@Mod.EventBusSubscriber(modid = Exceed.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ExceedForgeBusSub
{
    private static final ResourceLocation SMITH_QUAL_RESOURCE = new ResourceLocation(Exceed.MODID, "smith_quality");
    private static final ResourceLocation CHARGES_RESOURCE = new ResourceLocation(Exceed.MODID, "charges");
    private static final ResourceLocation EFFERVESCENCE_RESOURCE = new ResourceLocation(Exceed.MODID, "effervescence");

    @SubscribeEvent
    public static void onAttachItems(AttachCapabilitiesEvent<ItemStack> e)
    {
        if(e.getObject().getItem() instanceof IExceedTool) e.addCapability(SMITH_QUAL_RESOURCE, new SmithQualityCapability());
        if(e.getObject().getItem() instanceof IChargeable) e.addCapability(CHARGES_RESOURCE, new ChargesCapability());
    }

    @SubscribeEvent
    public static void onAttachPlayer(AttachCapabilitiesEvent<Entity> e)
    {
        if(e.getObject().getEntity() instanceof PlayerEntity) e.addCapability(EFFERVESCENCE_RESOURCE, new EffervescenceCapability());

        e.getObject().getCapability(EffervescenceCapability.EFFERVESCENCE_CAPABILITY).ifPresent(cap -> {
            cap.setPlayer((PlayerEntity) e.getObject());
        });
    }

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent e)
    {
        if(e.player.getHeldItem(Hand.MAIN_HAND).getItem() instanceof HellfireSword)
        {
            BlockPos pos = new BlockPos(new BlockPos(e.player.getPosX(), e.player.getPosY() - 1, e.player.getPosZ()));
            BlockState state = e.player.world.getBlockState(pos);
            if(state == Blocks.GRASS_BLOCK.getDefaultState()) e.player.world.setBlockState(pos, Blocks.DIRT.getDefaultState());
            else if(state == Blocks.SAND.getDefaultState()) e.player.world.setBlockState(pos, Blocks.SAND.getDefaultState()); // TODO Fix
        }
    }

    @OnlyIn(Dist.DEDICATED_SERVER)
    @SubscribeEvent
    public static void onEffervescenceChanged(ExceedEvents.EffervescenceChangedEvent e)
    {
        e.player.getCapability(EffervescenceCapability.EFFERVESCENCE_CAPABILITY).ifPresent(cap -> {
            ExceedPacketHandler.INSTANCE.sendTo(new EffervescencePacket(cap.getMaxEffervescence(), cap.getEffervescence()), ((ServerPlayerEntity)e.player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
            cap.setPlayer(e.player);
        });
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e)
    {
        e.getPlayer().getCapability(EffervescenceCapability.EFFERVESCENCE_CAPABILITY).ifPresent(cap -> {
            ExceedPacketHandler.INSTANCE.sendTo(new EffervescencePacket(cap.getMaxEffervescence(), cap.getEffervescence()), ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
        });
        RenderEffervescenceBar.timeSinceLastUpdate = 0;
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone e)
    {
        if(e.isWasDeath())
        {
            e.getOriginal().getCapability(EffervescenceCapability.EFFERVESCENCE_CAPABILITY).ifPresent(cap1 -> {
                e.getPlayer().getCapability(EffervescenceCapability.EFFERVESCENCE_CAPABILITY).ifPresent(cap2 -> {
                    cap2.setEffervescencesSilent(cap1.getMaxEffervescence(), cap1.getMaxEffervescence());
                    cap2.setPlayer(e.getPlayer());
                    ExceedPacketHandler.INSTANCE.sendTo(new EffervescencePacket(cap1.getMaxEffervescence(), cap1.getMaxEffervescence()), ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
                });
            });
        }
    }
}
