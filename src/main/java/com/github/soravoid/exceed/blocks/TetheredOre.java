package com.github.soravoid.exceed.blocks;

import com.github.soravoid.exceed.items.tools.IMineTetheredOre;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TetheredOre extends Block
{
    public TetheredOre()
    {
        super(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 3.0f));
    }

    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        if(!(stack.getItem() instanceof IMineTetheredOre))
        {
            worldIn.createExplosion(null, DamageSource.GENERIC, player.getPosX(), player.getPosY(), player.getPosZ(), 3, false, Explosion.Mode.DESTROY);
        }
        else
        {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }
}
