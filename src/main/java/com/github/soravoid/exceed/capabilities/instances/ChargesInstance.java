package com.github.soravoid.exceed.capabilities.instances;

import com.github.soravoid.exceed.capabilities.interfaces.ICharges;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class ChargesInstance implements ICharges
{
    private int charges = 0;

    @Override
    public int getCharges() { return charges; }

    @Override
    public void setCharges(int amt) { this.charges = amt; }

    @Override
    public void addCharges(int amt) { this.charges += amt; }

    @Override
    public void removeCharges(int amt) { this.charges -= amt; }

    @Override
    public void discharge() { this.charges = 0; }

    public static class Storage implements Capability.IStorage<ICharges>
    {
        @Nullable
        @Override
        public INBT writeNBT(Capability<ICharges> capability, ICharges instance, Direction side) { return IntNBT.valueOf(instance.getCharges()); }

        @Override
        public void readNBT(Capability<ICharges> capability, ICharges instance, Direction side, INBT nbt) { instance.setCharges(((IntNBT) nbt).getInt()); }
    }
}
