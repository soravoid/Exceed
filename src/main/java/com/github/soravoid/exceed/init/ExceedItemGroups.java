package com.github.soravoid.exceed.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.function.Supplier;

public class ExceedItemGroups
{
    public static class EXCEED_GROUP extends ItemGroup
    {
        private final Supplier<ItemStack> iconSupplier;

        public EXCEED_GROUP(final String name, final Supplier<ItemStack> iconSupplier)
        {
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        public ItemStack createIcon() { return this.iconSupplier.get(); }
    }
}
