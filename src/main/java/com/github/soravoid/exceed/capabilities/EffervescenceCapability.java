package com.github.soravoid.exceed.capabilities;

import com.github.soravoid.exceed.capabilities.interfaces.IEffervescence;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EffervescenceCapability implements ICapabilitySerializable<CompoundNBT>
{
    @CapabilityInject(IEffervescence.class)
    public static Capability<IEffervescence> EFFERVESCENCE_CAPABILITY = null;

    private LazyOptional<IEffervescence> instance = LazyOptional.of(EFFERVESCENCE_CAPABILITY::getDefaultInstance);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) { return EFFERVESCENCE_CAPABILITY.orEmpty(cap, instance); }

    @Override
    public void deserializeNBT(CompoundNBT nbt) { EFFERVESCENCE_CAPABILITY.getStorage().readNBT(EFFERVESCENCE_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null, nbt); }

    @Override
    public CompoundNBT serializeNBT() { return (CompoundNBT) EFFERVESCENCE_CAPABILITY.getStorage().writeNBT(EFFERVESCENCE_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null); }
}
