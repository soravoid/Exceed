package com.github.soravoid.exceed.items.scrolls;

import com.github.soravoid.exceed.Exceed;
import com.github.soravoid.exceed.capabilities.EffervescenceCapability;
import com.github.soravoid.exceed.capabilities.interfaces.IEffervescence;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LooseCannonScroll extends Item
{
    public LooseCannonScroll()
    {
        super(new Item.Properties().group(Exceed.EXCEED_TAB).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        IEffervescence cap = player.getCapability(EffervescenceCapability.EFFERVESCENCE_CAPABILITY).orElse(null);
        Exceed.LOG.info(cap);
        if(cap != null)
        {
            if(cap.consume(50))
            {
                Vec3d lookVec = player.getLookVec();
                for(int i = 0; i < 10; i++)
                {
                    world.addEntity(new FireballEntity(world, player, lookVec.x, lookVec.y, lookVec.z));
                }
                stack.setCount(stack.getCount() - 1);
                return ActionResult.resultConsume(stack);
            }
            else
            {
                Exceed.LOG.info("Player did not have enough Effervescence!");
                return ActionResult.resultFail(stack);
            }
        }
        return super.onItemRightClick(world, player, hand);
    }
}
