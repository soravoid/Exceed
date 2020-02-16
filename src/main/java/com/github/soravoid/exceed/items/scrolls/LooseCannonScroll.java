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

public class LooseCannonScroll extends Item
{
    public LooseCannonScroll()
    {
        super(new Item.Properties().group(Exceed.EXCEED_TAB).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        return player.getCapability(EffervescenceCapability.EFFERVESCENCE_CAPABILITY).map(cap -> {
            if(cap.consume(50))
            {
                Vec3d lookVec = player.getLookVec();
                int multiplier = 11;
                for(int i = 0; i < 10; i++)
                {
                    FireballEntity ball = new FireballEntity(world, player, lookVec.x * multiplier, lookVec.y * multiplier, lookVec.z * multiplier);
                    ball.setPosition(player.getPosX() + lookVec.x * 4.0D, player.getPosYHeight(0.5D) + 0.5D, player.getPosZ() + lookVec.z * 4.0D);
                    world.addEntity(ball);
                    multiplier -= 1;
                }
                stack.setCount(stack.getCount() - 1);
                return ActionResult.resultConsume(stack);
            }
            else
            {
                Exceed.LOG.info("Player did not have enough Effervescence!");
                return ActionResult.resultFail(stack);
            }
        }).orElse(super.onItemRightClick(world, player, hand));
    }
}
