package com.github.soravoid.exceed.items.weapons;

import com.github.soravoid.exceed.Exceed;
import com.github.soravoid.exceed.capabilities.ChargesCapability;
import com.github.soravoid.exceed.capabilities.SmithQualityCapability;
import com.github.soravoid.exceed.capabilities.interfaces.ICharges;
import com.github.soravoid.exceed.capabilities.interfaces.ISmithQuality;
import com.github.soravoid.exceed.items.ExceedSword;
import com.github.soravoid.exceed.items.ExceedTiers;
import com.github.soravoid.exceed.network.ExceedPacketHandler;
import com.github.soravoid.exceed.network.capabilities.ChargePacket;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkDirection;

import javax.annotation.Nullable;
import java.util.List;

public class StaticElectricitySword extends ExceedSword implements IChargeable
{
    public StaticElectricitySword()
    {
        //TODO Adjust damage and speed values, these are placeholders from Items.DIAMOND_SWORD
        super(ExceedTiers.EXCEED, 3, -2.4F, new Item.Properties().group(Exceed.EXCEED_TAB));

        this.addPropertyOverride(new ResourceLocation(Exceed.MODID, "charges"), (stack, world, entity) -> {
            ICharges cap = stack.getCapability(ChargesCapability.CHARGES_CAPABILITY).orElse(null);
            if(cap != null) return cap.getCharges();
            return 0;
        });
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
        if(attacker instanceof PlayerEntity)
        {
            PlayerEntity player = (PlayerEntity) attacker;
            ICharges cap = stack.getCapability(ChargesCapability.CHARGES_CAPABILITY).orElse(null);
            if(cap != null)
            {
                if(cap.getCharges() < 3)
                {
                    cap.addCharges(1);
                    if(!player.world.isRemote) ExceedPacketHandler.INSTANCE.sendTo(new ChargePacket(cap.getCharges()), ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
                }
                else
                {
                    World world = target.getEntityWorld();
                    LightningBoltEntity bolton = new LightningBoltEntity(target.getEntityWorld(), target.getPosX(), target.getPosY(), target.getPosZ(), false);
                    cap.discharge();
                    if(!player.world.isRemote)
                    {
                        ExceedPacketHandler.INSTANCE.sendTo(new ChargePacket(cap.getCharges()), ((ServerPlayerEntity)player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
                        ((ServerWorld)world).addLightningBolt(bolton);
                    }
                }
            }
        }
        return true;
    }
}
