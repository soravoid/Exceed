package com.github.soravoid.exceed.capabilities;

import com.github.soravoid.exceed.capabilities.interfaces.ISmithQuality;
import com.github.soravoid.exceed.util.SmithQuality;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class SmithQualityCapability implements ICapabilitySerializable<IntNBT>
{
    @CapabilityInject(ISmithQuality.class)
    public static final Capability<ISmithQuality> SMITH_QUALITY_CAPABILITY = null;

    private LazyOptional<ISmithQuality> instance = LazyOptional.of(SMITH_QUALITY_CAPABILITY::getDefaultInstance);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) { return SMITH_QUALITY_CAPABILITY.orEmpty(cap, instance); }

    @Override
    public IntNBT serializeNBT() { return (IntNBT) SMITH_QUALITY_CAPABILITY.getStorage().writeNBT(SMITH_QUALITY_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null); }

    @Override
    public void deserializeNBT(IntNBT nbt) { SMITH_QUALITY_CAPABILITY.getStorage().readNBT(SMITH_QUALITY_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null, nbt); }


}
