package com.github.soravoid.exceed.items.tools;

import com.github.soravoid.exceed.Exceed;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChillingAxe extends AxeItem
{
    public ChillingAxe()
    {
        super(ExceedTiers.EXCEED, 5.0f, -3.0f, new Item.Properties().group(Exceed.EXCEED_TAB));
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving)
    {
        if(stack.getItem().canHarvestBlock(state))
        {
            BlockState[] toCheck = new BlockState[]{};
            if(BlockTags.LOGS.contains(state.getBlock()))
            {

            }
        }
        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }
}
