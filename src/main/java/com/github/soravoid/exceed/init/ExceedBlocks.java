package com.github.soravoid.exceed.init;

import com.github.soravoid.exceed.blocks.TetheredOre;
import com.github.soravoid.exceed.Exceed;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ExceedBlocks
{
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Exceed.MODID);

    public static final RegistryObject<Block> TETHERED_ORE = BLOCKS.register("tethered_ore", () -> new TetheredOre());
}
