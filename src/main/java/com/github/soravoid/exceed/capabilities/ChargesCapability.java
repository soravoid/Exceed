package com.github.soravoid.exceed.capabilities;

import com.github.soravoid.exceed.capabilities.interfaces.ICharges;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ChargesCapability implements ICapabilitySerializable<IntNBT>
{
    @CapabilityInject(ICharges.class)
    public static final Capability<ICharges> CHARGES_CAPABILITY = null;

    private LazyOptional<ICharges> instance = LazyOptional.of(CHARGES_CAPABILITY::getDefaultInstance);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) { return CHARGES_CAPABILITY.orEmpty(cap, instance); }

    @Override
    public void deserializeNBT(IntNBT nbt) { CHARGES_CAPABILITY.getStorage().readNBT(CHARGES_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null, nbt); }

    @Override
    public IntNBT serializeNBT() { return (IntNBT) CHARGES_CAPABILITY.getStorage().writeNBT(CHARGES_CAPABILITY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional cannot be empty!")), null); }

    public static class Storage implements Capability.IStorage<ICharges>
    {
        @Nullable
        @Override
        public INBT writeNBT(Capability<ICharges> capability, ICharges instance, Direction side) { return IntNBT.valueOf(instance.getCharges()); }

        @Override
        public void readNBT(Capability<ICharges> capability, ICharges instance, Direction side, INBT nbt) { instance.setCharges(((IntNBT) nbt).getInt()); }
    }
}
