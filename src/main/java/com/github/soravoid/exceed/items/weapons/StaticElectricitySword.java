package com.github.soravoid.exceed.items.weapons;

import com.github.soravoid.exceed.Exceed;
import com.github.soravoid.exceed.capabilities.ChargesCapability;
import com.github.soravoid.exceed.capabilities.interfaces.ICharges;
import com.github.soravoid.exceed.items.tools.ExceedSword;
import com.github.soravoid.exceed.items.tools.ExceedTiers;
import com.github.soravoid.exceed.network.ExceedPacketHandler;
import com.github.soravoid.exceed.network.capabilities.ChargePacket;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkDirection;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Exceed.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class StaticElectricitySword extends ExceedSword implements IChargeable
{
    public StaticElectricitySword()
    {
        //TODO Adjust damage and speed values, these are placeholders from Items.DIAMOND_SWORD
        super(ExceedTiers.EXCEED, 3, -3.2f, new Item.Properties().group(Exceed.EXCEED_TAB));

        this.addPropertyOverride(new ResourceLocation(Exceed.MODID, "charges"), (stack, world, entity) -> {
            ICharges cap = stack.getCapability(ChargesCapability.CHARGES_CAPABILITY).orElse(null);
            if(cap != null) return cap.getCharges();
            return 0;
        });
    }

    @SubscribeEvent
    public static void onLivingHurt(AttackEntityEvent e)
    {
        ItemStack stack = e.getPlayer().getHeldItem(Hand.MAIN_HAND);
        if(stack.getItem() instanceof StaticElectricitySword)
        {
            if(e.getPlayer().getCooledAttackStrength(0) == 1.0f)
            {
                ICharges cap = stack.getCapability(ChargesCapability.CHARGES_CAPABILITY).orElse(null);
                if(cap != null)
                {
                    if(cap.getCharges() < 3)
                    {
                        cap.addCharges(1);
                        if(!e.getPlayer().world.isRemote) ExceedPacketHandler.INSTANCE.sendTo(new ChargePacket(cap.getCharges()), ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
                    }
                    else
                    {
                        World world = e.getEntityLiving().getEntityWorld();
                        List<LightningBoltEntity> boltons = new ArrayList<>();
                        for(int x = -1; x < 2; x++)
                        {
                            for(int z = -1; z < 2; z++)
                            {
                                boltons.add(new LightningBoltEntity(world, e.getEntityLiving().getPosX() + x, e.getEntityLiving().getPosY(), e.getEntityLiving().getPosZ() + z, false));
                            }
                        }
                        cap.discharge();
                        if(!e.getPlayer().world.isRemote)
                        {
                            ExceedPacketHandler.INSTANCE.sendTo(new ChargePacket(cap.getCharges()), ((ServerPlayerEntity)e.getPlayer()).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
                            for(int i = 0; i < boltons.size(); i++)
                            {
                                ((ServerWorld)world).addLightningBolt(boltons.get(i));
                            }
                        }
                    }
                }
            }
        }
    }
}
