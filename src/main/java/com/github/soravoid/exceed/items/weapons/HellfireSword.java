package com.github.soravoid.exceed.items.weapons;

import com.github.soravoid.exceed.Exceed;
import com.github.soravoid.exceed.items.tools.ExceedSword;
import com.github.soravoid.exceed.items.tools.ExceedTiers;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Exceed.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HellfireSword extends ExceedSword
{
    public HellfireSword()
    {
        //TODO Adjust damage and speed values, these are placeholders from Items.DIAMOND_SWORD
        super(ExceedTiers.EXCEED, 3, -3.2F, new Item.Properties().group(Exceed.EXCEED_TAB));
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        return false;
    }

    @SubscribeEvent
    public static void onLivingHurt(AttackEntityEvent e)
    {
        ItemStack stack = e.getPlayer().getHeldItem(Hand.MAIN_HAND);
        if(stack.getItem() instanceof HellfireSword)
        {
            if(e.getPlayer().getCooledAttackStrength(0) == 1.0f)
            {
                e.getTarget().setFireTimer(300);
                if(!(e.getTarget().getFireTimer() > 0)) e.getTarget().attackEntityFrom(DamageSource.causePlayerDamage(e.getPlayer()), 8);
                else e.getTarget().attackEntityFrom(DamageSource.causePlayerDamage(e.getPlayer()), 4);
            }
            else
            {
                e.getTarget().setFireTimer(20);
            }
        }
    }
}
