package com.github.soravoid.exceed.items.weapons;

import com.github.soravoid.exceed.Exceed;
import com.github.soravoid.exceed.capabilities.ChargesCapability;
import com.github.soravoid.exceed.capabilities.SmithQualityCapability;
import com.github.soravoid.exceed.capabilities.interfaces.ICharges;
import com.github.soravoid.exceed.capabilities.interfaces.ISmithQuality;
import com.github.soravoid.exceed.items.ExceedSword;
import com.github.soravoid.exceed.items.ExceedTiers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

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
        ICharges cap = stack.getCapability(ChargesCapability.CHARGES_CAPABILITY).orElse(null);
        Exceed.LOG.info("Hit an Entity!");
        if(cap != null)
        {
            Exceed.LOG.info("Capability Exists");
            if(cap.getCharges() < 4) cap.addCharges(1);
            else
            {
                Exceed.LOG.info("Adding Charge");
                World world = target.getEntityWorld();
                double yVal = target.getPosY();
                // TODO Find a better way to check for a solid block
                while(!world.getBlockState(new BlockPos(target.getPosX(), yVal, target.getPosZ())).isAir()) yVal -= 1;
                LightningBoltEntity bolt = new LightningBoltEntity(target.getEntityWorld(), target.getPosX(), yVal, target.getPosZ(), false);
                world.addEntity(bolt);
                cap.discharge();
            }
        }
        else
        {
            Exceed.LOG.info("Capability No Exists");
        }
        return true;
    }
}
